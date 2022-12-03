package billgates.use_cases.insert_entry;

/**
 * Clean Architecture Layer: Interface Adapters
 * This class serves as the controller of the Insert Entry Use Case.
 * It is only responsible for accepting the input (<code>model</code>) from the user and
 * invoke the corresponding use case.
 *
 * @author Ruby
 * @see InsertEntryInputPort
 */

public class InsertEntryController {

    private final InsertEntryInputPort useCase;

    public InsertEntryController(InsertEntryInputPort useCase) {
        this.useCase = useCase;
    }

    public void insert(InsertEntryRequestModel model) {
        this.useCase.insertEntry(model);
    }
}
