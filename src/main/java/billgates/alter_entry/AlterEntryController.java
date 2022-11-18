package billgates.alter_entry;

import billgates.database.QueryEntryData;

public class AlterEntryController {
    private final AlterEntryInputPort useCase;

    public AlterEntryController(AlterEntryInputPort useCase) {
        this.useCase = useCase;
    }

    public void update(int billId, QueryEntryData entry) {
        this.useCase.alterEntry(billId, entry);
    }
}
