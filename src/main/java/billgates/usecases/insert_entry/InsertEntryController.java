package billgates.usecases.insert_entry;

import billgates.database.QueryEntryData;

public class InsertEntryController {
    private final InsertEntryInputPort useCase;
    private final QueryEntryData entry;

    public InsertEntryController(InsertEntryInputPort useCase, QueryEntryData entry) {
        this.useCase = useCase;
        this.entry = entry;
    }

    public void insert() {
        this.useCase.insertEntry(this.entry);
    }
}
