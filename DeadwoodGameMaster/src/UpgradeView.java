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

//The labels for each upgrade so you can click directly on the board to upgrade. Their are seperate labels for each upgrade that just listen to the mouse and if they get clicked on then
//the user pays that amount to upgrade to the level shown. 

public class UpgradeView extends JPanel{

    private ArrayList<JLabel> labelList;
    private Controller c;

    public UpgradeView(Board board, Controller controller){
	super(null);
	c = controller;
	ArrayList<Upgrade> area = board.getRoom("Casting Office").getUpgrades();
	labelList = new ArrayList<JLabel>();
	setLayout(null);
	setBounds(0, 0, 1200, 900);
	setOpaque(false);

	JLabel l;

	int i = 0;
	while(i < area.size()){
	    l = new JLabel();
	    add(l, 0);
	    labelList.add(l);
	    l.setBounds(area.get(i).getArea().get(0), area.get(i).getArea().get(1), area.get(i).getArea().get(2), area.get(i).getArea().get(3));
	    l.setVisible(true);
	    i++;
	}

	i = 0;
	while(i < labelList.size()){
	    switch (i){
	    case 0: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.d1Click();
		    }
		});
		break;
	    case 1: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.d2Click();
		    }
		});
		break;
	    case 2: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.d3Click();
		    }
		});
		break;
	    case 3: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.d4Click();
		    }
		});
		break;
	    case 4: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.d5Click();
		    }
		});
		break;
	    case 5: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.c1Click();
		    }
		});
		break;
	    case 6: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.c2Click();
		    }
		});
		break;
	    case 7: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.c3Click();
		    }
		});
		break;
	    case 8: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.c4Click();
		    }
		});
		break;
	    case 9: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.c5Click();
		    }
		});
		break;
	    }
	    i++;
	}
    }

}
