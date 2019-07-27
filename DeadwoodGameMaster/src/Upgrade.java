import java.util.ArrayList;

//Each upgrade is its own object with a level, a curency type (0 is dollars, 1 is credits), an amount of currency required for that upgrade and lastly an arraylist of integers that are
//the coordinates for the panels, all the information is gotten directly from the xml file

public class Upgrade{

    private int level;
    private int currency;
    private int amount;
    private ArrayList<Integer> area;

    public Upgrade(int l, String c, int a, ArrayList<Integer> ar){
	this.level=l;
	this.amount=a;
	this.area=ar;
	if (c.equals("dollar")){
	    this.currency=0;
	}
	else if (c.equals("credit")){
	    this.currency=1;
	}
    }

    public ArrayList<Integer> getArea(){
	return area;
    }
}
