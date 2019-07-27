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


public class AddWorkRolePanelMouseAdapters{

    private Controller c;
    
    public AddWorkRolePanelMouseAdapters(ArrayList<String> roles, ArrayList<JLabel> labelList, Controller controller){
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
	    case 3: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(3));
		    }
		});
		break;
	    case 4: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(4));
		    }
		});
		break;
	    case 5: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(5));
		    }
		});
		break;
	    case 6: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(6));
		    }
		});
		break;
	    case 7: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(7));
		    }
		});
		break;
	    case 8: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(8));
		    }
		});
		break;
	    case 9: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(9));
		    }
		});
		break;
	    case 10: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(10));
		    }
		});
		break;
	    case 11: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(11));
		    }
		});
		break;
	    case 12: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(12));
		    }
		});
		break;
	    case 13: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(13));
		    }
		});
		break;
	    case 14: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(14));
		    }
		});
		break;
	    case 15: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(15));
		    }
		});
		break;
	    case 16: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(16));
		    }
		});
		break;
	    case 17: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(17));
		    }
		});
		break;
	    case 18: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(18));
		    }
		});
		break;
	    case 19: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(19));
		    }
		});
		break;
	    case 20: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(20));
		    }
		});
		break;
	    case 21: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(21));
		    }
		});
		break;
	    case 22: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(22));
		    }
		});
		break;
	    case 23: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(23));
		    }
		});
		break;	    
	    case 24: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(24));
		    }
		});
		break;
	    case 25: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(25));
		    }
		});
		break;
	    case 26: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(26));
		    }
		});
		break;
	    case 27: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(27));
		    }
		});
		break;
	    case 28: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(28));
		    }
		});
		break;
	    case 29: labelList.get(i).addMouseListener(new MouseAdapter(){
		    @Override
		    public void mouseClicked(MouseEvent e){
			c.roleClick(roles.get(29));
		    }
		});
		break;
	    
	    }
	    i++;
	}
	
	
	
    }
    

}
