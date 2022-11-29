package billgates.interface_adapters;

import billgates.database.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Clean Architecture Layer: Application Business Rules
 *
 * @author Ray, Scott
 */
public interface DatabaseGateway {

    /**
     * Establishes the connection to the remote MySQL Database with credentials in config.properties
     * It should be called before doing anything on the DatabaseGateway
     */
    void initializeConnection();

    /**
     * Obtains the information of a specific username
     * (username, password, userId, billId) bundled by QueryUserData class
     *
     * @param username the username of the user that we wish to obtain the information from
     * @return a class that contains the user information queried
     * returns null if the username being queried doesn't exist in users
     * @see QueryUserData
     */
    QueryUserData getUserData(String username);

    /**
     * Obtains all the information of ALL users within the database
     * (username, password, userId, billId) bundled by QueryUserData class
     *
     * @return a list of QueryUserData that contains all the information in the database
     * @see QueryUserData
     */
    List<QueryUserData> getUserData();

    /**
     * Obtains information from the whole bill table associated with the billId
     *
     * @param billId the billId that we wish to query
     * @return a QueryBillData that encapsulates all the QueryEntryData within it
     * @see QueryBillData
     */
    QueryBillData getBillData(int billId);

    /**
     * Obtains information from the whole Split Bill table associated with splitBillId
     *
     * @param splitBillId the splitBillId that we wish to query
     * @return a QuerySplitBillData that encapsulates all the QuerySplitEntryData within it
     * @see QuerySplitBillData
     */
    QuerySplitBillData getSplitBillData(int splitBillId);

    /**
     * Obtains information from the whole bill table associated with the billId
     * with a specific date range
     *
     * @param billId the bill ID that we are trying to obtain information from
     * @param startDate a ZonedDateTime object that marks the start date of this query
     * @param endDate a ZonedDateTime object that marks the end date of this query
     * @return a QueryBillData that encapsulates all the QueryEntryData within the date range
     * @see QueryBillData
     */
    QueryBillData getBillData(int billId, ZonedDateTime startDate, ZonedDateTime endDate);

    /**
     * Obtains information from a specific entry (using entryId)
     * in a specific bill (using billId)
     * the result information is bundled by QueryEntryData class
     *
     * @param billId the bill ID that we are trying to obtain the entry from
     * @param entryId the entry ID that we are trying to obtain information from
     * @return a QueryEntryData that contains the information of the entry queried
     * returns null if the entry_id being queried doesn't exist in the bill
     * @see QueryEntryData
     */
    QueryEntryData getEntryData(int billId, int entryId);

    /**
     * Obtains information from a specific entry (using entryId, THE ID OF THE SPLIT ENTRY)
     * in a specific SPLIT bill (using billId)
     * the result entry information is bundled by QuerySplitEntryData class
     *
     * @param billId the bill ID that we are trying to obtain the entry from
     * @param entryId the entry ID that we are trying to obtain information from
     * @return a QueryEntryData that contains the information of the entry queried
     * returns null if the entry_id being queried doesn't exist in the bill
     * @see QuerySplitEntryData
     */
    QuerySplitEntryData getSplitEntryData(int billId, int entryId);

    /**
     * Inserts the entry into the specified bill (using billId) in the database
     *
     * @param billId the bill ID that we are trying to insert the entry into
     * @param entry the QueryEntryData that we are trying to insert
     * @see QueryEntryData
     */
    void insertEntry(int billId, QueryEntryData entry);

    /**
     * Inserts the SPLIT entry into the specified SPLIT bill (using billId) in the database
     *
     * @param billId the SPLIT bill ID that we are trying to insert the entry into
     * @param entry the QuerySplitEntryData that we are trying to insert
     * @see QuerySplitEntryData
     */
    void insertSplitEntry(int billId, QuerySplitEntryData entry);

    /**
     * Inserts the user into the users table in the database
     *
     * @param user a QueryUserData object that we are trying to insert into the database
     * @see QueryUserData
     */
    void insertUser(QueryUserData user);

    /**
     * Deletes the entry with entryId from the specified bill (using billId)
     *
     * @param billId the id of the bill that we are trying to remove the specific entry from
     * @param entryId the id of entry that we are trying to delete
     */
    void deleteEntry(int billId, int entryId);

    /**
     * Deletes the split entry with SPLIT entryId from the specified SPLIT bill (using billId)
     *
     * @param billId the id of the SPLIT bill that we are trying to remove the specific entry from
     * @param entryId the id of SPLIT entry that we are trying to delete
     */
    void deleteSplitEntry(int billId, int entryId);

    /**
     * Modifies the entry with entryId from the specified bill (using billId)
     * modifies the value in the specified column to be the newValue
     *
     * @param billId the id of the bill that we are trying to modify the specific entry from
     * @param entryId the id of entry that we are trying to delete
     * @param column the column in the entry that we are trying to modify
     * @param newValue the new value that will be overwritten in the column
     */
    void modifyEntry(int billId, int entryId, String column, String newValue);

    /**
     * Modifies the SPLIT entry with SPLIT entryId from the specified bill (using billId)
     * modifies the value in the specified column to be the newValue
     *
     * @param billId the id of the bill that we are trying to modify the specific entry from
     * @param entryId the id of entry that we are trying to delete
     * @param column the column in the entry that we are trying to modify
     * @param newValue the new value that will be overwritten in the column
     */
    void modifySplitEntry(int billId, int entryId, String column, String newValue);

    /**
     * Overwrites the entry completely with the entry_id contained in entry
     * in the bill specified by billId
     *
     * @param billId the id of the bill that we are trying to modify the specific entry from
     * @param entry a QueryEntryData object that contains all the information of the new entry
     */
    void modifyEntry(int billId, QueryEntryData entry);

    /**
     * Overwrites the SPLIT entry completely with the SPLIT entry_id contained in entry
     * in the bill specified by billId
     *
     * @param billId the id of the SPLIT bill that we are trying to modify the specific entry from
     * @param entry a QuerySplitEntryData object that contains all the information of the new entry
     */
    void modifySplitEntry(int billId, QuerySplitEntryData entry);

    /**
     * Creates a bill with the table name "bill_{billId}" in our database
     * contains columns with types as specified in README.md
     *
     * @param billId the id of the bill that we are trying create
     */
    void createBillTable(int billId);

    /**
     * Creates a split bill with the table name "bill_{userId}_{billId}" in our database
     * the userId can be obtained as the User class is designed using a Singleton design pattern
     *
     * @param billId the id of the split bill that we are trying create
     * @see billgates.entities.User
     */
    void createSplitBillTable(int billId);

    /**
     * Creates the user table with the table name "users" in our database
     * the column types will be the same as specified in README.md
     */
    void createUsersTable();

    /**
     * Sets the userId in the Database Gateway so that the application
     * can insert Split Bills accordingly
     */
    void setUserId(int userId);
}
