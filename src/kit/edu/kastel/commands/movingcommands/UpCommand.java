package kit.edu.kastel.commands.movingcommands;

import kit.edu.kastel.rover.Direction;

/**
 * represents the  "up" Command.
 *
 * @author ufvgn
 */
public class UpCommand extends MovingCommands {
    private static final String COMMANDNAME = "up";
    private static final Direction DIRECTION = Direction.UP;

    /**
     * set up the Up commend.
     */
    public UpCommand() {
        super(COMMANDNAME, DIRECTION);
    }
}
