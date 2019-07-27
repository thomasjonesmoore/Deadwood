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

public class DayKeeper implements Board.Listener{

    Board board;
    int day;

    public DayKeeper(Board board){
	this.board = board;
	day = 1;	
    }

    // Function taken from interface, listening to board.
    // Resets players back to trailers, hydrates sets with
    // new scene cards, and resets currentplayerID
    public void incrementDay(){
	if(board.numWrappedScenes() == 9){
	    day++;
	    resetPlayers(board);
	    board.hydrateSets();
	    board.setCurrentPlayerID(1);
	}
    }
    
    /* Used on game startup and new days. Puts players back in Trailers,                        
       resets each players rehearse tokens, and resets all roles. */
    private void resetPlayers(Board board){
	int pnum = 1;
	int i = 0;
	while(i < board.getPlayerListSize()){
	    board.getPlayer(pnum).updateRoom(board.getTrailers());
	    board.getPlayer(pnum).getCurrentRoom().addPlayer(board.getPlayer(pnum));
	    board.getPlayer(pnum).resetRehearseTokens();
	    board.getPlayer(pnum).resetRole();
	    board.getPlayer(pnum).resetAction();
	    board.getPlayer(pnum).resetMove();
	    pnum++;
	    i++;
	}
    }
        
    // Not used
    public void currentPlayerID(int n){}
    public void currentPlayer(Player p){};
}
