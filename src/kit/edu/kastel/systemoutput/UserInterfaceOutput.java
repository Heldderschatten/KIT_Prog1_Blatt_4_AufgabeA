package kit.edu.kastel.systemoutput;

import kit.edu.kastel.pathfindingalgo.Graph;
import kit.edu.kastel.pathfindingalgo.Node;

/**
 * Find better NAME.
 *
 * @author ufvgn
 */
//todo Javadocs Kommenrat Klasse
public class UserInterfaceOutput {

    /**
     * Print a given Massage on bord.
     *
     * @param massage is the given String massage
     */
    public void printInvaildMassage(String massage) {
        System.out.println(massage);
    }

    /**
     * print a given Graph to the Terminal.
     *
     * @param graph is the given Graph.
     */
    public void printGraph(Graph graph) {
        for (Node[] nodes : graph.getWayPoints()) {
            for (Node node : nodes) {
                System.out.print(node.getCharSymbol());
            }
            System.out.println();
        }
    }

}
