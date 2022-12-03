package billgates.entities;

/**
 * Clean Architecture Layer: Enterprise Business Rules
 * This exception represents that the program failed to create an entry.
 *
 * @author Scott
 */
public class InvalidEntryException extends RuntimeException {

    public InvalidEntryException(String reason) {
        super("Failed to create an entry: " + reason);
    }
}
