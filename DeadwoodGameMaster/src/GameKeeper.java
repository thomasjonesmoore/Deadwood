import java.util.*;

public class GameKeeper{

    /* Called once from Deadwood object. The outer while loop keeps track of if the game
       is over, and the inner while loop keeps track of the number of days. Turn keeps
       track of the current turn and cycles back to 1 when it goes over the number of
       players. When game is over, calculateWinner() is called to print the winner.*/
    public static void startGame(Board board){
	System.out.println("Get ready to rumble! Starting game!");
	int day = 1;

	if (board.getNumPlayers()<=3){
	    while (day <=3){
		dayKeeper(board);
		day++;
	    }
	}
	else{
	    while(day <= 4){
		System.out.println("Day: "+day);
		dayKeeper(board);
		day++;
	    }
	}


	System.out.println("Game is over!");
	calculateWinner(board);
    }

    // Keeps track of days. Ends game when max number of days
    // is met. Returns nothing.
    public static void dayKeeper(Board board){
	int turn = 1;
	resetPlayers(board);
	hydrateSets(board);
	board.setCurrentPlayerID(turn);
	while(isEndOfDay(board) == 0){
	    if(turn > board.getNumPlayers()){
		turn = 1;
		board.setCurrentPlayerID(turn);
	    }
	    listener(board, turn);
	    board.getPlayer(turn).resetMove();
	    board.getPlayer(turn).resetAction();
	    turn++;
	    board.setCurrentPlayerID(turn);
	}
    }

    // Updates all sets with new scenes
    private static void hydrateSets(Board board){
	board.hydrateSets();
    }

    private static int updateTurn(int turn){
	return(turn+1);
    }

    /* While the end command has not been given, the current player can do whatever
       they want by inputting commands. */
    public static void listener(Board board, int turn){
	String command="";
	Scanner sc;
	int turnEnd = 0;
	while(turnEnd == 0){
	    printTurnOptions(board, turn);
	    sc = new Scanner(System.in);
	    command = sc.nextLine();
	    turn = board.getCurrentPlayerID();
	    turnEnd = (inputAdmin(command, turn, board));
	}
    }

    // Prints the available options for the player to do on their turn
    private static void printTurnOptions(Board board, int turn){
	Player player = board.getPlayer(turn);
	if(player.getJustMoved() == false && player.getActionUsed() == false){
	    if(player.getCurrentRoom().getName().equals("Casting Office")){
		System.out.println("end | who | where | move [room name] | rehearse |"+
				   "act | upgrade [type] [rank] | work [role name]");
	    }
	    else{
		System.out.println("end | who | where | move [room name] | rehearse |"+
				   "act | work [role name]");
	    }
	}
	else if((player.getJustMoved() == false && player.getActionUsed() == true) ||
		(player.getJustMoved() == true && player.getActionUsed() == true)){
	    System.out.println("end | who | where");
	}
	else if(player.getJustMoved() == true && player.getActionUsed() == false){
	    if(player.getCurrentRoom().getName().equals("Casting Office")){
		System.out.println("end | who | where | upgrade [type] [rank] | work [role name]");
	    }
	    else{
		System.out.println("end | who | where | work [role name]");
	    }
	}
    }

    // returns(?) the available options for the player to do on their turn
    public static boolean isCommandLegal(Board board, int turn, String command){
	Player player = board.getPlayer(turn);
	boolean valid = false;
	if(player.getRole() != null){
	    if(command.contains("act") || command.contains("rehearse") || command.contains("end")){
		valid = true;
	    }
	}
	else if(player.getJustMoved() == false && player.getActionUsed() == false){
	    if(player.getCurrentRoom().getName().equals("Casting Office")){
		if(command.contains("end") || command.contains("who") || command.contains("where") ||
		   command.contains("move") || command.contains("upgrade")){
		    valid = true;
		}
	    }
	    else{
		if(command.contains("end") || command.contains("who") || command.contains("where") ||
		   command.contains("move") || command.contains("rehearse") || command.contains("work") ||
		   command.contains("act")){
		    valid = true;
		}
	    }
	}
	else if((player.getJustMoved() == false && player.getActionUsed() == true) ||
		(player.getJustMoved() == true && player.getActionUsed() == true)){
	    if(command.contains("end") || command.contains("who") || command.contains("where")){
		valid = true;
	    }
	}
	else if(player.getJustMoved() == true && player.getActionUsed() == false){
	    if(player.getCurrentRoom().getName().equals("Casting Office")){
		if(command.contains("end") || command.contains("who") || command.contains("where") ||
		   command.contains("upgrade") || command.contains("work")){
		    valid = true;
		}
	    }
	    else{
		if(command.contains("end") || command.contains("who") || command.contains("where") ||
		   command.contains("work")){
		    valid = true;
		}
	    }
	}
	return valid;
    }

