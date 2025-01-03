package kit.edu.kastel.commands.movingcommands;

import kit.edu.kastel.rover.Direction;

/**
 * represents the "left" command.
 *
 * @author ufvgn
 */
public class LeftCommand extends MovingCommands {
    private static final String COMMANDNAME = "left";
    private static final Direction DIRECTION = Direction.LEFT;

    /**
     * set up the left commend.
     */
    public LeftCommand() {
        super(COMMANDNAME, DIRECTION);
    }
}
