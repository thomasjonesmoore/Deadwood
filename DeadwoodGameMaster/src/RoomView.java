import java.util.LinkedList;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.awt.Color;

public class RoomView extends JPanel{

    public interface Listener{
	public void enter();
	public void leave();
    }
    
    private int occupants;
    private List<Listener> listeners;

    // Notified when a player enters or leaves a room and lets
    // the listeners know when a player enters or leaves the
    // respective RoomView. There is one RoomView for each
    // room. 
    RoomView(){	
	occupants = 0;
	listeners = new LinkedList<Listener>();
    }

    
    public void addOccupant(){
	occupants++;
    }

    public void removeOccupant(){
	occupants--;
    }

    public synchronized void addListener(Listener l){
	listeners.add(l);
    }

    public synchronized void enter(){
	for(Listener l : listeners){
	    l.enter();
	}
    }

    public synchronized void leave(){
	for(Listener l : listeners){
	    l.leave();
	}
    }
}
