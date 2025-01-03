package kit.edu.kastel.commands.movingcommands;

import kit.edu.kastel.Controller;
import kit.edu.kastel.commands.Command;
import kit.edu.kastel.rover.Direction;

/**
 * Represents the MovoiungCommands.
 *
 * @author ufvgn
 */
public abstract class MovingCommands extends Command {
    private final Direction direction;

    /**
     * set the Name of the command in the super class.
     *
     * @param commandName the name of the Command
     * @param direction   is the Dirvetion ({@code Mapsymbol}) of the move
     */
    public MovingCommands(String commandName, Direction direction) {
        super(commandName);
        this.direction = direction;
    }

    @Override
    public void execute(String[] args, Controller controller) {
        if (args.length == 2) {
            try {
                int count = Integer.parseInt(args[1]);
                controller.moveRover(direction, count);
            } catch (NumberFormatException e) {
                controller.printInvalidMassage(ERRORMASSEGE);
            }
        } else {
            controller.moveRover(direction, 1);
        }
    }

    /**
     * Return the direction from the class.
     * @return returns a {@link Direction}
     */
    public Direction getDirection() {
        return direction;
    }
}
