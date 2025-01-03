package kit.edu.kastel.pathfindingalgo;

import kit.edu.kastel.commands.movingcommands.UpCommand;
import kit.edu.kastel.commands.movingcommands.DownCommand;
import kit.edu.kastel.commands.movingcommands.LeftCommand;
import kit.edu.kastel.commands.movingcommands.RightCommand;
import kit.edu.kastel.commands.movingcommands.MovingCommands;
import kit.edu.kastel.rover.Direction;

import java.util.ArrayList;

/**
 * This Class represent a singel way on the Graph.
 *
 * @author ufvgn
 */
public class Way {
    int distance;
    ArrayList<MovingCommands> commands = new ArrayList<>();

    /**
     * set up a Empty way.
     */
    public Way() {
        distance = 0;
    }

    /**
     * Setr up the Way from a statring way.
     * @param commands
     */
    public Way(ArrayList<MovingCommands> commands) {
        this.commands = (ArrayList<MovingCommands>) commands.clone();
        this.distance = this.commands.size();
    }

    /**
     * Add a singel move to the command list.
     * @param direction is the Direction of the Move.
     */
    public void addMove(Direction direction) {
        switch (direction) {
            case UP:
                commands.add(new UpCommand());
                break;
            case DOWN:
                commands.add(new DownCommand());
                break;
            case LEFT:
                commands.add(new LeftCommand());
                break;
            case RIGHT:
                commands.add(new RightCommand());
                break;
            default:
                return;
        }
        distance++;
    }

    /**
     * Return the size of the Way.
     * @return {@code int} the size of the Way
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Return the Arraylist from type {@link MovingCommands} they represent the Moves.
     * @return Array list
     */
    public ArrayList<MovingCommands> getCommands() {
        return commands;
    }
}
