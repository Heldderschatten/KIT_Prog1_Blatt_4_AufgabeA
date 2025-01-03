package kit.edu.kastel;

import kit.edu.kastel.pathfindingalgo.Graph;
import kit.edu.kastel.pathfindingalgo.Pathfinder;
import kit.edu.kastel.pathfindingalgo.Way;
import kit.edu.kastel.rover.Coordinate;
import kit.edu.kastel.rover.Direction;
import kit.edu.kastel.systemoutput.UserInterfaceOutput;


/**
 * Controlls the comunication and orginze the programm.
 *
 * @author ufvgn
 */
public class Controller {
    private UserInterfaceOutput output;
    private Graph graph;
    private Graph graphWithWay;
    private boolean quit;

    /**
     * Starts the Controller class and make it ready.
     */
    public Controller() {
        output = new UserInterfaceOutput();
        quit = false;
    }

    /**
     * Moves the Rover by  given direction and count.
     *
     * @param count     the number of Moves that the Rover do
     * @param direction the Direction of the move for the Rover
     */
    public void moveRover(Direction direction, int count) {
        for (int index = 0; index < count; index++) {
            if (!graph.moveRover(direction)) {
                break;
            }
        }
    }

    /**
     * Print the current Graph on screen.
     */
    public void printMap() {
        output.printGraph(graph);
    }
    /**
     * Print a message of error.
     *
     * @param massage is the printing Massgae.
     */
    public void printInvalidMassage(String massage) {
        output.printInvaildMassage(massage);
    }


    /**
     * Set the new actual Map by the Rover.
     *
     * @param graph is a given {@link Graph}
     */
    public void setCurrentGraph(Graph graph) {
        this.graph = graph.copyGraph();
        graphWithWay = graph.copyGraph();
    }

    /**
     * Return the status of the quit parameter.
     *
     * @return if the programm go to quit {@code true} else {@code false}
     */
    public boolean isQuit() {
        return quit;
    }

    /**
     * set the quit paramter.
     *
     * @param quit is the new stauts of the quit param
     */
    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    /**
     * Return the current Map as {@link Graph}.
     *
     * @return the current Graph
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * set the way graph coorect.
     *
     * @param finder is the findier.
     */
    public void setGraphWithWay(Pathfinder finder) {
        Way way = finder.getShortestPath();
        Coordinate currentPostion = graph.findRover();
        for (int index = 0; index < way.getCommands().size() - 1; index++) {
            Direction direction = way.getCommands().get(index).getDirection();
            currentPostion = currentPostion.getNeibourCoordinate(direction);
            graphWithWay.changeSymbolAt(currentPostion, '.');
        }
    }


    /**
     * Print the graph only dev.
     */
    public void printWayGraph() {
        output.printGraph(graphWithWay);
    }
}

