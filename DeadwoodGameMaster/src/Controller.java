import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.util.concurrent.*;
import java.awt.Color;

public class Controller extends JPanel {

    private Board board;
    private Executor executor;
    
    public Controller(Board b){
	board = b;
	executor = Executors.newSingleThreadExecutor();	
    }

    // Act click. Calls actInput on GameKeeper with current player
    public void actClick(){
	executor.execute(() -> {
		if(GameKeeper.isCommandLegal(board, board.getCurrentPlayerID(), "act") == true){
		    GameKeeper.actInput("", board.getCurrentPlayerID(), board);
		}		
	    });
    }

    // Rehearse click. Calls rehearseinput from gamekeeper on current player
    public void rehearseClick(){
	executor.execute(() -> {
		if(GameKeeper.isCommandLegal(board, board.getCurrentPlayerID(), "rehearse") == true){
		    GameKeeper.rehearseInput("", board.getCurrentPlayerID(), board);		    
		}		
	    });
    }

    // End turn click. Updates player turn in the board
    public void endClick(){
	executor.execute(() -> {
		board.getPlayer(board.getCurrentPlayerID()).resetMove();
		board.getPlayer(board.getCurrentPlayerID()).resetAction();
		board.setCurrentPlayerID(board.getCurrentPlayerID() + 1);		
	    });
    }

    // When the 1st dollar panel is clicked in Casting Office.
    // Checks if upgrade is valid, then updates the players money and rank.
    public void d1Click(){
	executor.execute(() -> {
		if(GameKeeper.getPlayerFunds(board.getPlayer(board.getCurrentPlayerID()), 0, 2) == 1
		   && board.getPlayer(board.getCurrentPlayerID()).getCurrentRoom().getName().equals("Casting Office")){
		    board.getPlayer(board.getCurrentPlayerID()).setRank(2);
		    board.getPlayer(board.getCurrentPlayerID()).addCurrency(0, -4);
		}
	    });

    }

    // When the 2nd dollar panel is clicked in Casting Office.
    // Checks if upgrade is valid, then updates the players money and rank.
    public void d2Click(){
	executor.execute(() -> {
		if(GameKeeper.getPlayerFunds(board.getPlayer(board.getCurrentPlayerID()), 0, 3) == 1
		   && board.getPlayer(board.getCurrentPlayerID()).getCurrentRoom().getName().equals("Casting Office")){
		    board.getPlayer(board.getCurrentPlayerID()).setRank(3);
		    board.getPlayer(board.getCurrentPlayerID()).addCurrency(0, -10);
		}
	    });
    }

    // When the 3rd dollar panel is clicked in Casting Office.
    // Checks if upgrade is valid, then updates the players money and rank.
    public void d3Click(){
	executor.execute(() -> {
		if(GameKeeper.getPlayerFunds(board.getPlayer(board.getCurrentPlayerID()), 0, 4) == 1
		   && board.getPlayer(board.getCurrentPlayerID()).getCurrentRoom().getName().equals("Casting Office")){
		    board.getPlayer(board.getCurrentPlayerID()).setRank(4);
		    board.getPlayer(board.getCurrentPlayerID()).addCurrency(0, -18);
		}
	    });
    }

    // When the 4th dollar panel is clicked in Casting Office.
    // Checks if upgrade is valid, then updates the players money and rank.
    public void d4Click(){
	executor.execute(() -> {
		if(GameKeeper.getPlayerFunds(board.getPlayer(board.getCurrentPlayerID()), 0, 5) == 1
		   && board.getPlayer(board.getCurrentPlayerID()).getCurrentRoom().getName().equals("Casting Office")){
		    board.getPlayer(board.getCurrentPlayerID()).setRank(5);
		    board.getPlayer(board.getCurrentPlayerID()).addCurrency(0, -28);
		}
	    });
    }

    // When the 5th dollar panel is clicked in Casting Office.
    // Checks if upgrade is valid, then updates the players money and rank.
    public void d5Click(){
	executor.execute(() -> {
		if(GameKeeper.getPlayerFunds(board.getPlayer(board.getCurrentPlayerID()), 0, 6) == 1
		   && board.getPlayer(board.getCurrentPlayerID()).getCurrentRoom().getName().equals("Casting Office")){
		    board.getPlayer(board.getCurrentPlayerID()).setRank(6);
		    board.getPlayer(board.getCurrentPlayerID()).addCurrency(0, -40);
		}
	    });
    }

