package kit.edu.kastel.commands.movingcommands;

import kit.edu.kastel.rover.Direction;

/**
 * Represents the "down" command.
 *
 * @author ufvgn
 */
public class DownCommand extends MovingCommands {
    private static final String COMMANDNAME = "down";
    private static final Direction DIRECTION = Direction.DOWN;

    /**
     * set up the down commend.
     */
    public DownCommand() {
        super(COMMANDNAME, DIRECTION);
    }
}
