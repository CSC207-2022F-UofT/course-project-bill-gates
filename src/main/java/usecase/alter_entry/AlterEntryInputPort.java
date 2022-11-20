package usecase.alter_entry;

import billgates.database.QueryEntryData;

public interface AlterEntryInputPort {
    void alterEntry(int billId, int entry_id, Object new_value,String alter_column);
}
