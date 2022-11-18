package billgates.usecases.alter_entry;

import billgates.database.QueryEntryData;

public interface AlterEntryInputPort {
    void alterEntry(int billId, QueryEntryData entry);
}
