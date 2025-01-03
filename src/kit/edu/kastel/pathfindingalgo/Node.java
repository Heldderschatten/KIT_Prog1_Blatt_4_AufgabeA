package kit.edu.kastel.pathfindingalgo;

import kit.edu.kastel.rover.Coordinate;
import kit.edu.kastel.rover.MapSymboles;

import java.util.ArrayList;

/**
 * Represents a singel Node in the Graph.
 *
 * @author ufvgn
 */
public class Node {
    private final Coordinate coordinate;
    private char charSymbol;
    private ArrayList<Node> neighbours = new ArrayList<Node>();

    /**
     * set up one Node for the new Graph.
     *
     * @param height {@code int} the y postion in the map
     * @param width  {@code int } the x postion in the map
     * @param charSymbol {@code char} the visibalSymbol of the Node
     */
    public Node(int height, int width, char charSymbol) {
        this.coordinate = new Coordinate(height, width);
        this.charSymbol = charSymbol;
    }

    /**
     * set up a new Node.
     * <p>
     *     The Node is set up by spefic given Coordinates.
     *     And one given Symbol that represent the Node graphical.
     * </p>
     * @param coordinate The Postion of the Node.
     * @param charSymbol The graphical Symbol of the Node
     */
    public Node(Coordinate coordinate, char charSymbol) {
        this.coordinate = coordinate;
        this.charSymbol = charSymbol;
    }

    /**
     * Add One neibour Node to the graph. (Also the Node self will add to the Node)
     *
     * @param neighbour the adding Node
     * @return {@code true} if the add was sucseful, else will return {@code false}
     */
    public boolean addNeighbour(Node neighbour) {
        if (neighbours.contains(neighbour)) {
            return false;
        }
        neighbours.add(neighbour);
        return true;
    }

    /**
     * Return the Coordinates of the Node.
     *
     * @return {@link Coordinate} wich one contains the postion of the Node
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Return the current Map symbol from the Node.
     * @return a vlaue from {@link MapSymboles} of the Node
     */
    public char getCharSymbol() {
        return charSymbol;
    }

    /**
     * set a new given  Mapsymbol.
     * @param charSymbol {@link MapSymboles} is the new status of the Node
     */
    public void setCharSymbol(char charSymbol) {
        this.charSymbol = charSymbol;
    }

    /**
     * Return a ArrayList of the {@link Node} from the Neibours.
     * @return  Array list
     */
    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }

    /**
     * Return a Copy of the Node.
     * @return a copy of the Node.
     */
    public Node copyNode() {
        Node copy = new Node(coordinate, charSymbol);
        for (Node neighbour : neighbours) {
            copy.addNeighbour(neighbour);
        }
        return copy;
    }

}
