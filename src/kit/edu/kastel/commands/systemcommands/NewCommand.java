package kit.edu.kastel.commands.systemcommands;

import kit.edu.kastel.Controller;
import kit.edu.kastel.commands.Command;
import kit.edu.kastel.pathfindingalgo.Graph;

import java.util.Scanner;

/**
 * Represents the new command.
 * <p>
 *     The new Command need to integer paramters to run!
 * </p>
 *
 * @author ufvgn
 */
public class NewCommand extends Command {
    private static final String COMMANDNAME = "new";

    /**
     * Set up the NewCommand and give the super class the name of the Command.
     */
    public NewCommand() {
        super(COMMANDNAME);
    }

    @Override
    public void execute(String[] args, Controller controller) {
        int[] intArgs = parseTheArguments(args);
        //Check command is vaild
        if (args.length == 2) {
            controller.printInvalidMassage(ERRORMASSEGE); // Massge declaed in super class
        }

        Graph sendingGraph = new Graph(intArgs[1], intArgs[0]);
        try (Scanner scanner = new Scanner(System.in)) {
            for (int height = 0; height < intArgs[1]; height++) {
                String sendLine = scanner.nextLine();
                if (!sendingGraph.setOneLine(height, sendLine.toCharArray())) {
                    controller.printInvalidMassage(ERRORMASSEGE);
                }
            }
        }
        if (!sendingGraph.graphIsVaild()) {
            controller.printInvalidMassage(ERRORMASSEGE);
        } else {
            controller.setCurrentGraph(sendingGraph);
        }
    }
    private int[] parseTheArguments(String[] toParseArg) {
        int[] args = new int[2];
        //the parsing args start by 1 because 0 is the Command
        args[0] = Integer.parseInt(toParseArg[1]);
        args[1] = Integer.parseInt(toParseArg[2]);
        return args;
    }


}
