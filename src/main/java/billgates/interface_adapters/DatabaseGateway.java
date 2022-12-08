package billgates.interface_adapters;

import billgates.entities.Entry;
import billgates.entities.QueryUserData;
import billgates.entities.SplitterEntry;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Clean Architecture Layer: Application Business Rules
 * Design Pattern: Strategy
 * This class is being used by multiple use cases to retrieve, insert, and modify information
 * that is used for the application.
 * <p>
 * We can implement the database gateway in multiple different types of databases
 * The current implemented database by default, uses MySQL Database
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
     * @return a list of Entry objects
     * @see Entry
     */
    List<Entry> getBillData(int billId);

    /**
     * Obtains information from the whole Split Bill table associated with splitBillId
     *
     * @param splitBillId the splitBillId that we wish to query
     * @return a list of SplitterEntry objects
     * @see SplitterEntry
     */
    List<SplitterEntry> getSplitBillData(int splitBillId);

    /**
     * Obtains information from the whole bill table associated with the billId
     * with a specific date range
     *
     * @param billId    the bill ID that we are trying to obtain information from
     * @param startDate a ZonedDateTime object that marks the start date of this query
     * @param endDate   a ZonedDateTime object that marks the end date of this query
     * @return a list of Entry that is within the specified date range
     * @see Entry
     */
    List<Entry> getBillData(int billId, ZonedDateTime startDate, ZonedDateTime endDate);

    /**
     * Obtains information from a specific entry (using entryId)
     * in a specific bill (using billId)
     * the result information is bundled by the Entry class
     *
     * @param billId  the bill ID that we are trying to obtain the entry from
     * @param entryId the entry ID that we are trying to obtain information from
     * @return an Entry with the information
     * returns null if the entry_id being queried doesn't exist in the bill
     * @see Entry
     */
    Entry getEntryData(int billId, int entryId);

    /**
     * Obtains information from a specific entry (using entryId, THE ID OF THE SPLIT ENTRY)
     * in a specific SPLIT bill (using billId)
     * the result entry information is bundled by SplitterEntry class
     *
     * @param billId  the bill ID that we are trying to obtain the entry from
     * @param entryId the entry ID that we are trying to obtain information from
     * @return a SplitterEntry that contains the information of the entry queried
     * returns null if the entry_id being queried doesn't exist in the bill
     * @see SplitterEntry
     */
    SplitterEntry getSplitEntryData(int billId, int entryId);

    /**
     * Inserts the entry into the specified bill (using billId) in the database
     *
     * @param billId the bill ID that we are trying to insert the entry into
     * @param entry  the Entry that we are trying to insert
     * @see Entry
     */
    void insertEntry(int billId, Entry entry);

    /**
     * Inserts the SPLIT entry into the specified SPLIT bill (using billId) in the database
     *
     * @param billId the SPLIT bill ID that we are trying to insert the entry into
     * @param entry  the SplitterEntry that we are trying to insert
     * @see SplitterEntry
     */
    void insertSplitEntry(int billId, SplitterEntry entry);

    /**
     * Inserts the user into the users table in the database
     *
     * @param user a QueryUserData object that we are trying to insert into the database
     * @see QueryUserData
     */
    void insertUser(QueryUserData user);

    /**
     * Deletes the <code>user</code> in the database.
     *
     * @param user the user to be deleted
     * @see QueryUserData
     */
    void deleteUser(QueryUserData user);

    /**
     * Deletes the <code>user</code> in the database.
     *
     * @param userid the user id of the user to be deleted
     */
    void deleteUser(int userid);

    /**
     * Deletes the <code>user</code> in the database.
     *
     * @param username the username of the user to be deleted
     */
    void deleteUser(String username);

    /**
     * Cleans the <code>user</code> in the database.
     * Drops all the tables related to the <code>user</code>, including the split bills.
     *
     * @param user the user to be cleaned
     */
    void cleanUser(QueryUserData user);

    /**
     * Deletes the entry with entryId from the specified bill (using billId)
     *
     * @param billId  the id of the bill that we are trying to remove the specific entry from
     * @param entryId the id of entry that we are trying to delete
     */
    void deleteEntry(int billId, int entryId);

    /**
     * Deletes the split entry with SPLIT entryId from the specified SPLIT bill (using billId)
     *
     * @param billId  the id of the SPLIT bill that we are trying to remove the specific entry from
     * @param entryId the id of SPLIT entry that we are trying to delete
     */
    void deleteSplitEntry(int billId, int entryId);

    /**
     * Modifies the entry with entryId from the specified bill (using billId)
     * modifies the value in the specified column to be the newValue
     *
     * @param billId   the id of the bill that we are trying to modify the specific entry from
     * @param entryId  the id of entry that we are trying to delete
     * @param column   the column in the entry that we are trying to modify
     * @param newValue the new value that will be overwritten in the column
     */
    void modifyEntry(int billId, int entryId, String column, String newValue);

    /**
     * Modifies the SPLIT entry with SPLIT entryId from the specified bill (using billId)
     * modifies the value in the specified column to be the newValue
     *
     * @param billId   the id of the bill that we are trying to modify the specific entry from
     * @param entryId  the id of entry that we are trying to delete
     * @param column   the column in the entry that we are trying to modify
     * @param newValue the new value that will be overwritten in the column
     */
    void modifySplitEntry(int billId, int entryId, String column, String newValue);

    /**
     * Overwrites the entry completely with the entry_id contained in entry
     * in the bill specified by billId
     *
     * @param billId the id of the bill that we are trying to modify the specific entry from
     * @param entry  a QueryEntryData object that contains all the information of the new entry
     */
    void modifyEntry(int billId, Entry entry);

    /**
     * Overwrites the SPLIT entry completely with the SPLIT entry_id contained in entry
     * in the bill specified by billId
     *
     * @param billId the id of the SPLIT bill that we are trying to modify the specific entry from
     * @param entry  a QuerySplitEntryData object that contains all the information of the new entry
     */
    // This warning shouldn't be resolved because it is necessary to have this method.
    void modifySplitEntry(int billId, SplitterEntry entry);

    /**
     * Creates a bill with the table name "bill_{billId}" in our database
     * contains columns with types as specified in README.md
     *
     * @param billId the id of the bill that we are trying create
     */
    void createBillTable(int billId);

    /**
     * Drops a bill with the table name "bill_{billId}" in our database.
     *
     * @param billId the id of the bill that we are trying drop
     */
    void dropBillTable(int billId);

    /**
     * Creates a split bill with the table name "bill_{userId}_{billId}" in our database
     * the userId can be obtained as the User class is designed using a Singleton design pattern
     *
     * @param billId the id of the split bill that we are trying create
     * @see billgates.entities.User
     */
    void createSplitBillTable(int billId);

    /**
     * Drops a split bill with the table name "bill_{userId}_{billId}" in our database.
     *
     * @param billId the id of the split bill that we are trying create
     */
    void dropSplitBillTable(int billId);

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
