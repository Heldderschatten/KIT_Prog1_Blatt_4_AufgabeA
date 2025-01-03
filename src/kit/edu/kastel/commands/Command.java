package kit.edu.kastel.commands;

import kit.edu.kastel.Controller;


/**
 * This is abstarct mother class, and give the stucturer for every other command.
 *
 * <p>
 *     Nähere beschreibung
 * </p>
 *
 * @author ufvgn
 */
//todo bessers JavaDocs Klasse
public abstract class Command {
    protected static final String ERRORMASSEGE = "ERROR.";
    protected final String commandName;
    /**
     * set the Name of the command in the super class.
     * @param commandName the name of the Command
     */
    //todo javaDoc des consturtors verbessern
    public Command(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Execute the command to Overwrite.
     * @param args the given parameters {@code int}
     * @param controller is the controller for the command
     */
    public abstract void execute(String[] args, Controller controller);
    //Getter and Setter
    /**
     * Return the Name of the Command.
     * @return  {@code String} with the name of the command
     */
    public String getCommandName() {
        return commandName;
    }

}
