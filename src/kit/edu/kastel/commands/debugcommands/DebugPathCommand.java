package kit.edu.kastel.commands.debugcommands;

import kit.edu.kastel.Controller;
import kit.edu.kastel.commands.Command;

/**
 * To debug the Path. Only for Dev.
 *
 * @author ufvgn
 */
public class DebugPathCommand extends Command {
    private static final String COMMANDNAME = "debug-path";

    /**
     * set up the Path Command.
     */
    public DebugPathCommand() {
        super(COMMANDNAME);
    }

    @Override
    public void execute(String[] args, Controller controller) {
        controller.printWayGraph();
    }
}
