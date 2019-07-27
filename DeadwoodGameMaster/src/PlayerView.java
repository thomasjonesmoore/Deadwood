import java.util.LinkedList;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.awt.Color;
import java.util.concurrent.*;
import java.util.ArrayList;

//The view for the player class, each player will have their own PlayerView, they have an offset so players don't stack on top of each other when not working. It listens to the player
//and whenever the player takes a role it moves to the desired role, whenever a player moves it moves to that role and whenever a player's rank changes it will update that.

public class PlayerView extends JPanel implements Player.Listener{

    private JLabel p1;
    private int panelOffSet;

    public PlayerView(PlayerResources r, int playerNum){
	super(null);
	setLayout(null);

	panelOffSet = (playerNum-1) * 5;

	p1 = new JLabel();
	add(p1, 0);
	p1.setBounds(0, 0, 40, 40);
	setBounds(1060 + panelOffSet, 242, 40, 40);
	p1.setIcon(r.getIcon(playerNum, 1));
	setOpaque(false);
	p1.setVisible(true);

    }

    public void playerMoved(Player p){
	int xOffSet = 0;
	int yOffSet = 0;
	if(p.getCurrentRoom().getName().equals("Bank") || p.getCurrentRoom().getName().equals("Casting Office")){
	    yOffSet = panelOffSet;
	}
	else{
	    xOffSet = panelOffSet;
	}
	setBounds(p.getCurrentRoom().getBlankSpace().get(0) - 200 + xOffSet, p.getCurrentRoom().getBlankSpace().get(1) + yOffSet, 40, 40);
    }

    public void roleTaken(Player p){
	int x = p.getRole().getArea().get(0);
	int y = p.getRole().getArea().get(1);
	if(x < 300 && y < 200){
	    x = p.getCurrentRoom().getArea().get(0) + p.getRole().getArea().get(0);
	    y = p.getCurrentRoom().getArea().get(1) + p.getRole().getArea().get(1);
	    setBounds(x, y, 40, 40);	    
	}
	else{
	    setBounds(x, y, 40, 40);
	}

    }

    public void updateScore(Player p){
	PlayerResources r = PlayerResources.getInstance();
	p1.setIcon(r.getIcon(p.getPid(), p.getRank()));
    }

}