    /* Responsible for handling all the input strings on a players turn. If the command
       is not recognized, a 'bad input' message is printed out. */
    public static int inputAdmin(String command, int turn, Board board){
	int turnEnd = 0;

	if(command.equals("end")){
	    turnEnd = 1;
	    System.out.println("Player " + turn+" turn is over.");
	}
	else if(command.equals("who")){
	    whoInput(command, turn, board);
	}
	else if(command.equals("where")){
	    whereInput(command, turn, board);
	}
	else if(command.contains("move")){
	    if(isCommandLegal(board, turn, command) == true){
		moveInput(command, turn, board);
	    }
	}
	else if(command.equals("rehearse")){
	    if(isCommandLegal(board, turn, command) == true){
		rehearseInput(command, turn, board);
	    }
	}
	else if(command.equals("act")){
	    if(isCommandLegal(board, turn, command) == true){
		actInput(command, turn, board);
	    }
	}
	else if(command.contains("upgrade")){
	    if(isCommandLegal(board, turn, command) == true){
		upgradeInput(command, turn, board);
	    }
	}
	else if(command.contains("work")){
	    if(isCommandLegal(board, turn, command) == true){
		workInput(command, turn, board);
	    }
	}
	else{
	    System.out.println("Command not recognized, please try again. ");
	}
	return(turnEnd);
    }

