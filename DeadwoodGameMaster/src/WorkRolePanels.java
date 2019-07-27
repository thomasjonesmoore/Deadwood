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

public class WorkRolePanels extends JPanel{

    private ArrayList<JLabel> labelList;
    private Controller c;
    private ArrayList<String> roles;

    // Sets jpanel clickables for all room roles and adds forwards their clickable
    // events onto the controller
    public WorkRolePanels(Board board, Controller controller){
	super(null);
	c = controller;

	roles = new ArrayList<String>();
	ArrayList<Room> roomList = board.getRoomList();
	ArrayList<Role> roleList;
	ArrayList<Integer> area;

	labelList = new ArrayList<JLabel>();
	setLayout(null);
	setBounds(0, 0, 1200, 900);
	setOpaque(false);

	JLabel l;

	int i = 0;
	int j;
	String roleName = "";
	while(i < roomList.size() - 2){
	    j = 0;
	    while(j < roomList.get(i).getRoles().size()){
		l = new JLabel();
		add(l, 0);
		labelList.add(l);
		l.setBounds(roomList.get(i).getRoles().get(j).getArea().get(0), roomList.get(i).getRoles().get(j).getArea().get(1), roomList.get(i).getRoles().get(j).getArea().get(2), roomList.get(i).getRoles().get(j).getArea().get(3));
		l.setVisible(true);
		roleName = roomList.get(i).getRoles().get(j).getName();
		roles.add(roleName);
		j++;
	    }
	    i++;
	}
	AddWorkRolePanelMouseAdapters a = new AddWorkRolePanelMouseAdapters(roles, labelList, controller);
    }

}

