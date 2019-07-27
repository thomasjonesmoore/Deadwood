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

public class CreateSceneCardPanels{

    ArrayList<JPanel> panels;
    JFrame mainFrame;
    
    // Creates the x,y corrdinated panels for each scene and adds them to mainFrame
    public CreateSceneCardPanels(JFrame mainFrame, Board board) throws IOException{
	this.mainFrame = mainFrame;
	panels = new ArrayList<JPanel>();
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
		panels.add(room);
	    }
	    i++;
	}
    }

    public ArrayList<JPanel> getPanels(){
	return panels;
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
