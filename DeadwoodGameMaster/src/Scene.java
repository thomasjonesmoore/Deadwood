/* Parsed in from XmlReader. Each scene is assigned roles. Getter methods are used to
   provide encapsulation from user. */
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

//Each scene listens to the room they are placed in, if a scene is not currently active on the board it will listen to nothing.

public class Scene implements Room.Listener{

    //the scene views will listen to the scenes
    public interface Listener{
	public void showScene(Scene s);
	public void closeScene(Scene s);
    }

    private List<Listener> listeners;
    private String _name;
    private ArrayList<Role> _roles;
    private int _budget;
    private String line;
    private int number;
    private String image;
    private int num_roles;
    private ArrayList<Integer> area;
    private Room _room;

    //the base variables of a scene as gathered from the ParseCard reader reading the card.xml file
    Scene(String name, String l, int n, String i, ArrayList<Role> roles, int budget){
	listeners = new LinkedList<Listener>();
	_name = name;
	_roles = roles;
	_budget = budget;
	this.line=l;
	this.number =n;
	this.image=i;
	num_roles = roles.size();
	_room = null;
    }

    //adds listeners
    public synchronized void addListener(Listener l){
	listeners.add(l);
    }
    //lets the listeners know when someone enters the room and the scene card should be flipped over
    private synchronized void enterListener(){
	for (Listener l : listeners){
	    l.showScene(this);
	}
    }
    //lets the listeners know when the scene is over and the card goes to the "discard pile"
    private synchronized void overListener(){
	for (Listener l : listeners){
	    l.closeScene(this);
	}
    }

    //methods to get various variables
    public String getName(){
	return _name;
    }

    public int getNumberOfRoles(){
	return (_roles.size());
    }

    public ArrayList<Role> getRoles(){
	return _roles;
    }

    public int getBudget(){
	return _budget;
    }

    public int getNumRoles(){
	return num_roles;
    }

    public int getNumber(){
	return this.number;
    }

    public ArrayList<Integer> getArea(){
	return this.area;
    }

    //the room lets its listeners know when a player enters the room and the scene passes on this information to its listeners
    public void enter(ArrayList<Integer> n){
	this.area=n;
	enterListener();
    }

    //when the rooms shot counters are depleted it tells the scene that it is over and the scene tells its listeners that it is over
    public void over(){
	overListener();
    }

    //room listener methods that are not used here
    public void incrementShots(int n){}
    public void reset(){}
}
