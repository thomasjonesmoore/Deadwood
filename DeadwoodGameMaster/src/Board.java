import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

/* The Board object holds all the players, rooms, used/unused scenes,
   and number of players. It does not keep track of which player is in each room (Player object
   does this). After the game is setup, this object is passed to GameKeeper.java in order to
   properly run the game. */
public class Board{

    public interface Listener {
	public void currentPlayerID(int n);
  public void currentPlayer(Player p);
  public void incrementDay();
    }

    private int num_players;
    private ArrayList<Player> player_list = new ArrayList<Player>();
    private ArrayList<Room> room_list = new ArrayList<Room>();
    private ArrayList<Scene> used_scenes = new ArrayList<Scene>();
    private ArrayList<Scene> unused_scenes = new ArrayList<Scene>();
    private int numWrappedScenes;
    private Player currentPlayer;
    private int currentPlayerID;
    private List<Listener> listeners;

    Board(int numPlayers){
	num_players = numPlayers;
	numWrappedScenes = 0;
	listeners = new LinkedList<Listener>();
    }

    public ArrayList<Scene> getAllScenes(){
	return unused_scenes;
    }

    // Adds listeners to board
    public synchronized void addListener(Listener l){
	listeners.add(l);
    }

    private synchronized void sendPlayerID(){
	for (Listener l : listeners){
	    l.currentPlayerID(currentPlayerID);
	}
    }
    private synchronized void sendPlayer(){
  for (Listener l : listeners){
      l.currentPlayer(currentPlayer);
  }
    }
    private synchronized void updateDay(){
  for (Listener l : listeners){
      l.incrementDay();
  }
    }

    public void setCurrentPlayerID(int i){
	if(i <= player_list.size()){
	    this.currentPlayerID=i;
	}
	else{
	    this.currentPlayerID = 1;
	}
  setCurrentPlayer();
	sendPlayerID();
    }
    public void setCurrentPlayer(){
	for (int i =0; i < player_list.size(); i++){

	    if (player_list.get(i).getPid()==currentPlayerID){
		currentPlayer=player_list.get(i);
    	sendPlayer();
	    }
	}
    }
    public Player getCurrentPlayer(){
	return currentPlayer;
    }
    public int getCurrentPlayerID(){
	return this.currentPlayerID;
    }
    public void addPlayer(Player player){
	player_list.add(player);
    }

    public void addRoom(Room room){
	room_list.add(room);
    }

    public void addNeighbors(){
	int j = 0;
	Room room;
	while(j < room_list.size()){
	    room  = room_list.get(j);
	    int i = 0;
	    while(i < room.getNeighborHelper().size()){
		room.addNeighbor(getRoom(room.getNeighborHelper().get(i)));
		i++;
	    }
	    room = room_list.get(j);
	    j++;
	}
    }

    // 1. get scenes from unused_scenes and hydrate the sets with them
    // 2. move used scenes to used_scenes array by calling updateScenes
    public void hydrateSets(){
	int i = 0;
	while(i < room_list.size()){
	    if(!room_list.get(i).getName().equals("Casting Office") &&
	       !room_list.get(i).getName().equals("Trailers")){
		room_list.get(i).placeScene(unused_scenes.get(i));
		room_list.get(i).addListener(unused_scenes.get(i));
		updateScenes(unused_scenes.get(i));
	    }
	    i++;
	}
    }

    // Removes scene from unused_scenes and adds it to used_scenes
    private void updateScenes(Scene scene){
	used_scenes.add(scene);
	unused_scenes.remove(scene);
    }

    // Used on game startup and new days
    public void addScene(Scene scene){
	unused_scenes.add(scene);
    }

    // pid number is 1 <= pid <= total_num_players
    public Player getPlayer(int pid){
	Player temp = player_list.get(0);
	int i = 0;
	while(i < player_list.size()){
	    if(player_list.get(i).getPid() == pid){
		temp = player_list.get(i);
		break;
	    }
	    i++;
	}
	return temp;
    }

    public int getNumPlayers(){
	return num_players;
    }

    /* This function is so we can get the player_list size without actually returning
       the player list */
    public int getPlayerListSize(){
	return (player_list.size());
    }
    public ArrayList<Player> getPlayers(){
	return this.player_list;
    }

    /* Call on game startup and on new days */
    public Room getTrailers(){
	Room temp = room_list.get(0);
	int i = 0;
	while(i < room_list.size()){
	    if(room_list.get(i).getName().equals("Trailers")){
		temp = room_list.get(i);
	    }
	    i++;
	}
	return temp;
    }

    // Returns the room specified
    public Room getRoom(String desired_room){
	Room temp = room_list.get(0);
	int i = 0;
	String d_room = "";
	if(desired_room.equals("office")){
	    d_room += "Casting Office";
	}
	else if(desired_room.equals("trailer")){
	    d_room += "Trailers";
	}
	else{
	    d_room = desired_room;
	}
	while(i < room_list.size()){
	    if(room_list.get(i).getName().equals(d_room)){
		temp = room_list.get(i);
	    }
	    i++;
	}
	return temp;
    }

    public void wrapScene(){
	numWrappedScenes++;
  updateDay();
    }

    public int numWrappedScenes(){
	return numWrappedScenes;
    }

    public ArrayList<Room> getRoomList(){
	return room_list;
    }
}
