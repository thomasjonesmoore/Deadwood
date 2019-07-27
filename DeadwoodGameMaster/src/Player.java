import java.util.LinkedList;
import java.util.List;
/* Each Player has its own player class. Keeps track of current role, current room, currency,
   and rank. */

public class Player{

    public interface Listener {
	public void playerMoved(Player p);
	public void roleTaken(Player p);
	public void updateScore(Player p);
    }
    private int pid;
    private int dollars;
    private int credits;
    private int rank;
    private Room _room;
    private int rehearseTokens;
    private Role _role;
    private int roleType; // 0 for room(side role), and 1 for scene(main role)
    private boolean justMoved;
    private boolean actionUsed;
    private List<Listener> listeners;
    private int score=dollars+credits+(5*rank);
    private int lastRoll;


    Player(int p_id){
	listeners = new LinkedList<Listener>();
	pid = p_id;
	dollars = 0;
	credits = 0;
	rank = 1;
	rehearseTokens = 0;
	justMoved = false;
	actionUsed = false;
	lastRoll=1;
    }

    public synchronized void addListener(Listener l){
	listeners.add(l);
    }
    private synchronized void sendChangeMove(){
	for (Listener l : listeners){
	    l.playerMoved(this);
	}
    }
    private synchronized void sendChangeRole(){
	for (Listener l : listeners){
	    l.roleTaken(this);
	}
    }

    private synchronized void sendUpdate(){
	for (Listener l : listeners){
	    l.updateScore(this);
	}
    }

    public void useAction(){
	actionUsed = true;
    }

    public void move(){
	justMoved = true;
	sendChangeMove();
    }
    // Since Player object keeps track of room, updateRoom()
    public void updateRoom(Room room){
  _room = room;
  sendChangeMove();
    }

    // Adds currency to player fields
    public void addCurrency(int money_type, int amount){
	if(money_type == 0){
	    dollars += amount;
	}
	else{
	    credits += amount;
	}
	sendUpdate();
    }

    public void rehearse(){
	rehearseTokens++;
	sendUpdate();
    }

    //checks if the player succeeds at acting and sends an update to its listeners
    public boolean act(int roll, int budget){
  boolean acting = false;
  if ((roll+rehearseTokens)>=budget){
      acting=true;
  }
  this.lastRoll=roll;
  sendUpdate();
  return acting;
    }
    public void updateRankAndMoney(int new_rank, int money_type){
	rank = new_rank;

	if(new_rank == 2 && money_type == 0){
	    dollars = dollars - 4;
	}
	else if(new_rank == 2 && money_type == 1){
	    credits = credits - 5;
	}
	else if(new_rank == 3 && money_type == 0){
	    dollars = dollars - 10;
	}
	else if(new_rank == 3 && money_type == 1){
	    credits = credits - 10;
	}
	else if(new_rank == 4 && money_type == 0){
	    dollars = dollars - 18;
	}
	else if(new_rank == 4 && money_type == 1){
	    credits = credits - 15;
	}
	else if(new_rank == 5 && money_type == 0){
	    dollars = dollars - 28;
	}
	else if(new_rank == 5 && money_type == 1){
	    credits = credits - 20;
	}
	else if(new_rank == 6 && money_type == 0){
	    dollars = dollars - 40;
	}
	else if(new_rank == 6 && money_type == 1){
	    credits = credits - 25;
	}
    }


    //These four methods just reset various attributes of the Player

    public void resetAction(){
  actionUsed = false;
    }

    public void resetMove(){
  justMoved = false;
    }
    public void resetRehearseTokens(){
  rehearseTokens = 0;
  sendUpdate();
    }
    // Used when scene is wrapped or day is over
    public void resetRole(){
  _role = null;
  rehearseTokens=0;
  roleType = 0;
  sendUpdate();
    }

    //These three methods just set various attributes of the Player

    public void setRole(Role role){
	_role = role;
  actionUsed=true;
	sendChangeRole();
    }

    public void setRoleType(int type){
	roleType = type;
    }

    public void setRank(int n){
  this.rank=n;
    }


    //The rest of this file is just methods to get the various variables stored in Player

    public int getRoleType(){
	return roleType;
    }

    public Role getRole(){
  return _role;
    }

    public int getRehearseTokens(){
  return rehearseTokens;
    }

    public Room getCurrentRoom(){
  return _room;
    }
    public int getPid(){
	return pid;
    }

    public int getDollars(){
	return dollars;
    }

    public int getCredits(){
	return credits;
    }

    public int getRank(){
	return rank;
    }

    //returns the players score
    public int getScore(){
	return this.score;
    }

    public int getLastRoll(){
	return this.lastRoll;
    }
    public boolean getJustMoved(){
	return justMoved;
    }

    public boolean getActionUsed(){
	return actionUsed;
    }


}
