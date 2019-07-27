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

public class Deadwood{

    private static Executor UI_Executor;
    private static Executor Game_Executor;

    /* A gateway to start the program. Captures number of players and instantiates the singleton
       Board object that is passed around through the rest of the classes. */
    public static void main(String args[]) throws IOException{
	if(args.length != 1 || Integer.parseInt(args[0]) < 2 || Integer.parseInt(args[0]) > 8){
	    System.out.println("Please specify a correct number of players");
	}
	else{
	    UI_Executor = Executors.newSingleThreadExecutor();
	    Game_Executor = Executors.newSingleThreadExecutor();
	    int num_players = Integer.parseInt(args[0]);
	    Board board = new Board(num_players);

	    setupGame(num_players, board);
	    setupScenes(args, board);

	    // Sets up special rules for number of players
	    if (num_players==5){
		for (int i=0; i<num_players+1; i++){
		    board.getPlayer(i).addCurrency(0, 2);
		}
	    }
	    else if (num_players==6){
		for (int i=0; i<num_players+1; i++){
		    board.getPlayer(i).addCurrency(0, 4);
		}
	    }
	    else if (num_players == 7 || num_players==8){
		for (int i=0; i<num_players+1; i++){
		    board.getPlayer(i).setRank(2);
		}
	    }

	    createGUI(board);
	    GameKeeper.startGame(board);
	}
    }

    // Starts the GUI setup, returns nothing.
    // Passes the single board object to DeadwoodGUI for it to use on panel
    // creation.
    private static void createGUI(Board board) throws IOException{
	DeadwoodGUI dw = new DeadwoodGUI(board);
    }

    // Adds players to Board object and calls setupRooms()
    private static void setupGame(int num_players, Board board){
	int i = 0;
	while(i < num_players){ //Adding players to board object
	    Player new_player = new Player(i+1);
	    board.addPlayer(new_player);
	    i++;
	}
	setupRooms(board);
    }

    /* Creates all room objects, adds them to board object, and
       also creates the room neighbors relationships for each
       room. Hard coded, not read through XMLReader */
    private static void setupRooms(Board board){
	ParseBoard parsableBoard = new ParseBoard(board);
	board.addNeighbors();
    }

    /* Calls XmlReader to add scenes to Board unused_scenes arraylist to be used
       when hydrating sets*/
    private static void setupScenes(String[] args, Board board){
	ParseCard parsableCard = new ParseCard(board);
    }
}
