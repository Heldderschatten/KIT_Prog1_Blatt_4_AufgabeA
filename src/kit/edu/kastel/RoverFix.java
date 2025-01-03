package kit.edu.kastel;

import kit.edu.kastel.commands.CommandHandler;

/**
 * Some Commend.
 *
 * @author ufvgn
 */
public final class RoverFix {
    private RoverFix() { }

    /**
     * Entry point of the Rover fix programm.
     *
     * @param args given parameters
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        new CommandHandler(controller);
    }
}
