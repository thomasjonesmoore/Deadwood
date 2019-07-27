import java.util.LinkedList;
import java.util.List;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.awt.Color;
import java.util.*;
import java.util.concurrent.*;

//Fairly similar to our other views, the class extends JPanel and listens to the scene (which listens to the room it is placed in), once again we use no layout for the panels or labels,
//preferring to manually place the panels/labels where we want, the scene card is 205 by 115 and it gets added onto the panel which is once again not opaque for aesthetic purposes.

public class SceneView extends JPanel implements Scene.Listener{

    private JLabel scene;
    private Controller c;
    private Room _room;

    public SceneView(SceneResources s, int sceneNum, Controller controller){
	super(null);
	setLayout(null);

	_room = null;
	c = controller;
	scene = new JLabel();
	add(scene, 0);
	scene.setBounds(0,0,205,115);

	setOpaque(false);
	setVisible(true);
    }

  //The room keeps track of whenever a player enters or leaves the room, when someone enters the room the scene listens to that and the scene view listens to that scene and knows to flip
  //the card over. All we do here is get the instance of the SceneResources and get the sceneCard image using the scenes number (which are all in the xml files) then set the bounds of the
  //panel to be that of the card so it will actually show up on screen.
  public void showScene(Scene s){
    SceneResources res = SceneResources.getInstance();
    scene.setIcon(res.getSceneIcon(s.getNumber()));
    setBounds(s.getArea().get(0), s.getArea().get(1), s.getArea().get(3), s.getArea().get(2));
    WorkRoleScenePanels wsrp = new WorkRoleScenePanels(s, c);
    add(wsrp);
  }

  //The scene alerts the view whenever it is over and since a scene can only show up once per game we just set the bounds to be all 0, effectively removing it from the board.
    public void closeScene(Scene s){
	setBounds(0,0,0,0);
	setVisible(false);
    }

}
