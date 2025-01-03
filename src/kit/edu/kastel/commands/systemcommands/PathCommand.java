package kit.edu.kastel.commands.systemcommands;

import kit.edu.kastel.Controller;
import kit.edu.kastel.commands.Command;
import kit.edu.kastel.pathfindingalgo.Pathfinder;

/**
 * Represents the path command.
 * <p>
 *     Start the cal. to the best path
 * </p>
 *
 * @author ufvgn
 */
public class PathCommand extends Command {


    private static final String COMMANDNAME = "path";
    /**
     * set up the path command.
     */
    public PathCommand() {
        super(COMMANDNAME);
    }
    @Override
    public void execute(String[] args, Controller controller) {
        Pathfinder pathfinder = new Pathfinder(controller.getGraph());
        pathfinder.latitudeSearch();
        pathfinder.getshortestDistance();
        controller.setGraphWithWay(pathfinder);
    }
}
