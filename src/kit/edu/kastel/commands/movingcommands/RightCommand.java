package kit.edu.kastel.commands.movingcommands;

import kit.edu.kastel.rover.Direction;

/**
 * represents the "right" command.
 *
 * @author ufvgn
 */
public class RightCommand extends MovingCommands {
    private static final String COMMANDNAME = "right";
    private static final Direction DIRECTION = Direction.RIGHT;

    /**
     * set up the right commend.
     */
    public RightCommand() {
        super(COMMANDNAME, DIRECTION);
    }
}
