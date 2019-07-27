import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class ParseCard {

    /* For_each node
     * This interface and for_each function iterator over the nodes in a node
     * list.
     */
    public interface Runnable_Node {
	public void run(Node n);
    }

    public static void for_each(NodeList list, Runnable_Node run) {
	for(int i = 0; i < list.getLength(); ++i)
	    run.run(list.item(i));
    }

    /* For_each element
     * This interface and the following for_each function iterate over the
     * elements in a node list.
     */
    public interface Runnable_Element {
	public void run(Element n);
    }

    public static void for_each(NodeList list, Runnable_Element run) {
	for_each(list, (Node n) -> {
		if(n.getNodeType() == Node.ELEMENT_NODE)
		    run.run((Element) n);
	    });
    }

    /* Parse Document
     *
     * Reads, parses, and returns the root element of the indicated file.
     */
    public static Element parse_doc(String filename) {
	Element root = null;

	try{
	    File inputFile = new File(filename);

	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(inputFile);

	    doc.getDocumentElement().normalize();
	    root = doc.getDocumentElement();
	}
	catch (Exception e) {
	    e.printStackTrace();
	    System.exit(1);
	}

	return root;
    }

    //the constructor, it takes in a parameter board, we parse through each card and create the scenes which are added to board's ArrayList of scenes
    public ParseCard(Board board) {
	Element doc = parse_doc("resources/cards.xml");
	for_each(doc.getElementsByTagName("card"),
		 (Element card) -> board.addScene(createScene(card)));
    }

    /*Functions very similarily to the createScene method, it gets each individual part of the role and uses them to create a role that is then returned and added to the scene's ArrayList
      of roles. The only different part here is getting the line, I basically kept it the same as it was in the source document only instead of printing it I added each part to an ArrayList
      which I then turned into a String.
    */
    public static Role createRole(Element part) {
	String name = part.getAttribute("name");
	int level = Integer.parseInt(part.getAttribute("level"));
	String line="";
	ArrayList<String> lineHelper = new ArrayList<String>();
	for_each(part.getElementsByTagName("line"),
		 (Element line2) -> {
		     lineHelper.add(line2.getTextContent()+" ");
		 });
	for (int i =0; i<lineHelper.size(); i++){
	    line+=lineHelper.get(i);
	}
	ArrayList<Integer> areaList = new ArrayList<Integer>();
	for_each(part.getElementsByTagName("area"),
		 (Element area) -> {
		     areaList.add(Integer.parseInt(area.getAttribute("x")));
		     areaList.add(Integer.parseInt(area.getAttribute("y")));
		     areaList.add(Integer.parseInt(area.getAttribute("h")));
		     areaList.add(Integer.parseInt(area.getAttribute("w")));
		 });
	Role role = new Role(level, areaList, name, line);
	return role ;
    }

    /*Each card is a scene, each scene has a name, a budget, a number, a line, and several parts. To get the number and line I had to go inside card to scene in the xml file, I used
      ArrayList helper functions because it wouldn't let me assign a value to a number inside the for_each loop. Basically I search each card for the important information and pass it
      to a variable which at the end I use to construct the scene that gets passed to the board. For the roles I used an ArrayList and each role is constructed in the createRole
      method above that creates a role in a similar fashion.
    */
    public static Scene createScene(Element card) {
	String name = card.getAttribute("name");
	int budget = Integer.parseInt(card.getAttribute("budget"));
	//int number;
	ArrayList<String> lineHelper = new ArrayList<String>();
	ArrayList<String> numberHelper = new ArrayList<String>();
	for_each(card.getElementsByTagName("scene"),
		 (Element scene) -> {
		     numberHelper.add(scene.getAttribute("number"));
		     lineHelper.add(scene.getTextContent());
		 });
	String line = lineHelper.get(0);
	int number = Integer.parseInt(numberHelper.get(0));
	ArrayList<Role> list = new ArrayList<Role>();
	for_each(card.getElementsByTagName("part"),
		 (Element part) -> {
		     list.add(createRole(part));
		 });
	String image = card.getAttribute("img");
	Scene scene = new Scene(name, line, number, image, list, budget);
	return scene;
    }
}
