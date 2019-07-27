import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.awt.FlowLayout;
import java.awt.Color;
import java.util.*;
import java.awt.BorderLayout;
import java.util.concurrent.*;

public class DeadwoodGUI{

    private Controller controller;
    private JFrame mainFrame;
    private PlayerResources r;
    private SceneResources s;
    private ShotCounterResources sc;
    private RoomView rView;
    private SidePanel sidePanel;
    private BottomLeftPanel bottomLeftPanel;
    private BottomPanel bottomPanel;
    private ArrayList<PlayerView> playerViewList;
    private ArrayList<SceneView> sceneViewList;
    private ArrayList<ShotCounterView> shotViewList;

    // Creates the whole board with all necessary panels and views
    public DeadwoodGUI(Board board) throws IOException{
	controller = new Controller(board);
	setupPlayerViews(board, board.getNumPlayers());
	setupSceneViews(board);
	setupShotCounterViews(board);

	mainFrame = new JFrame();

	mainFrame.setTitle("Deadwood");
	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainFrame.setLayout(null);

	createSceneCardPanels(mainFrame, board);

	bottomPanel = new BottomPanel(mainFrame, controller);
	sidePanel = new SidePanel(mainFrame, board.getPlayers());
	bottomLeftPanel = new BottomLeftPanel(mainFrame, board);
	UpgradeView upgradeView = new UpgradeView(board, controller);
	SceneMovePanels smp = new SceneMovePanels(board, controller);
	WorkRolePanels wrp = new WorkRolePanels(board, controller);

	board.addListener(bottomLeftPanel);

	JPanel boardpanel = makeBoardPanel();

	// Adds playerViews as listeners to players
	for(int i = 0; i < playerViewList.size(); i++){
	    boardpanel.add(playerViewList.get(i), 0);
	    board.getPlayer(i).addListener(sidePanel);
	    board.getPlayer(i).addListener(bottomPanel);
	}

	board.getPlayer(board.getPlayerListSize()).addListener(sidePanel);
	board.getPlayer(board.getPlayerListSize()).addListener(bottomPanel);
	board.addListener(bottomPanel);
	boardpanel.add(upgradeView);
	boardpanel.add(smp);
	boardpanel.add(wrp);
	mainFrame.add(sidePanel);
	mainFrame.add(bottomLeftPanel);
	mainFrame.add(boardpanel);		
	mainFrame.pack();
	mainFrame.setSize(1400, 1100);

	DayKeeper daykeeper = new DayKeeper(board);
	board.addListener(daykeeper);
	
	mainFrame.setVisible(true);
	mainFrame.setResizable(false);

	// Requests focus for all playerViews
	for(int i = 0; i < playerViewList.size(); i++){
	    playerViewList.get(i).requestFocus();
	}
	for (int j =0; j<sceneViewList.size(); j++){
	    boardpanel.add(sceneViewList.get(j), playerViewList.size());
	    sceneViewList.get(j).requestFocus();
	}
	for (int k = 0; k<shotViewList.size(); k++){
	    boardpanel.add(shotViewList.get(k), 0);
	    shotViewList.get(k).requestFocus();
	}

    }

    //Initializes all shotCounter views and sets their icons
    private void setupShotCounterViews(Board board){
	sc = ShotCounterResources.getInstance();
	shotViewList = new ArrayList<ShotCounterView>();
	ShotCounterView scv;
	for (int x =0; x<board.getRoomList().size(); x++){
	    for (int y=0; y<board.getRoomList().get(x).getShotCounters(); y++){
		scv = new ShotCounterView(sc, board.getRoomList().get(x).getShotArea(y), y);
		board.getRoomList().get(x).addListener(scv);
		shotViewList.add(scv);
	    }
	}
    }

    // Initializes all scene views and sets icons
    private void setupSceneViews(Board board){
	s = SceneResources.getInstance();
	sceneViewList = new ArrayList<SceneView>();
	SceneView sv;
	int i = 1;
	while(i < 41){
	    sv = new SceneView(s, i, controller);
	    board.getAllScenes().get(i-1).addListener(sv);
	    sceneViewList.add(sv);
	    i++;
	}
    }

    // Initializes all player views and sets their icon
    private void setupPlayerViews(Board board, int numPlayers){
	r = PlayerResources.getInstance();
	playerViewList = new ArrayList<PlayerView>();
	PlayerView pv;
	int i = 1;
	while(i < numPlayers){
	    pv = new PlayerView(r, i);
	    board.getPlayer(i).addListener(pv);
	    playerViewList.add(pv);
	    i++;
	}
	pv = new PlayerView(r, i);
	board.getPlayer(i).addListener(pv);
	playerViewList.add(pv);
    }

    // Creates bottom left corner panel
    private void createBottomLeftPanel(JFrame mainFrame) throws IOException{
	JPanel bottomRightPanel = new JPanel();
	bottomRightPanel.setBounds(0, 900, 200, 200);
	bottomRightPanel.setBackground(Color.decode("#79CEDC"));
	mainFrame.add(bottomRightPanel);
    }

    // Makes the panel that displays the Deadwood game background
    private JPanel makeBoardPanel() throws IOException{
	JPanel boardpanel = new JPanel(null);
	BufferedImage image = ImageIO.read(new File("resources/board.jpg"));
	JLabel label = new JLabel(new ImageIcon(image));
	label.setBounds(0, 0, 1200, 900);
	boardpanel.add(label);
	boardpanel.setBounds(200, 0, 1200, 900);

	return boardpanel;
    }

    // Creates the x,y corrdinated panels for each scene and adds them to mainFrame
    private void createSceneCardPanels(JFrame mainFrame, Board board) throws IOException{
	ArrayList<Room> room_list = board.getRoomList();
	int x;
	int y;
	JPanel room;
	int i = 0;
	while(i < room_list.size()){
	    if(!room_list.get(i).getName().equals("Trailers") &&
	       !room_list.get(i).getName().equals("Casting Office")){
		x = room_list.get(i).getArea().get(0) + 200;
		y = room_list.get(i).getArea().get(1);
		room = new JPanel();
		room.setBounds(x, y-5, 205, 120);
		room.setOpaque(false);
		BufferedImage image = ImageIO.read(new File("resources/cards/card_back.png"));
		JLabel label = new JLabel(new ImageIcon(image));
		label.setBounds(0, 0, 205, 115);
		room.add(label, -1);
		createSceneRolePanels(room_list.get(i), room);
		mainFrame.add(room);
	    }
	    i++;
	}
    }

    // Adds the scene role panels to each scene
    private void createSceneRolePanels(Room room, JPanel room_panel){
	JPanel sceneRole;
	int x;
	int y;
	Scene scene = room.getScene();
	if(scene != null){
	    ArrayList<Role> roles = scene.getRoles();
	    int num_roles = scene.getNumRoles();
	    int i = 0;
	    while(i < roles.size()){
		x = roles.get(i).getArea().get(0) + 200;
		y = roles.get(i).getArea().get(1);
		sceneRole = new JPanel();
		sceneRole.setBounds(x, y, 40, 40);
		room_panel.add(sceneRole);
		i++;
	    }
	}
    }
}
