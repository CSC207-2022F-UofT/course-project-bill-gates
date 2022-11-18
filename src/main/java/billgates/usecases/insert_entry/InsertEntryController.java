package billgates.usecases.insert_entry;

import billgates.database.QueryEntryData;

public class InsertEntryController {
    private final InsertEntryInputPort useCase;

    public InsertEntryController(InsertEntryInputPort useCase) {
        this.useCase = useCase;
    }

    public void insert(QueryEntryData entry) {
        this.useCase.insertEntry(entry);
    }
}
