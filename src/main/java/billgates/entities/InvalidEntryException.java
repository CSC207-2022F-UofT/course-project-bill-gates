package billgates.entities;

/**
 * This exception represents that the program failed to create an entry.
 */
public class InvalidEntryException extends RuntimeException {

    public InvalidEntryException(String reason) {
        super("Failed to create an entry: " + reason);
    }
}
