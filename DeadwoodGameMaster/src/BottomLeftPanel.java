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

//This panel listens to the board and changes its color and message based on who's turn it is, for more information on how our panels work look at the SidePanel

public class BottomLeftPanel extends JPanel implements Board.Listener{

    private JPanel bottomLeftPanel;
    private JLabel label;
    Color[] backGroundColor = new Color[]{Color.blue, Color.orange, Color.green, Color.red, Color.yellow, Color.magenta, Color.pink, Color.cyan};

    public BottomLeftPanel(JFrame mainFrame, Board board) throws IOException{
	bottomLeftPanel = new JPanel();
	setLayout(null);
	setBounds(0, 900, 200, 200);
	setBackground(Color.black);
	label = new JLabel("yo");
	label.setBounds(40, 20, 150, 150);
	label.setForeground(Color.white);
	add(label);
	setVisible(true);
    }

    //listens to the board to change to the current player's turn
    public void currentPlayerID(int n){

	setBackground(backGroundColor[n-1]);
	label.setText("Player "+n+"\'s turn");
    }
    //this part of the listener interface is not used in this file
    public void currentPlayer(Player p){}
    public void incrementDay(){}
}
