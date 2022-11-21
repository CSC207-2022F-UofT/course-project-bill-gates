package billgates.interface_adapters;

import billgates.database.QueryBillData;
import billgates.database.QueryEntryData;
import billgates.database.QueryUserData;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Clean Architecture Layer: Application Business Rules
 *
 * @author Ray, Scott
 */
public interface DatabaseGateway {

    void initializeConnection();

    QueryUserData getUserData(String username);

    List<QueryUserData> getUserData();

    QueryBillData getBillData(int billId);

    QueryBillData getBillData(int billId, ZonedDateTime startDate, ZonedDateTime endDate);

    QueryEntryData getEntryData(int billId, int entryId);

    void insertEntry(int billId, QueryEntryData entry);

    void insertUser(QueryUserData user);

    void deleteEntry(int billId, int entryId);

    // This uses the ID in entry to overwrite a specific column
    void modifyEntry(int billId, int entryId, String column, String newValue);

    // This uses the ID in entry to overwrite the whole entry with the new entry
    void modifyEntry(int billId, QueryEntryData entry);

    // Creates a new bill with the pre-specified columns
    void createBillTable(int billId);

    // Creates a new splitBill with the pre-specified columns
    void createSplitBillTable(int billId);

    // Creates the user table
    void createUsersTable();

    void setUserId(int userId);
}
