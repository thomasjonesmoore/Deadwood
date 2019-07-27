import java.util.LinkedList;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.awt.Color;
import java.util.*;
import java.util.concurrent.*;

public class SceneMovePanels extends JPanel{

    private ArrayList<JLabel> labelList; // stores all labels
    private Controller c;

    // Parses through all rooms and and sets the jpanels for the scene cards
    // complete with mouse click events that are passed on to controller
    public SceneMovePanels(Board board, Controller controller){
	super(null);
	c = controller;

	ArrayList<Room> roomList = board.getRoomList();
	ArrayList<Integer> area;
	
	labelList = new ArrayList<JLabel>();
	setLayout(null);
	setBounds(0, 0, 1200, 900);
	setOpaque(false);

	JLabel l;

	int i = 0;
	while(i < roomList.size() - 2){
	    l = new JLabel();
	    add(l, 0);
	    labelList.add(l);
	    l.setBounds(roomList.get(i).getArea().get(0), roomList.get(i).getArea().get(1), roomList.get(i).getArea().get(3), roomList.get(i).getArea().get(2));
	    l.setVisible(true);
	    i++;
	}

	// Trailers
	l = new JLabel();
	add(l, 0);
	labelList.add(l);
	l.setBounds(991, 248, 201, 194);
	l.setVisible(true);

	// Casting Office
	l = new JLabel();
	add(l, 0);
	labelList.add(l);
	l.setBounds(9, 459, 209, 208);
	l.setVisible(true);
	
	i = 0;
	while(i < labelList.size()){
	    switch (i){
		
	    case 0: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roomClick("Train Station");
		    }
		});
		break;
	    case 1: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roomClick("Secret Hideout");
		    }
		});
		break;
	    case 2: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roomClick("Church");
		    }
		});
		break;
	    case 3: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roomClick("Hotel");
		    }
		});
		break;
	    case 4: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roomClick("Main Street");
		    }
		});
		break;
	    case 5: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roomClick("Jail");
		    }
		});
		break;
	    case 6: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roomClick("General Store");
		    }
		});
		break;
	    case 7: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roomClick("Ranch");
		    }
		});
		break;
	    case 8: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roomClick("Bank");
		    }
		});
		break;
	    case 9: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roomClick("Saloon");
		    }
		});
		break;
	    case 10: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roomClick("Trailers");
		    }
		});
		break;
	    case 11: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roomClick("Casting Office");
		    }
		});
		break;
	    }
	    i++;
	}
    }

}

