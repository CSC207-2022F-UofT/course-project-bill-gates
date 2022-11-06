package billgates.database;

import java.time.ZonedDateTime;

public interface DatabaseGateway {
    void initializeConnection();
    QueryUserData getUserData();
    QueryBillData getBillData(int billId);
    QueryBillData getBillData(int billId, ZonedDateTime startDate, ZonedDateTime endDate);
    QueryEntryData getEntryData(int billId, int entryId);
    void insertEntry(int billId, QueryEntryData entry);
    void deleteEntry(int billId, int entryId);

    // This uses the ID in entry to overwrite what was originally there
    void modifyEntry(int billId, QueryEntryData entry);

    // Creates a new bill with the pre-specified columns
    void createBillTable(int billId);

    void createUsersTable();
}
