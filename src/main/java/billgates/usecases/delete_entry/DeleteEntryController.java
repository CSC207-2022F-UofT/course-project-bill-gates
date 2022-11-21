package billgates.usecases.delete_entry;

/**
 * Clean Architecture Layer: Interface Adapters
 *
 * @author Ellen, Scott
 */
public class DeleteEntryController {

    private final DeleteEntryInputPort useCase;

    public DeleteEntryController(DeleteEntryInputPort useCase) {
        this.useCase = useCase;
    }

    public void delete(int entryId) {
        this.useCase.deleteEntry(entryId);
    }
}
