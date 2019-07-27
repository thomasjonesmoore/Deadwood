import java.util.LinkedList;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.awt.Color;

//the resources for the PlayerView, similar to our other resource classes this is a singleton pattern, it has an array of ImageIcons, 48 in total and there is one for each dice color and
//number, it just iterates through those to add them to the array, then returns the desired ImageIcon. 

public class PlayerResources{

    private ImageIcon[] big;
    static PlayerResources instance;
    /*Player Colors
      p1=blue
      p2=orange
      p3=green
      p4=red
      p5=yellow
      p6=violet
      p7=pink
      p8=cyan
    */
    static char[] color = new char[]{'b', 'o', 'g', 'r', 'y', 'v', 'p', 'c'};

    public PlayerResources(){
	big = new ImageIcon[48];
	final Class cls = PlayerResources.class;
	//player one = blue, p2 = yellow and so on
	for (int j =0; j<8; j++){
	    for (int i =0; i < 6; i++){
		String fn = String.format("resources/dice/%c%d.png", color[j], i+1);
		try (InputStream r = cls.getResourceAsStream(fn)) {
		    big[(6*j)+i] = new ImageIcon (ImageIO.read(r));
		}
		catch (IOException e) {
		    System.err.println("fn=\"" + fn + "\"");
		    e.printStackTrace();
		    System.exit(1);
		}
	    }
	}
    }

    public ImageIcon getIcon(int id, int rank){
	int x =rank + ((id-1)*6)-1;
	return big[x];
    }

    public static PlayerResources getInstance() {
	if (instance == null)
	    instance = new PlayerResources();
	return instance;
    }
}