    /* Used on game startup and new days. Puts players back in Trailers,
       resets each players rehearse tokens, and resets all roles. */
    private static void resetPlayers(Board board){
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

    // Returns String of room name from command line when 'move' is called
    private static String getDesiredRoom(String command){
	String temp = "";
	String[] tokens = command.split(" ");
	if(tokens.length > 2){
	    temp += tokens[1] + " " + tokens[2];
	}
	else if(tokens.length == 2){
	    temp += tokens[0] + " " + tokens[1];
	}
	else{
	    temp += tokens[0];
	}
	return temp;
    }

    // returns 0 for dollars, 1 for credits
    private static int getMoneyType(String command){
	int type = 0;
	String temp = "";
	String[] tokens = command.split(" ");
	temp += tokens[1];
	if(temp.equals("$")){
	    type = 0;
	}
	else if(temp.equals("cr")){
	    type = 1;
	}
	else{
	    System.out.println("Bad money type. You messed things up. Everything is messed up now. Nice.");
	}
	return type;
    }

    // Returns new desired rank from when 'upgrade' is called
    private static int getNewRank(String command){
	int type;
	String temp = "";
	String[] tokens = command.split(" ");
	temp += tokens[2];
	type = Integer.parseInt(temp);

	return type;
    }

    // Rolls dice, returns roll
    private static int rollDice(int numberOfDice){
	Random rand = new Random();
	int roll = rand.nextInt(6)+1;
	return roll;
    }

    // Returns the String name of the desired role when 'work' is called
    private static String getDesiredRoleString(String command){
	String temp = "";
	String[] tokens = command.split(" ");

	if(tokens.length == 3){
	    temp += tokens[0] + " " +tokens[1] + " " + tokens[2];
	}
	else if(tokens.length == 4){
	    temp += tokens[0] + " " + tokens[1] + " " + tokens[2] + " " + tokens[3];
	}
	else if(tokens.length == 5){
	    temp += tokens[0] + " " + tokens[1] + " " + tokens[2] + " " + tokens[3] + " " + tokens[4];
	}
	else if(tokens.length == 2){
	    temp += tokens[0] + " " + tokens[1];
	}
	else
	    temp += tokens[0];
	return temp;
    }

    /* Parses through room roles then scene roles for the room that the player is in and
       returns the role once it is found. If it is never found, an error message is printed
       out. */
    private static Role findRole(String desired_role, Player p){
	Role role = null;
	int found = 0;
	Room room = p.getCurrentRoom();
	for(int i = 0; i < room.getNumberOfRoles(); i++){
	    if(room.getRoles().get(i).getName().equals(desired_role)){
		role = room.getRoles().get(i);
		found = 1;
	    }
	}
	if(found == 0){
	    Scene scene = room.getScene();
	    for(int i = 0; i < scene.getNumberOfRoles(); i++){
		if(scene.getRoles().get(i).getName().equals(desired_role)){
		    role = scene.getRoles().get(i);
		    found = 1;
		}
	    }
	}
	if(found == 0){
	    System.out.println("You input bad role.. things are messed up now. nice. ");
	}
	return role;
    }

    // returns 0 for not enough, 1 for enough funds for rank
    public static int getPlayerFunds(Player temp, int money_type, int new_rank){
	int enough_money = 0;
        if(new_rank == 2 && money_type == 0 && temp.getDollars() >= 4){
	    enough_money = 1;
	}
	else if(new_rank == 2 && money_type == 1 && temp.getCredits() >= 5){
	    enough_money = 1;
	}
	else if(new_rank == 3 && money_type == 0 && temp.getDollars() >= 10){
	    enough_money = 1;
	}
	else if(new_rank == 3 && money_type == 1 && temp.getCredits() >= 10){
	    enough_money = 1;
	}
	else if(new_rank == 4 && money_type == 0 && temp.getDollars() >= 18){
	    enough_money = 1;
	}
	else if(new_rank == 4 && money_type == 1 && temp.getCredits() >= 15){
	    enough_money = 1;
	}
	else if(new_rank == 5 && money_type == 0 && temp.getDollars() >= 28){
	    enough_money = 1;
	}
	else if(new_rank == 5 && money_type == 1 && temp.getCredits() >= 20){
	    enough_money = 1;
	}
	else if(new_rank == 6 && money_type == 0 && temp.getDollars() >= 40){
	    enough_money = 1;
	}
	else if(new_rank == 6 && money_type == 1 && temp.getCredits() >= 25){
	    enough_money = 1;
	}

	return enough_money;
    }
    /* First goes through the current room and finds all the players that are in a role
       at all. Then it looks to see if anyone of those players is in a main role.
       If at least one person is, bonus is calculated and distributed accordingly. Then,
       player roles are reset, and scenes are wrapped accordingly. */
    private static void wrapScene(Room room, Board board){
	ArrayList<Player> players_in_room_with_roles = new ArrayList<Player>();
	for(int i = 0; i < board.getPlayerListSize(); i++){
	    if(board.getPlayer(i+1).getCurrentRoom().getName().equals(room.getName())
	       && board.getPlayer(i+1).getRole() != null){
		players_in_room_with_roles.add(board.getPlayer(i+1));
	    }
	}

	ArrayList<Player> main_role_players = new ArrayList<Player>();
	for(int i = 0; i < board.getPlayerListSize(); i++){
	    if(board.getPlayer(i+1).getCurrentRoom().getName().equals(room.getName())
	       && board.getPlayer(i+1).getRoleType() == 1){
		main_role_players.add(board.getPlayer(i+1));
	    }
	}

	if(main_role_players.size() > 0){
	    ArrayList<Integer> rolls = new ArrayList<Integer>();
	    for(int i = 0; i < room.getScene().getBudget(); i++){
		rolls.add(rollDice(1));
	    }

	    Collections.sort(rolls);
	    int j = rolls.size()-1;
	    for(int i = 0; i < main_role_players.size() && j >= 0; i++){
		main_role_players.get(i).addCurrency(0, rolls.get(j));
		j--;
		if( (i+1) == main_role_players.size() ){
		    i = 0;
		}
	    }
	}

	for(int i = 0; i < players_in_room_with_roles.size(); i++){
	    players_in_room_with_roles.get(i).resetRole();
	}

	for(int i = 0; i < main_role_players.size(); i++){
	    main_role_players.get(i).resetRole();
	}
	board.wrapScene();
	room.wrapScene();
    }

    // returns 0 for room role, 1 for scene role
    private static int getRoleType(String desired_role, Player p){
	int role_type = -1;
	Role role = new Role(1, null, "fake role", "this is a fake role");
	int found = 0;
	Room room = p.getCurrentRoom();
	for(int i = 0; i < room.getNumberOfRoles(); i++){
	    if(room.getRoles().get(i).getName().equals(desired_role)){
		role = room.getRoles().get(i);
		found = 1;
		role_type = 0;
	    }
	}
	if(found == 0){
	    Scene scene = room.getScene();
	    for(int i = 0; i < scene.getNumberOfRoles(); i++){
		if(scene.getRoles().get(i).getName().equals(desired_role)){
		    role = scene.getRoles().get(i);
		    found = 1;
		    role_type = 1;
		}
	    }
	}
	if(found == 0){
	    System.out.println("You input bad role.. things are messed up now. nice. ");
	}
	return role_type;
    }

    // Gives player payout on success roll based on their role type
    private static void payoutSuccess(Player p){
	if(p.getRoleType() == 0){
	    p.addCurrency(0, 1);
	    p.addCurrency(1, 1);
	}
	else{
	    p.addCurrency(1, 2);
	}
    }

    // Gives player payout only if they are side role on fail roll
    private static void payoutFail(Player p){
	if(p.getRoleType() == 0){
	    p.addCurrency(0, 1);
	}
    }

    // returns 0 for no, 1 for yes
    private static int isEndOfDay(Board board){
	int eod = 0;
  System.out.println("number of wrapped scenes: "+board.numWrappedScenes());
	if(board.numWrappedScenes() == 9){
	    eod = 1;
	}
	return eod;
    }

    /* Parses through all players when the game is over to calculate the winner by
       adding up dollars, credits, and (rank * 5). Prints winner to console. */
    private static void calculateWinner(Board board){
	int winning_pid = 0;
	int top_score = 0;

	for(int i = 0; i < board.getPlayerListSize(); i++){
	    Player temp_player = board.getPlayer(i+1);
	    int temp_score = temp_player.getDollars() + temp_player.getCredits() +
		(temp_player.getRank() * 5);
	    if(temp_score > top_score){
		top_score = temp_score;
		winning_pid = i + 1;
	    }
	}

	System.out.println("The winner is: Player "+winning_pid+"!");
    }

    /* Prints the current player who's turn it is along with dollars, credits, and role
       if they have one. */
    private static void whoInput(String command, int turn, Board board){
	Player temp = board.getPlayer(turn);
	System.out.print("Player "+turn+": ($:"+temp.getDollars()+", cr:"+temp.getCredits()+
			 ", rank:"+temp.getRank());
	if(temp.getRole() != null){
	    System.out.print(", role:"+temp.getRole().getName()+")\n");
	}
	else{
	    System.out.print(")\n");
	}
    }

    /* Prints the current room of the Player who called the command, along with a scene
       if there is a scene in that room. Trailers, Casting Office, and already wrapped
       rooms will not print a scene currently shooting. */
    private static void whereInput(String command, int turn, Board board){
	Player temp = board.getPlayer(turn);
	if(temp.getCurrentRoom().getScene() != null){
	    Scene temp_scene = temp.getCurrentRoom().getScene();
	    System.out.println("in "+temp.getCurrentRoom().getName()+" shooting "+temp_scene.getName());
	}
	else{
	    System.out.println("in "+temp.getCurrentRoom().getName());
	}
    }

    /* Grabs the desired room that the player wants to move to and first checks if
       it is a neighboring room of their current room. If it is, it updates the players
       room. If not, it prints an error message. */
    public static void moveInput(String command, int turn, Board board){
	Player temp = board.getPlayer(turn);
	String desired_room_string = getDesiredRoom(command);
	Room desired_room = board.getRoom(desired_room_string);
	Room current_room = temp.getCurrentRoom();
	if(current_room.isNeighbor(desired_room) == 1){
	    temp.updateRoom(desired_room);
	    temp.move();
	    desired_room.addPlayer(temp);
	    current_room.removePlayer(temp);
	}
	else{
	    System.out.println("Room is not neighboring room. Try something else.");
	}
    }

    // Adds a rehearse token to player
    public static void rehearseInput(String command, int turn, Board board){
	Player temp = board.getPlayer(turn);
	if(temp.getRole() != null){
	    if(temp.getRehearseTokens() <= temp.getCurrentRoom().getScene().getBudget()){
		temp.rehearse();
		temp.useAction();
	    }
	    else{
		System.out.println("Rehearsing too many times!");
	    }
	}
	else{
	    System.out.println("Not in a role.");
	}
    }

    /* First checks if player is in role. If not, prints error. Then rolls dice
       to check for success or fail and calculates currency accordingly depending
       on main role or side role. */
    public static void actInput(String command, int turn, Board board){
	Player temp = board.getPlayer(turn);
	if(temp.getCurrentRoom().getShotCounters() > 0){
	    if(temp.getRole() == null){
		System.out.println("No current role. Try something else.");
	    }
	    else{
		temp.useAction();
		int roll = rollDice(1);
		int budget = temp.getCurrentRoom().getScene().getBudget();
		System.out.println("budget: "+budget+". Scene name: "+temp.getCurrentRoom().getScene().getName());
		if(temp.act(roll, budget) == false){
		    payoutFail(temp);
		    System.out.println("fail roll");
		}
		else if(temp.act(roll, budget) == true){
		    System.out.println("success roll");
		    temp.getCurrentRoom().removeShot();
		    payoutSuccess(temp);
		    if(temp.getCurrentRoom().getShotCounters() == 0){
			wrapScene(temp.getCurrentRoom(), board);
		    }
		}
	    }
	}
	else{
	    System.out.println("Scene is wrapped already.");
	}
    }

    /* Upgrades current player to desired rank. First checks that they're in casting
       office, then checks for 2 <= rank <= 6, then checks if there is enough money.
       Prints error messages accordingly. */
    public static void upgradeInput(String command, int turn, Board board){
	Player temp = board.getPlayer(turn);
	if(!temp.getCurrentRoom().getName().equals("Casting Office")){
	    System.out.println("Not in Casting Office. Try again.");
	}
	else{
	    int money_type = getMoneyType(command);
	    int new_rank = getNewRank(command);
	    if(new_rank < 2 || new_rank > 6){
		System.out.println("Bad rank. Try again.");
	    }
	    else{
		if(getPlayerFunds(temp, money_type, new_rank) == 0){
		    System.out.println("Not enough money for upgrade.");
		}
		else{
		    temp.useAction();
		    temp.updateRankAndMoney(new_rank, money_type);
		    System.out.println("new rank: "+temp.getRank());
		}
	    }
	}
    }

    /* Updates player to be working in desired role. First checks if the role exists, then if
       the player rank is >= the role rank, then checks if the role is not already taken. Prints
       error messages accordingly. */
    public static void workInput(String command, int turn, Board board){
	Player temp = board.getPlayer(turn);
	String desired_role_string = getDesiredRoleString(command);
	Role role = findRole(desired_role_string, temp);
	if(role != null){
	    int role_type = getRoleType(desired_role_string, temp);
	    if(temp.getRank() >= role.getRank() && temp.getRole() == null && role.isBeingWorked() == 0){
		temp.useAction();
		temp.setRole(role);
		role.workRole();
		temp.setRoleType(role_type);
	    }
	    else{
		System.out.println("The role rank is larger than player rank or "+
				   "the player already has a role or the role is already taken. Try again.");
	    }
	}
	else{
	    System.out.println("Cannot take that role");
	}
    }

}
