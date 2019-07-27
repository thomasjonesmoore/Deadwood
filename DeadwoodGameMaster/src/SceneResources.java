import java.util.LinkedList;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.awt.Color;
import java.util.ArrayList;

//Similar to the other singleton pattern resource clases we have in our Deadwood, its got an array of ImageIcons for each image that are stored in our resources folder.
//Each card has a scene number that is assigned, the only interesting thing here is the sub number 10 cards have a 0 in their name that we account for.

public class SceneResources{

    private ArrayList<ImageIcon> big;
    static SceneResources instance;

    public SceneResources(){
	big = new ArrayList<ImageIcon>();
	final Class cls = SceneResources.class;
	String fn = "";

	for (int i = 0; i < 40; i++){
	    if(i < 9){
		fn = String.format("resources/cards/0%d.png", i+1);
	    }
	    else{
		fn = String.format("resources/cards/%d.png", i+1);
	    }
	    try (InputStream r = cls.getResourceAsStream(fn)) {
		big.add(new ImageIcon (ImageIO.read(r)));
	    }
	    catch (IOException e) {
		System.err.println("fn=\"" + fn + "\"");
		e.printStackTrace();
		System.exit(1);
	    }
	}
    }

    public ImageIcon getSceneIcon(int sceneNum){
	return big.get(sceneNum-1);
    }

    public static SceneResources getInstance() {
	if (instance == null)
	    instance = new SceneResources();
	return instance;
    }
}
