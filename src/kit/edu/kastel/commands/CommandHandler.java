package kit.edu.kastel.commands;

import kit.edu.kastel.Controller;
import kit.edu.kastel.commands.debugcommands.DebugCommand;
import kit.edu.kastel.commands.debugcommands.DebugPathCommand;
import kit.edu.kastel.commands.movingcommands.DownCommand;
import kit.edu.kastel.commands.movingcommands.LeftCommand;
import kit.edu.kastel.commands.movingcommands.RightCommand;
import kit.edu.kastel.commands.movingcommands.UpCommand;
import kit.edu.kastel.commands.systemcommands.NewCommand;
import kit.edu.kastel.commands.systemcommands.PathCommand;
import kit.edu.kastel.commands.systemcommands.QuitCommand;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Handle the Commands is the interface between the Programm and the commands.
 *
 * @author ufvgn
 */

// todo verbessern JavaDoc Klassenkommentar
public class CommandHandler {
    private static final String REGEX = " ";
    private Controller controller;
    private HashMap<String, Command> commands;
    private boolean stopProgramm;

    /**
     * set up the handler and make it redy to readCommands.
     * @param controller
     */
    public CommandHandler(Controller controller) {
        this.controller = controller;
        this.commands = new HashMap<>();
        initCommands();
        run();
    }
    private void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!controller.isQuit()) {
                String commandString = scanner.nextLine();
                String[] args = commandString.split(REGEX);
                if (!commands.containsKey(args[0])) {
                    controller.printInvalidMassage(Command.ERRORMASSEGE); //todo Datenkaspelung
                    continue;
                }
                commands.get(args[0]).execute(args, controller);
            }
        }
    }
    private void initCommands() {
        //Add System Commands
        addCommand(new NewCommand());
        addCommand(new QuitCommand());
        addCommand(new PathCommand());
        //Add Moving Commands
        addCommand(new UpCommand());
        addCommand(new DownCommand());
        addCommand(new RightCommand());
        addCommand(new LeftCommand());
        //Add debug Commands
        addCommand(new DebugCommand());
        addCommand(new DebugPathCommand());
    }

    private void addCommand(Command command) {
        this.commands.put(command.getCommandName(), command);
    }
}