    // When the 1st credit panel is clicked in Casting Office.
    // Checks if upgrade is valid, then updates the players money and rank.
    public void c1Click(){
	executor.execute(() -> {
		if(GameKeeper.getPlayerFunds(board.getPlayer(board.getCurrentPlayerID()), 1, 2) == 1
		   && board.getPlayer(board.getCurrentPlayerID()).getCurrentRoom().getName().equals("Casting Office")){
		    board.getPlayer(board.getCurrentPlayerID()).setRank(2);
		    board.getPlayer(board.getCurrentPlayerID()).addCurrency(1, -5);
		}
	    });
    }

    
    // When the 2nd credit panel is clicked in Casting Office.
    // Checks if upgrade is valid, then updates the players money and rank.
    public void c2Click(){
	executor.execute(() -> {
		if(GameKeeper.getPlayerFunds(board.getPlayer(board.getCurrentPlayerID()), 1, 3) == 1
		   && board.getPlayer(board.getCurrentPlayerID()).getCurrentRoom().getName().equals("Casting Office")){
		    board.getPlayer(board.getCurrentPlayerID()).setRank(3);
		    board.getPlayer(board.getCurrentPlayerID()).addCurrency(1, -10);
		}
	    });
    }

    // When the 3rd credit panel is clicked in Casting Office.
    // Checks if upgrade is valid, then updates the players money and rank.
    public void c3Click(){
	executor.execute(() -> {
		if(GameKeeper.getPlayerFunds(board.getPlayer(board.getCurrentPlayerID()), 1, 4) == 1
		   && board.getPlayer(board.getCurrentPlayerID()).getCurrentRoom().getName().equals("Casting Office")){
		    board.getPlayer(board.getCurrentPlayerID()).setRank(4);
		    board.getPlayer(board.getCurrentPlayerID()).addCurrency(1, -15);
		}
	    });
    }

    // When the 4th credit panel is clicked in Casting Office.
    // Checks if upgrade is valid, then updates the players money and rank.
    public void c4Click(){
	executor.execute(() -> {
		if(GameKeeper.getPlayerFunds(board.getPlayer(board.getCurrentPlayerID()), 1, 5) == 1
		   && board.getPlayer(board.getCurrentPlayerID()).getCurrentRoom().getName().equals("Casting Office")){
		    board.getPlayer(board.getCurrentPlayerID()).setRank(5);
		    board.getPlayer(board.getCurrentPlayerID()).addCurrency(1, -20);
		}
	    });
    }

    // When the 5th credit panel is clicked in Casting Office.
    // Checks if upgrade is valid, then updates the players money and rank.
    public void c5Click(){
	executor.execute(() -> {
		if(GameKeeper.getPlayerFunds(board.getPlayer(board.getCurrentPlayerID()), 1, 6) == 1
		   && board.getPlayer(board.getCurrentPlayerID()).getCurrentRoom().getName().equals("Casting Office")){
		    board.getPlayer(board.getCurrentPlayerID()).setRank(6);
		    board.getPlayer(board.getCurrentPlayerID()).addCurrency(1, -25);
		}
	    });
    }

    // When a room is clicked, sent to this function with the roomName.
    // Then looks up roomName and calls a function in GameKeeper to set
    // the player room after doing a serious of checks.     
    public void roomClick(String roomName){
	executor.execute(() -> {
		if(GameKeeper.isCommandLegal(board, board.getCurrentPlayerID(), "move") == true){
		    String cmd = "move "+ roomName;
		    if(GameKeeper.isCommandLegal(board, board.getCurrentPlayerID(), cmd) == true){
			GameKeeper.moveInput(board.getRoom(roomName).getName(), board.getCurrentPlayerID(), board);
		    }
		}
	    });
    }

    // When a role is clicked, sent to this function with the roleName.
    // Then looks up roleName and calls a function in GameKeeper to set
    // the player role after doing a serious of checks. 
    public void roleClick(String roleName){
	executor.execute(() -> {
		try{
		    if(GameKeeper.isCommandLegal(board, board.getCurrentPlayerID(), "work") == true){
			String cmd = "work " + roleName;
			if(GameKeeper.isCommandLegal(board, board.getCurrentPlayerID(), cmd) == true){
			    GameKeeper.workInput(roleName, board.getCurrentPlayerID(), board);
			}
		    }
		}
		catch(Exception e){
		}
	    });	
    }
}
