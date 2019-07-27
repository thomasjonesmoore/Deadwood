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

//The view for the shotCounter, like all of the views it is a JPanel with a JLabel containing the image placed on top of it. Each shotCounter has its own view and they listen to whichever
//Room the panel is in. The constructor takes in the ShotCounterResources which just gets the image, an arraylist of integers for the area of the shotCounter and lastly an integer x which
//is set to be the panels number, each panel has a number so that only one is removed at a time. The background panel is not Opaque so as not to cause any obvious clipping issues and
//make the game look cleaner. The shot counters only show up when a player enters the room because there is no reason for them to be there beforehand.

public class ShotCounterView extends JPanel implements Room.Listener{

    private JLabel shotCounter;
    private ArrayList<Integer> area;
    private int number;

    public ShotCounterView(ShotCounterResources s, ArrayList<Integer> n, int x){
	super(null);
	setLayout(null);
	shotCounter = new JLabel();
	this.area=n;
	this.number=x;

	add(shotCounter, 0);
	shotCounter.setBounds(0, 0, n.get(2), n.get(3));
	setOpaque(false);
	setVisible(true);
    }

    public void enter(ArrayList<Integer> n){
	ShotCounterResources s = ShotCounterResources.getInstance();
	shotCounter.setIcon(s.getIcon());
	setBounds(area.get(0),area.get(1),area.get(2),area.get(3));
    }

    //listener for incrementing shots
    public void incrementShots(int n){
	if (n<=number){
	    setVisible(false);
	}
    }
    //listens for the scene to be reset at the end of the day
    public void reset(){
      setVisible(true);
    }

    //these listeners are not used in this file
    public void sendRoom(Room room){}
    public void over(){}
}
