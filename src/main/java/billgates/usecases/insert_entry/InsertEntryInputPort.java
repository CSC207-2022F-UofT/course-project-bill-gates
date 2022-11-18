package billgates.usecases.insert_entry;

import billgates.database.QueryEntryData;

public interface InsertEntryInputPort {
    void insertEntry(QueryEntryData entry);
}
