package billgates.use_cases.delete_entry;

/**
 * Clean Architecture Layer: Interface Adapters
 * This class serves as the controller of the Delete Entry Use Case.
 * It is only responsible for accepting the input (<code> entryID </code>) from the user and
 * invoke the corresponding use case.
 *
 * @author Ellen, Scott
 * @see DeleteEntryInputPort
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
