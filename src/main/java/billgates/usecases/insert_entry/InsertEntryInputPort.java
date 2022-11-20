package billgates.usecases.insert_entry;

import billgates.database.QueryEntryData;

public interface InsertEntryInputPort {
    void insertEntry(int billId, QueryEntryData entry);
}
