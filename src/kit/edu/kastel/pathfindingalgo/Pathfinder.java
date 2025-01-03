package kit.edu.kastel.pathfindingalgo;

import kit.edu.kastel.commands.movingcommands.MovingCommands;
import kit.edu.kastel.rover.Coordinate;
import kit.edu.kastel.rover.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


/**
 * This class search the best way to the goal.
 *
 * @author ufvgn
 */
public class Pathfinder {
    private static final char ROVERSYMBOL = 'R';
    private Graph startGraph;
    private HashMap<Node, Integer> distances = new HashMap<Node, Integer>();
    private HashMap<Node, Way> ways = new HashMap<>();

    /**
     * set up the Pathfinder Algorytmus.
     *
     * @param startGraph is the given Graph for the move
     */
    public Pathfinder(Graph startGraph) {
        this.startGraph = startGraph;
    }

    /**
     * start the latudie search on the in the start given Graph.
     */
    //todo make it butiful
    public void latitudeSearch() {
        calculateShortestWay(); //todo remove
    }

    /**
     * Print at the moment the PathWay on screen.
     */
    public void getshortestDistance() {
        Coordinate goalPostion = startGraph.findGoal();
        int wayToGOal = distances.get(startGraph.getNodeAt(goalPostion.getHeight(), goalPostion.getWidth()));
        System.out.println("PATH " + wayToGOal); //todo Datenkapselung

        Node goalNode = startGraph.getNodeAt(goalPostion.getHeight(), goalPostion.getWidth());
        ArrayList<MovingCommands> commands = ways.get(goalNode).getCommands();

        for (int index = 0; index < commands.size(); index++) {
            System.out.println(commands.get(index).getCommandName());
        }

    }

    private void calculateShortestWay() {
        Coordinate roverPostion = startGraph.findRover();
        LinkedList<Node> inProgessNodes = new LinkedList<>();
        ArrayList<Node> lookedList = new ArrayList<Node>();

        Node startNode = startGraph.getNodeAt(roverPostion.getHeight(), roverPostion.getWidth());
        inProgessNodes.add(startNode);
        distances.put(startNode, 0);
        ways.put(startNode, new Way());

        while (!inProgessNodes.isEmpty()) {
            Node testNode = inProgessNodes.poll();
            for (int index = 0; index < testNode.getNeighbours().size(); index++) {
                Node neighbour = testNode.getNeighbours().get(index);
                Direction direction = findDirectionNeibouhr(testNode, neighbour);
                Way newWay = new Way(ways.get(testNode).getCommands());
                newWay.addMove(direction);
                if (lookedList.contains(neighbour)) {
                    continue;
                }
                inProgessNodes.add(neighbour);
                if (!distances.containsKey(neighbour)) {
                    distances.put(neighbour, distances.get(testNode) + 1);
                    ways.put(neighbour, newWay);
                    continue;
                }
                if (distances.get(neighbour) < distances.get(testNode) + 1) {
                    distances.put(neighbour, distances.get(testNode) + 1);
                    ways.put(neighbour, newWay);

                }
            }
            inProgessNodes.remove(testNode);
            lookedList.add(testNode);
        }
    }

    private Direction findDirectionNeibouhr(Node node1, Node node2) {
        Coordinate postionNode1 = node1.getCoordinate();
        Coordinate postionNode2 = node2.getCoordinate();

        int[] result = new int[2];
        result[0] = postionNode1.getHeight() - postionNode2.getHeight();
        result[1] = postionNode1.getWidth() - postionNode2.getWidth();

        if (result[0] < 0) {
            return Direction.RIGHT;
        }
        if (result[0] > 0) {
            return Direction.LEFT;
        }
        if (result[1] < 0) {
            return Direction.DOWN;
        }
        if (result[1] > 0) {
            return Direction.UP;
        }
        return null;
    }

    /**
     * Return the shortest Path of the finder.
     *
     * @return {@link Way}
     */
    public Way getShortestPath() {
        Coordinate goalPostion = startGraph.findGoal();
        return ways.get(startGraph.getNodeAt(goalPostion.getHeight(), goalPostion.getWidth()));
    }


}
