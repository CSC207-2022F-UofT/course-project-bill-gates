package billgates.use_cases.insert_entry;

public class InsertEntryController {

    private final InsertEntryInputPort useCase;

    public InsertEntryController(InsertEntryInputPort useCase) {
        this.useCase = useCase;
    }

    public void insert(InsertEntryRequestModel model) {
        this.useCase.insertEntry(model);
    }
}
