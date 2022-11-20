package billgates.usecases.insert_entry;

import billgates.database.QueryEntryData;

public class InsertEntryController {
    private final InsertEntryInputPort useCase;

    public InsertEntryController(InsertEntryInputPort useCase) {
        this.useCase = useCase;
    }

    public void insert(int billId, QueryEntryData entry) {
        this.useCase.insertEntry(billId, entry);
    }
}
