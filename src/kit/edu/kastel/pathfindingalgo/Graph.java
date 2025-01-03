package kit.edu.kastel.pathfindingalgo;


import kit.edu.kastel.rover.Coordinate;
import kit.edu.kastel.rover.Direction;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the Graph.
 *
 * @author ufvgn
 */
public class Graph {
    private static final char[] BARRIERSYMBOLS = new char[]{'|', '_', '/', '\\', '*'};
    private static final char ROVERSYMBOL = 'R';
    private static final char GOALSYMBOL = 'x';
    private static final char ROVERANDGOALSYMBOL = 'X';
    private static final char PASSABLETARIN = ' ';
    private static final char DEBUGPATHSYMBOL = '.';
    //One big set with all Symbols
    private Set<Character> validSymbols = new HashSet<>();
    private int height;
    private int width;

    private Node[][] wayPoints;

    /**
     * set up the Graph. the Given Map is the Map that the Graph would represent.
     *
     * @param height is the number of Nodes in Down Direction from the Start.
     * @param width is the Number Of Nodes in Right Direction from the Start.
     */
    public Graph(int height, int width) {
        this.height = height;
        this.width = width;
        wayPoints = new Node[height][width];
        setUpVaildSymbolsSet();
    }


    private void setUpVaildSymbolsSet() {
        //add all Symbol of the Map
        validSymbols.add(ROVERSYMBOL);
        validSymbols.add(GOALSYMBOL);
        validSymbols.add(ROVERANDGOALSYMBOL);
        validSymbols.add(PASSABLETARIN);
        validSymbols.add(DEBUGPATHSYMBOL);
        //add the BarrierSymbols
        for (char barrier : BARRIERSYMBOLS) {
            validSymbols.add(barrier);
        }
    }

    /**
     * Set one spefic send line new on the Graph.
     * <p>
     *     The height of the line is startet in the Left UP Corner to the Right Down Corner.
     *     if the line has no correct Symbols the Method return {@code false},
     *     if all is oke, the line set new and return {@code true}
     * </p>
     * @param height is the height form the Line.
     * @param line is the sendet line.
     * @return if the set of the new line is correct return {@code true} else return  {@code false}
     */
    public boolean setOneLine(int height, char[] line) {
        if (!checkLineCorrect(line)) {
            return false;
        }
        for (int index = 0; index < line.length; index++) {
            wayPoints[height][index] = new Node(height, index, line[index]); //Create a new Node
        }
        if (height == this.height - 1) {
            setNeibours();
        }
        return true;
    }

    /**
     * Move the Rover to a given Postion.
     * @param direction the Direction of the Move.
     * @return {@code true} if the step was sucessful. Else {@code false}
     */
    public boolean moveRover(Direction direction) {
        Coordinate roverPostion = findRover();
        Coordinate goalPostion = roverPostion.getNeibourCoordinate(direction);
        Node roverNode = wayPoints[roverPostion.getHeight()][roverPostion.getWidth()];
        //todo butiful machen
        if (!(goalPostion.getHeight() >= 0 && goalPostion.getWidth() >= 0)) {
            return false;
        }
        if (!(goalPostion.getHeight() < height && goalPostion.getWidth() < width)) {
            return false;
        }
        Node goalNode = wayPoints[goalPostion.getHeight()][goalPostion.getWidth()];
        for (int index = 0; index < roverNode.getNeighbours().size(); index++) {
            Coordinate neighbour = roverNode.getNeighbours().get(index).getCoordinate();
            if (goalPostion.getHeight() == neighbour.getHeight() && goalPostion.getWidth() == neighbour.getWidth()) {
                roverNode.setCharSymbol(PASSABLETARIN);
                goalNode.setCharSymbol(ROVERSYMBOL);
                return true;
            }
        }
        return false;
    }

    private boolean checkLineCorrect(char[] line) {
        //Check Line has the correct Length
        if (line.length != width) {
            return false;
        }
        for (char symbol : line) {
            if (!validSymbols.contains(symbol)) {
                return false;
            }
        }
        return true;
    }
    //todo can Remove
    private void setUpWayPoints(char[][] wayPointsChars) {
        if (!(wayPointsChars.length > 0)) {
            return;
        }
        wayPoints = new Node[wayPointsChars.length][wayPointsChars[0].length];
        for (int height = 0; height < wayPointsChars.length; height++) {
            for (int width = 0; width < wayPointsChars[0].length; width++) {
                wayPoints[height][width] = createNode(wayPointsChars[height][width], width, height);
            }
        }
        setNeibours();
    }

