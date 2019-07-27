// Each room has an instance of the Room class. Scene is added in accordingly on start game and hydrateSet()
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

//We just have one type of room, there are no special types for the casting office or the trailer, they just use different constructors as called by the ParseBoard. Each room has a list
//of neighbors, roles, an area for the scene card and for each take, and an additional area for players just sitting around who aren't on a role along with an arralist of players
//in the room. Every room has an arrayList of office upgrades but only the offices ever gets initialized, outside of that the casting office and trailer are just rooms with no values
//for their scnes, roles, takesArea, or shot counters.

public class Room{

    //the shot counter views and scenes listen to their respective rooms, have methods for when a player enters the room, when the scene is over, and for removing a shot counter
    public interface Listener{
	public void enter(ArrayList<Integer> n);
	public void over();
	public void incrementShots(int n);
  public void reset();
    }

    private List<Listener> listeners;
    private ArrayList<Room> neighbors = new ArrayList<Room>();
    private ArrayList<String> neighborHelper = new ArrayList<String>(); //neighbors as strings
    private ArrayList<Role> roles = new ArrayList<Role>();
    private ArrayList<Integer> area = new ArrayList<Integer>();
    private ArrayList<Upgrade> officeUpgrades = new ArrayList<Upgrade>();
    private ArrayList<ArrayList<Integer>> takesArea = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> blankSpace = new ArrayList<Integer>(); //where players sit w/o a role
    private ArrayList<Player> occupants = new ArrayList<Player>();
    private Scene _scene;
    public String _name;
    public int shotCounters;

    Room(String name, int shots, ArrayList<ArrayList<Integer>> takesArea, ArrayList<Role> r, ArrayList<String> h, ArrayList<Integer> a, ArrayList<Integer> b){
      	listeners = new LinkedList<Listener>();
	_name = name;
	shotCounters = shots;
	this.roles=r;
	this.neighborHelper=h;
	this.area=a;
	this.takesArea=takesArea;
	this.blankSpace=b;
    }
    //constructor for the office
    Room(String name, ArrayList<String> h, ArrayList<Upgrade> o,  ArrayList<Integer> b){
	this._name="Casting Office";
	this.neighborHelper=h;
	this.officeUpgrades=o;
	this.blankSpace=b;
    }
    //constructor for the trailer
    Room(String name, ArrayList<String> h,  ArrayList<Integer> b){
	this._name="Trailers";
	this.neighborHelper=h;
	this.blankSpace=b;
    }

    public synchronized void addListener(Listener l){
	listeners.add(l);
    }
    private synchronized void playerEnter(){
	if (_scene !=null){
	    for (Listener l : listeners){
		l.enter(area);
	    }
	}
    }
    private synchronized void sceneOver(){
	for (Listener l : listeners){
	    l.over();
	}
    }
    private synchronized void shotListener(){
	for (Listener l: listeners){
	    l.incrementShots(shotCounters);
	}
    }
    private synchronized void newScene(){
  for (Listener l: listeners){
      l.reset();
  }
    }
    //lets the listeners know that a player entered the room
    public void addPlayer(Player p){
	occupants.add(p);
	playerEnter();
    }
    public void removePlayer(Player p){
	occupants.remove(p);
    }

    //adds a role to this room
    public void addRole(int rank, ArrayList<Integer> r, String name, String line){
	Role role = new Role(rank, r, name, line);
	roles.add(role);
    }

    public String getName(){
	return _name;
    }

    public void addNeighbor(Room room){
	neighbors.add(room);
    }
    //assigns a scene to this room
    public void placeScene(Scene scene){
	_scene = scene;
  newScene();
    }

    // Parses all neighbors in the neighbors arraylist and returns 0 (no) or 1 (yes) if neighbor or not
    public int isNeighbor(Room room){
	Room temproom = neighbors.get(0);
	int i = 0;
	int is_neighbor = 0;
	while(i < neighbors.size()){
	    if(neighbors.get(i).getName().equals(room.getName())){
		is_neighbor = 1;
	    }
	    else{
		temproom = neighbors.get(i);
	    }
	    i++;
	}
	return is_neighbor;
    }

    //lets the listeners know a shot has been removed and removes a shot from the shotcounter
    public void removeShot(){
	shotCounters--;
	shotListener();
    }
    //lets the listeners know the scene is over and sets the current scene assigned to the room to null;
    public void wrapScene(){
	sceneOver();
	_scene = null;
    }

    //the rest of this file is just get methods to get the various variables belonging to the room

    public ArrayList<Integer> getShotArea(int n){
	return this.takesArea.get(n);
    }

    public Scene getScene(){
	return _scene;
    }

    public int getNumberOfRoles(){
	return (roles.size());
    }

    public ArrayList<Role> getRoles(){
	return roles;
    }

    public int getShotCounters(){
	return shotCounters;
    }

    public ArrayList<Integer> getArea(){
	return area;
    }

    public ArrayList<Upgrade> getUpgrades(){
	return officeUpgrades;
    }

    public Role getSpecificRole(String roleName){
	int i = 0;
	Role role = null;
	while(i < roles.size()){
	    if(roles.get(i).getName().equals(roleName)){
		role = roles.get(i);
	    }
	}
	return role;
    }

    public ArrayList<Integer> getBlankSpace(){
	return this.blankSpace;
    }

    // Returns the Strings of neighboring rooms
    public ArrayList<String> getNeighborHelper(){
	return neighborHelper;
    }
}
