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

public class WorkRoleScenePanels extends JPanel{

    private ArrayList<JLabel> labelList;
    private Controller c;
    private ArrayList<String> roles;

    public WorkRoleScenePanels(Scene scene, Controller controller){
	super(null);
	c = controller;

	roles = new ArrayList<String>();
	ArrayList<Role> roleList;
	ArrayList<Integer> area;

	labelList = new ArrayList<JLabel>();
	setLayout(null);
	setBounds(0, 0, 205, 115);
	setOpaque(false);

	JLabel l;

	String roleName = "";
	int i = 0;
	while(i < scene.getRoles().size()){
	    l = new JLabel();
	    add(l, 0);
	    labelList.add(l);
	    l.setBounds(scene.getRoles().get(i).getArea().get(0), scene.getRoles().get(i).getArea().get(1), scene.getRoles().get(i).getArea().get(2), scene.getRoles().get(i).getArea().get(3));
	    l.setVisible(true);
	    roleName = scene.getRoles().get(i).getName();
	    roles.add(roleName);
	    i++;
	}

	AddWorkRoleScenePanelMouseAdapters a = new AddWorkRoleScenePanelMouseAdapters(roles, labelList, controller, scene);
    }
}