    private void setNeibours() {
        for (int height = 0; height < wayPoints.length; height++) {
            for (int width = 0; width < wayPoints[0].length; width++) {
                //Check barrierBlock --> No way posible
                Coordinate currentCoordinate = new Coordinate(height, width);
                if (nodeAtIsBarrier(currentCoordinate)) {
                    continue;
                }
                Coordinate check = currentCoordinate.getNeibourCoordinate(Direction.UP);
                //todo vereinfachen
                //check UP
                if (check.getHeight() >= 0) {
                    if (!nodeAtIsBarrier(check)) {
                        wayPoints[height][width].addNeighbour(wayPoints[check.getHeight()][check.getWidth()]);
                    }
                }
                check = currentCoordinate.getNeibourCoordinate(Direction.DOWN);
                //check Down
                if (check.getHeight() < wayPoints.length) {
                    if (!nodeAtIsBarrier(check)) {
                        wayPoints[height][width].addNeighbour(wayPoints[check.getHeight()][check.getWidth()]);
                    }
                }
                check = currentCoordinate.getNeibourCoordinate(Direction.RIGHT);
                //check Right
                if (check.getWidth() < wayPoints[0].length) {
                    if (!nodeAtIsBarrier(check)) {
                        wayPoints[height][width].addNeighbour(wayPoints[check.getHeight()][check.getWidth()]);
                    }
                }
                check = currentCoordinate.getNeibourCoordinate(Direction.LEFT);
                //check Left
                if (check.getWidth() >= 0) {
                    if (!nodeAtIsBarrier(check)) {
                        wayPoints[height][width].addNeighbour(wayPoints[check.getHeight()][check.getWidth()]);
                    }
                }
            }
        }
    }
    private boolean nodeAtIsBarrier(Coordinate coordinate) {
        return nodeIsBarrier(wayPoints[coordinate.getHeight()][coordinate.getWidth()]);
    }
    //return true if the Node is a Barrier. Else Return false
    private boolean nodeIsBarrier(Node node) {
        for (char barrier : BARRIERSYMBOLS) {
            if (node.getCharSymbol() == barrier) {
                return true;
            }
        }
        return false;
    }

    //create and return a Node without a neigbour
    private Node createNode(char point, int height, int width) {
        return new Node(height, width, point);
    }

    /**
     * Return a {@code Node[][]} Array of the Nodes.
     * @return the Nodes from the Graph.
     */
    public Node[][] getWayPoints() {
        return wayPoints;
    }

    /**
     * Return a sepfic Node on one Postion.
     * @param height is the Height Coordinate.
     * @param width is the width postion.
     * @return {@link Node} on the sepfic Coordinate
     */
    public Node getNodeAt(int height, int width) {
        return wayPoints[height][width];
    }
    /**
     * Search in the Graph the Rover and Return the coordinates of the Rover.
     * <p>
     *     0 --> Height
     *     1 --> Width
     * </p>
     * @return {@link Coordinate} the Rover Postion. if no Rover in there Return {@code null}
     */
    public Coordinate findRover() {
        for (int height = 0; height < wayPoints.length; height++) {
            for (int width = 0; width < wayPoints[0].length; width++) {
                if (wayPoints[height][width].getCharSymbol() == ROVERSYMBOL) {
                    return new Coordinate(height, width);
                }
            }
        }
        return null;
    }
    /**
     * Search in the Graph the Goal and Return the coordinates of the Goal.
     * <p>
     *     0 --> Height
     *     1 --> Width
     * </p>
     * @return {@link Coordinate} the Goal Postion. if no Goal in there Return {@code null}
     */
    public Coordinate findGoal() {
        for (int height = 0; height < wayPoints.length; height++) {
            for (int width = 0; width < wayPoints[0].length; width++) {
                if (wayPoints[height][width].getCharSymbol() == GOALSYMBOL) {
                    return new Coordinate(height, width);
                }
            }
        }
        return null;
    }

    /**
     * Change at a sepezic postoin the smybol of a Node.
     * @param coordinate represent the Coordinate of the changuing Node
     * @param newSymbol the new Symbol {@code char}
     */
    public void changeSymbolAt(Coordinate coordinate, char newSymbol) {
        wayPoints[coordinate.getHeight()][coordinate.getWidth()].setCharSymbol(newSymbol);
    }

    /**
     * Return  the Graph is Vaild.
     * <p>
     *     The Graph is Vaild:
     *     only One Robot, only one Goal, Only Coorect Symbols.
     *     the Robot and Goal can on the same Postion.
     * </p>
     * @return {@code true} if the Graph is vaild, else {@code false}
     */
    public boolean graphIsVaild() {
        return true; //todo einfügen
    }

    /**
     * Set a spefic Node new.
     * @param coordinate is the Coordinate of the chaning Node
     * @param node is the new Node on the Postion.
     */
    public void setNode(Coordinate coordinate, Node node) {
        wayPoints[coordinate.getHeight()][coordinate.getWidth()] = node;
    }

    /**
     * Return a Copy of the Graph.
     * @return one exact copy of the Graph
     */
    public Graph copyGraph() {
        Graph copyGraph = new Graph(height, width);
        for (int height = 0; height < wayPoints.length; height++) {
            for (int width = 0; width < wayPoints[0].length; width++) {
                copyGraph.setNode(new Coordinate(height, width), wayPoints[height][width].copyNode());
            }
        }
        return copyGraph;
    }

}
