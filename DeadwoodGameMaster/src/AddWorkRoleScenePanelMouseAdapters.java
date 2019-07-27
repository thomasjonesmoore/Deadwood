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


public class AddWorkRoleScenePanelMouseAdapters{

    private Controller c;

    // Adds mouse event clicks to the roles that are in a scene and not in a room.
    // Forwards the message to the controller through roleClick()
    public AddWorkRoleScenePanelMouseAdapters(ArrayList<String> roles, ArrayList<JLabel> labelList, Controller controller, Scene scene){
	c = controller;
	int i = 0;

	while(i < labelList.size()){
	    switch(i){

	    case 0: labelList.get(i).addMouseListener(new MouseAdapter(){
		        @Override
			public void mouseClicked(MouseEvent e){
			    c.roleClick(roles.get(0));
			}
		});
		break;
	    case 1: labelList.get(i).addMouseListener(new MouseAdapter(){
		        @Override
			public void mouseClicked(MouseEvent e){
			    c.roleClick(roles.get(1));
			}
		});
		break;
	    case 2: labelList.get(i).addMouseListener(new MouseAdapter(){
		        @Override
			public void mouseClicked(MouseEvent e){
			    c.roleClick(roles.get(2));
			}
		});
		break;
	    }
	    i++;
	}



    }


}
