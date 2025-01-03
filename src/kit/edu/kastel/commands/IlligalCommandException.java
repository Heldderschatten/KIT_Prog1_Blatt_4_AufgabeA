package kit.edu.kastel.commands;

/**
 * Thors a new Exception if the Command from Ground Control is not oke.
 *
 * @author ufvgn
 */
//todo verbessern des JavaDocs Kommentars
public class IlligalCommandException extends Exception {
    private static final String MESSAGE = "ERROR";

    /**
     * The Construtor of the excetion.
     *
     * @param message a given Massege but not used in the method
     */
    public IlligalCommandException(String message) {
        super(MESSAGE);
    }
}
