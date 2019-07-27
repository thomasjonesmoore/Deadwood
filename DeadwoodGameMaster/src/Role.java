/* Used for main roles and side roles. Keeps track of rank, name, line, and being worked or not. */
import java.util.ArrayList;
import java.util.Arrays;

public class Role{

    private int _rank;
    private String _name;
    private String _line;
    private int being_worked; //0 for no, 1 for yes
    private ArrayList<Integer> area = new ArrayList<Integer>();

    Role(int rank, ArrayList<Integer> a, String name, String line){
	_rank = rank;
	_name = name;
	_line = line;
	being_worked = 0;
	this.area=a;
    }

    public String getName(){
	return _name;
    }

    public String getLine(){
	return _line;
    }

    public int getRank(){
	return _rank;
    }

    public int isBeingWorked(){
	return being_worked;
    }

    public void workRole(){
	being_worked = 1;
    }

    public ArrayList<Integer> getArea(){
	return area;
    }
}
