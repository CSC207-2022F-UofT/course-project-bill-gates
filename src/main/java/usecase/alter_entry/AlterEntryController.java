package usecase.alter_entry;

import billgates.database.QueryEntryData;

public class AlterEntryController {
    private final AlterEntryInputPort useCase;

    public AlterEntryController(AlterEntryInputPort useCase) {
        this.useCase = useCase;
    }

    public void alter_Entry(int billId, int entry_id, Object new_value,String alter_column) {
        this.useCase.alterEntry(billId, entry_id,new_value,alter_column);
    }
}
