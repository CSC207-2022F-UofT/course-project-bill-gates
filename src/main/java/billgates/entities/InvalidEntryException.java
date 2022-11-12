package billgates.entities;

public class InvalidEntryException extends RuntimeException {

    public InvalidEntryException(String reason) {
        super("Failed to create an entry: " + reason);
    }
}
