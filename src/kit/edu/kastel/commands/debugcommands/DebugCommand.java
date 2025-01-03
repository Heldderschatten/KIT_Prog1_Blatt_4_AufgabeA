package kit.edu.kastel.commands.debugcommands;

import kit.edu.kastel.Controller;
import kit.edu.kastel.commands.Command;

/**
 * contains the Debug command Only for dev. .
 * <p>
 *     Nähere Deatils
 * </p>
 * @author ufvgn
 */
//todo verbessern des JavaDocs (Klasse)
public class DebugCommand extends Command {
    private static final String COMMANDNAME  = "debug";

    /**
     * Setup the debug command.
     */
    public DebugCommand() {
        super(COMMANDNAME);
    }
    @Override
    public void execute(String[] args, Controller controller) {
        controller.printMap();
    }


}
