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

//The sidePanel is one of our larger classes, it listens to all of the players in the game. It creates empty arrayLists of Jlabels for each stat displayed which are initialized
//inside the constructor. At the top there is a small JLabel that just displays that it is a stat panel in white text. For each player in the game we create a label for every attribute:
//name, score, rank, dollars, credits, rehearses on current space, and their last roll for acting. These are all added to the Jpanel and set inside the for loop, with each stat
//being incremented slightly downwards. We add these labels to the arraylists created earlier and set everything to visible. Whenever a player does something that changes any of their
//stats we update all of the stats for that player.

//The dice are for displayed the players last roll when acting, they do not take into account the times rehearsed so the players will have to add that to the dice to see their final roll.
//They all start at one and are not to be confused with the player dice on the board which are what shows the players rank (which is shown again in the stat panel).

public class SidePanel extends JPanel implements Player.Listener{

    private JPanel sidePanel;
    private ArrayList<JLabel> names;
    private ArrayList<JLabel> scores;
    private ArrayList<JLabel> ranks;
    private ArrayList<JLabel> dollars;
    private ArrayList<JLabel> credits;
    private ArrayList<JLabel> rehearses;
    private ArrayList<JLabel> lastRolls;
    private PlayerResources res;

    public SidePanel(JFrame mainFrame, ArrayList<Player> players) throws IOException {
	res = PlayerResources.getInstance();
	names = new ArrayList<JLabel>();
	scores = new ArrayList<JLabel>();
	ranks = new ArrayList<JLabel>();
	dollars = new ArrayList<JLabel>();
	credits = new ArrayList<JLabel>();
	rehearses = new ArrayList<JLabel>();
	lastRolls = new ArrayList<JLabel>();
	sidePanel = new JPanel();
	setLayout(null);
	setBounds(0, 0, 200, 900);
	Color[] backGroundColor = new Color[]{Color.decode("#3498db"), Color.orange, Color.green, Color.red, Color.yellow, Color.magenta, Color.pink, Color.cyan};
	setBackground(Color.decode("#0F2043"));

	JLabel statPanel = new JLabel("Stat Panel:");
	statPanel.setBounds(20,0,160,30);
	statPanel.setForeground(Color.white);
	add(statPanel);

	for (int i =0; i<players.size(); i++){
	    JLabel name = new JLabel("Player: "+players.get(i).getPid()+"");
	    name.setBounds(20,-70+(100*(i+1)),160,15);
	    name.setForeground(backGroundColor[i]);
	    names.add(name);

	    JLabel score = new JLabel("Score: "+players.get(i).getScore());
	    score.setBounds(20,-55+(100*(i+1)),160,15);
	    score.setForeground(backGroundColor[i]);
	    scores.add(score);

	    JLabel rank = new JLabel("Rank: "+players.get(i).getRank()+"");
	    rank.setBounds(20,-40+(100*(i+1)),160,15);
	    rank.setForeground(backGroundColor[i]);
	    ranks.add(rank);

	    JLabel dollar = new JLabel("Dollars: "+players.get(i).getDollars());
	    dollar.setBounds(20,-25+(100*(i+1)),160,15);
	    dollar.setForeground(backGroundColor[i]);
	    dollars.add(dollar);

	    JLabel credit = new JLabel("Credits: "+players.get(i).getCredits());
	    credit.setBounds(20,-10+(100*(i+1)),160,15);
	    credit.setForeground(backGroundColor[i]);
	    credits.add(credit);

	    JLabel rehearse = new JLabel("Times Rehearsed: "+players.get(i).getRehearseTokens());
	    rehearse.setBounds(20,5+(100*(i+1)),160,15);
	    rehearse.setForeground(backGroundColor[i]);
	    rehearses.add(rehearse);

	    JLabel lastRoll = new JLabel();
	    lastRoll.setBounds(100, -55+(100*(i+1)), 40, 40);
	    lastRoll.setIcon(res.getIcon(players.get(i).getPid(), players.get(i).getLastRoll()));
	    lastRolls.add(lastRoll);

	    add(name);
	    add(score);
	    add(rank);
	    add(dollar);
	    add(credit);
	    add(rehearse);
	    add(lastRoll);
	}
	setVisible(true);
    }
    //part of the player listener, not necessary to use for this so these methods remain empty
    public void playerMoved(Player p){}
    public void roleTaken(Player p){}

    //this gets called whenever the player does anything to change any of their stats, all of their stats are then updated on the stat panel.
    public void updateScore(Player p){
	res = PlayerResources.getInstance();
	int x = p.getPid()-1;
	scores.get(x).setText("Score: "+p.getScore());
	ranks.get(x).setText("Rank: "+p.getRank());
	dollars.get(x).setText("Dollars: "+p.getDollars());
	credits.get(x).setText("Credits: "+p.getCredits());
	rehearses.get(x).setText("Times Rehearsed: "+p.getRehearseTokens());
	lastRolls.get(x).setIcon(res.getIcon(p.getPid(), p.getLastRoll()));
    }
}
