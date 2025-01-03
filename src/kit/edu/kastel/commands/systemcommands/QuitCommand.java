package kit.edu.kastel.commands.systemcommands;

import kit.edu.kastel.Controller;
import kit.edu.kastel.commands.Command;

/**
 * represents the Quit command. if it used the Protcol will be end.
 *
 * @author ufvgn
 */
public class QuitCommand extends Command {
    private static final String COMMENDNAME = "quit";
    /**
     * Set up the qiut Commend.
     */
    public QuitCommand() {
        super(COMMENDNAME);
    }
    @Override
    public void execute(String[] args, Controller controller) {
        controller.setQuit(true);
    }
}
