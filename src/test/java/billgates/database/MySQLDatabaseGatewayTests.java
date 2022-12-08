package billgates.database;

import billgates.entities.Entry;
import billgates.entities.EntryBuilder;
import billgates.entities.QueryUserData;
import billgates.view.BillGatesUtilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MySQLDatabaseGatewayTests {
    public static final int TEST_TIMEOUT = 100000;
    public MySQLDatabaseGateway testGateway;
    public final int testBillID = 9999;
    public final int testSplitBillID = 1234;
    public final int testUserID = 9999;
    public Connection testConnection;

    @Before
    public void setUp() {
        this.testGateway = new MySQLDatabaseGateway();

        this.testGateway.setUserId(testUserID);

        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            try {
                this.testConnection = DriverManager.getConnection(
                        String.format("jdbc:mysql://%s/bill", url), user, password);

                Statement testStatement = this.testConnection.createStatement();

                // Checking if the test table already exists, if it does, then we want to remove it
                // for our tests
                String dropBillQuery = String.format("DROP TABLE IF EXISTS bill_%d",
                        this.testBillID);

                testStatement.execute(dropBillQuery);

                // Checking if the test user already exists, if it does, then we want to remove it
                // for our tests
                String checkUserQuery = String.format("SELECT * FROM users WHERE user_id=%d",
                        this.testUserID);

                ResultSet resultSet = testStatement.executeQuery(checkUserQuery);

                if (resultSet.next()) {
                    // If the user already exists there (Due to previous failed tests, we want to
                    // remove it and recreate)
                    String deleteUserQuery = String.format("DELETE FROM users WHERE user_id=%d",
                            this.testUserID);

                    testStatement.execute(deleteUserQuery);
                }

                // For each test, I want a table called bill_9999
                String createTableQuery = String.format("""
                        CREATE TABLE bill_%d
                        (
                            entry_id         INT             AUTO_INCREMENT
                                                             PRIMARY KEY,
                            value            DECIMAL(16, 2)  NOT NULL,
                            date             TIMESTAMP       NOT NULL,
                            currency         CHAR(3)         NOT NULL,
                            description      TEXT            NOT NULL,
                            `from`           TEXT            NOT NULL,
                            `to`             TEXT            NOT NULL,
                            location         TEXT            NOT NULL,
                            split_bill_id    INT             NOT NULL
                        )
                        """, this.testBillID);

                testStatement.execute(createTableQuery);

                String createEntryOneQuery = String.format("""
                        INSERT INTO bill_%d (entry_id, value, date, currency, description, `from`,
                        `to`, location, split_bill_id) VALUE
                        (1, 123.45, "1970-01-02 00:00:00", "CAD", "This is a test entry",
                        "Credit Card", "T&T Supermarket", "T&T Supermarket", -1)
                        """, this.testBillID);

                testStatement.execute(createEntryOneQuery);

                String createEntryTwoQuery = String.format("""
                        INSERT INTO bill_%d (entry_id, value, date, currency, description, `from`,
                        `to`, location, split_bill_id) VALUE
                        (2, 678.90, "1970-01-10 00:00:00", "CNY", "This is another test entry",
                        "Cash", "Burger King", "College Street", -1)
                        """, this.testBillID);

                testStatement.execute(createEntryTwoQuery);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            Statement statement = this.testConnection.createStatement();
            String dropTableQuery = String.format("DROP TABLE IF EXISTS bill_%d", this.testBillID);

            // Dropping the table that we created for testing
            statement.execute(dropTableQuery);
        } catch (SQLException ignored) {
        }
    }

    // Test for creating a users table
    @Test(timeout = TEST_TIMEOUT)
    public void testCreateUsersTable() {
        try {
            // After we call this method, we must have a users table that exists in the server
            this.testGateway.createUsersTable();

            Statement statement = this.testConnection.createStatement();

            String checkQuery = "SHOW COLUMNS FROM users";

            ResultSet resultSet = statement.executeQuery(checkQuery);

            ArrayList<String> obtainedColumnNames = new ArrayList<>();

            ArrayList<String> trueColumnNames = new ArrayList<>(List.of("user_id",
                    "username",
                    "password",
                    "bill_id"));

            while (resultSet.next()) {
                obtainedColumnNames.add(resultSet.getString("Field"));
            }

            // Tests if the obtained list of Users column names
            assertEquals(obtainedColumnNames, trueColumnNames);
        } catch (RuntimeException | SQLException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    // Test for creating a users table
    @Test(timeout = TEST_TIMEOUT)
    public void testGetUserData() {
        try {
            // This warning shouldn't be resolved: this is just a test to check if the method fails.
            List<QueryUserData> obtainedUsers = this.testGateway.getUserData();
            // There's not much we can test on this method, as the users list grows in size as
            // people use our application
            // So we will just test if there is a runtime exception being raised
        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            // Only tests if we obtain an error
            fail();
        }
    }

    // Test for creating a bill table with the ID being testBillID
    @Test(timeout = TEST_TIMEOUT)
    public void testCreateBillTable() {
        try {
            Statement testStatement = this.testConnection.createStatement();

            String dropTableQuery = String.format("DROP TABLE IF EXISTS bill_%d", this.testBillID);
            // Remove the table created by @before first
            testStatement.execute(dropTableQuery);

            // Test create the table
            this.testGateway.createBillTable(this.testBillID);

            String checkQuery = String.format("SHOW COLUMNS FROM bill_%d", this.testBillID);

            ResultSet resultSet = testStatement.executeQuery(checkQuery);

            ArrayList<String> obtainedColumnNames = new ArrayList<>();

            ArrayList<String> trueColumnNames = new ArrayList<>(List.of("entry_id",
                    "value",
                    "date",
                    "currency",
                    "description",
                    "from",
                    "to",
                    "location",
                    "split_bill_id"));

            while (resultSet.next()) {
                obtainedColumnNames.add(resultSet.getString("Field"));
            }

            // Tests if the obtained list of Bill column names
            assertEquals(obtainedColumnNames, trueColumnNames);

        } catch (RuntimeException | SQLException e) {
            // Fails the test whenever we encounter an Error
            // When trying to run getBillData method on BillID testBillID
            fail();
        }
    }

    // Test for creating a splitBillTable
    @Test(timeout = TEST_TIMEOUT)
    public void testCreateSplitBillTable() {
        try {
            Statement testStatement = this.testConnection.createStatement();


            // Dropping the table if it already exists (due to previous failed tests)
            String dropTableQuery = String.format("DROP TABLE IF EXISTS bill_%d_%d",
                    this.testBillID, this.testSplitBillID);

            testStatement.execute(dropTableQuery);


            // Test create the table
            this.testGateway.createSplitBillTable(this.testSplitBillID);

            String checkQuery = String.format("SHOW COLUMNS FROM bill_%d_%d", this.testBillID,
                    this.testSplitBillID);

            ResultSet resultSet = testStatement.executeQuery(checkQuery);

            ArrayList<String> obtainedColumnNames = new ArrayList<>();

            ArrayList<String> trueColumnNames = new ArrayList<>(List.of("entry_id",
                    "value",
                    "date",
                    "currency",
                    "description",
                    "from",
                    "to",
                    "location",
                    "payee",
                    "paid_back"
            ));

            while (resultSet.next()) {
                obtainedColumnNames.add(resultSet.getString("Field"));
            }

            // Tests if the obtained list of Bill column names
            assertEquals(obtainedColumnNames, trueColumnNames);

            // Drop the split bill we just created
            dropTableQuery = String.format("DROP TABLE bill_%d_%d", this.testBillID,
                    this.testSplitBillID);

            testStatement.execute(dropTableQuery);

        } catch (RuntimeException | SQLException e) {
            // Fails the test whenever we encounter an Error
            // When trying to run getBillData method on BillID testBillID
            fail();
        }
    }

    // Test for inserting a new user into the users table
    @Test(timeout = TEST_TIMEOUT)
    public void testInsertUser() {
        try {
            String testUsername = "TestUser";
            String testPassword = "TestUserPassword";

            QueryUserData testUser = new QueryUserData(this.testUserID,
                    this.testBillID,
                    testUsername,
                    testPassword);

            // Inserts the user into the database
            this.testGateway.insertUser(testUser);

            // Checking if we actually inserted
            Statement testStatement = this.testConnection.createStatement();

            String getNewlyCreatedUser = String.format("SELECT * FROM users WHERE user_id = %d",
                    testUser.getUserID());

            ResultSet result = testStatement.executeQuery(getNewlyCreatedUser);

            int obtainedUserID = -1;
            int obtainedUserBillID = -1;
            String obtainedUsername = "";
            String obtainedPassword = "";

            while (result.next()) {
                obtainedUserID = result.getInt("user_id");
                obtainedUserBillID = result.getInt("bill_id");
                obtainedUsername = result.getString("username");
                obtainedPassword = result.getString("password");
            }

            assertEquals(this.testUserID, obtainedUserID);
            assertEquals(this.testBillID, obtainedUserBillID);
            assertEquals(testUsername, obtainedUsername);
            assertEquals(testPassword, obtainedPassword);

            // If every test passed, try to remove the user we created
            String removeNewlyCreatedUser = String.format("DELETE FROM users WHERE user_id = %d",
                    testUser.getUserID());

            testStatement.execute(removeNewlyCreatedUser);

        } catch (RuntimeException | SQLException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    // Test inserting an entry with all attributes
    @Test(timeout = TEST_TIMEOUT)
    public void testInsertEntryAllAttributes() {
        try {
            int testID = 3;
            ZonedDateTime testDate = ZonedDateTime.of(2022,
                    1,
                    1,
                    0,
                    0,
                    0,
                    0,
                    ZoneId.systemDefault());
            double testValue = 100.0;
            String testCurrency = "NTD";
            String testDescription = "This is the third example entry";
            String testFrom = "Credit Card";
            String testTo = "Amazon";
            String testLocation = "Online";
            int testSplitBillID = -1;

            Entry testEntry3 = new EntryBuilder()
                    .setId(testID)
                    .setDate(testDate)
                    .setValue(testValue)
                    .setCurrency(testCurrency)
                    .setDescription(testDescription)
                    .setFrom(testFrom)
                    .setTo(testTo)
                    .setLocation(testLocation)
                    .setSplitterBillId(testSplitBillID)
                    .buildEntry();

            this.testGateway.insertEntry(this.testBillID, testEntry3);

            Statement testStatement = this.testConnection.createStatement();

            String getNewlyCreatedEntry = String.format("SELECT * FROM bill_%d WHERE entry_id = %d",
                    this.testBillID, testEntry3.getId().getAttribute());

            ResultSet result = testStatement.executeQuery(getNewlyCreatedEntry);

            int obtainedID = -1;
            double value = 0.0;
            String currency = "";
            String description = "";
            String from = "";
            String to = "";
            String location = "";
            ZonedDateTime zDate = ZonedDateTime.now();
            int splitBillID = -1;

            while (result.next()) {
                obtainedID = result.getInt("entry_id");
                value = result.getDouble("value");
                Timestamp date = result.getTimestamp("date");
                currency = result.getString("currency");
                description = result.getString("description");
                from = result.getString("from");
                to = result.getString("to");
                location = result.getString("location");
                splitBillID = result.getInt("split_bill_id");

                Instant i = Instant.ofEpochMilli(date.getTime());

                // We can pass in the different zones we want to convert in, and we can obtain the
                // value we want
                zDate = ZonedDateTime.ofInstant(i, ZoneId.systemDefault());
            }

            assertEquals(obtainedID, testID);
            assertEquals(value, testValue, 1e-8);
            assertEquals(zDate, testDate);
            assertEquals(currency, testCurrency);
            assertEquals(description, testDescription);
            assertEquals(from, testFrom);
            assertEquals(to, testTo);
            assertEquals(location, testLocation);
            assertEquals(splitBillID, testSplitBillID);


        } catch (RuntimeException | SQLException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    // Test for inserting an entry with only the ID, Date, Value
    @Test(timeout = TEST_TIMEOUT)
    public void testInsertEntryMainAttributes() {
        try {
            int testID = 4;
            ZonedDateTime testDate = ZonedDateTime.of(2022,
                    2,
                    1,
                    0,
                    0,
                    0,
                    0,
                    ZoneId.systemDefault());
            double testValue = 999.1;

            Entry testEntry4 = new EntryBuilder()
                    .setId(testID)
                    .setDate(testDate)
                    .setValue(testValue)
                    .buildEntry();

            this.testGateway.insertEntry(this.testBillID, testEntry4);

            Statement testStatement = this.testConnection.createStatement();

            String getNewlyCreatedEntry = String.format("SELECT * FROM bill_%d WHERE entry_id = %d",
                    this.testBillID, testEntry4.getId().getAttribute());

            ResultSet result = testStatement.executeQuery(getNewlyCreatedEntry);

            int obtainedID = -1;
            double value = 0.0;
            String currency = "";
            String description = "";
            String from = "";
            String to = "";
            String location = "";
            ZonedDateTime zDate = ZonedDateTime.now();
            int splitBillID = -1;

            while (result.next()) {
                obtainedID = result.getInt("entry_id");
                value = result.getDouble("value");
                Timestamp date = result.getTimestamp("date");
                currency = result.getString("currency");
                description = result.getString("description");
                from = result.getString("from");
                to = result.getString("to");
                location = result.getString("location");
                splitBillID = result.getInt("split_bill_id");

                Instant i = Instant.ofEpochMilli(date.getTime());

                // We can pass in the different zones we want to convert in, and we can obtain
                // the value we want
                zDate = ZonedDateTime.ofInstant(i, ZoneId.systemDefault());
            }

            assertEquals(obtainedID, testID);
            assertEquals(value, testValue, 1e-8);
            assertEquals(zDate, testDate);
            assertEquals(currency, "");
            assertEquals(description, "");
            assertEquals(from, "");
            assertEquals(to, "");
            assertEquals(location, "");
            assertEquals(splitBillID, -1);


        } catch (RuntimeException | SQLException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    // Testing insertEntry with auto increment
    @Test(timeout = TEST_TIMEOUT)
    public void testInsertEntryAutoIncrement() {
        try {
            ZonedDateTime testDate = ZonedDateTime.of(2009,
                    3,
                    5,
                    3,
                    5,
                    3,
                    0,
                    ZoneId.systemDefault());
            double testValue = 1011.02;

            Entry testEntry5 = new EntryBuilder()
                    .setDate(testDate)
                    .setValue(testValue)
                    .buildEntry();

            this.testGateway.insertEntry(this.testBillID, testEntry5);

            Statement testStatement = this.testConnection.createStatement();

            String query = String.format("SELECT * FROM bill_%d", testBillID);

            ResultSet result = testStatement.executeQuery(query);

            int size = 0;

            while (result.next()) {
                size += 1;
            }

            // We have 3 entries now, because we have 2 entries inserted in setUp, and 1 inserted
            // in this test case
            assertEquals(size, 3);


        } catch (RuntimeException | SQLException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    // Test for getting the Entry that we inserted in the previous test
    @Test(timeout = TEST_TIMEOUT)
    public void testGetEntry() {
        try {
            // In the @Before chunk, we inserted an entry that has the ID 1
            Entry obtainedEntry = this.testGateway.getEntryData(this.testBillID, 1);

            ZonedDateTime expectedDate = ZonedDateTime.of(1970,
                    1,
                    2,
                    0,
                    0,
                    0,
                    0, ZoneId.systemDefault());

            // This information was identical to the ones in the setUp chunk.
            // (1, 123.45, "1970-01-02 00:00:00", "CAD", "This is a test entry", "Credit Card", "T&T
            // Supermarket", "T&T Supermarket", -1)
            assertEquals(1, (int) obtainedEntry.getId().getAttribute());
            assertEquals(expectedDate, obtainedEntry.getDate().getAttribute());
            assertEquals(123.45, obtainedEntry.getValue().getAttribute(), 1e-8);
            assertEquals("CAD", obtainedEntry.getCurrency().getAttribute());
            assertEquals("This is a test entry", obtainedEntry.getDescription().getAttribute());
            assertEquals("Credit Card", obtainedEntry.getFrom().getAttribute());
            assertEquals("T&T Supermarket", obtainedEntry.getTo().getAttribute());
            assertEquals("T&T Supermarket", obtainedEntry.getLocation().getAttribute());
            assertEquals(-1, (int) obtainedEntry.getSplitterBillId().getAttribute());
        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    @Test(timeout = TEST_TIMEOUT)
    public void testGetBillDataAll() {
        try {
            List<Entry> obtainedBillData = this.testGateway.getBillData(this.testBillID);

            // Testing if the size is 2, since if there is 2, then it means we have obtained all
            // entries
            // In the setUp chunk, we initialized the bill with 2 entries
            assertEquals(obtainedBillData.size(), 2);

        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    @Test(timeout = TEST_TIMEOUT)
    public void testGetBillDataPartial() {
        try {
            ZonedDateTime startTime = ZonedDateTime.of(1969,
                    1,
                    1,
                    0,
                    0,
                    0,
                    0, ZoneId.systemDefault());

            ZonedDateTime endTime = ZonedDateTime.of(1970,
                    1,
                    5,
                    0,
                    0,
                    0,
                    0, ZoneId.systemDefault());

            List<Entry> obtainedBillData = this.testGateway.getBillData(
                    this.testBillID, startTime, endTime);

            // If we only have 1 entry obtained, then it is a success
            assertEquals(obtainedBillData.size(), 1);

        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    @Test(timeout = TEST_TIMEOUT)
    public void testModifyEntryDescription() {
        try {
            int testEntryID = 1;
            String testDescription = "This is modified test entry 1";

            this.testGateway.modifyEntry(this.testBillID, testEntryID, "Description",
                    testDescription);

            Statement testStatement = this.testConnection.createStatement();

            String getModifiedEntry = String.format("SELECT * FROM bill_%d WHERE entry_id = %d",
                    this.testBillID, testEntryID);

            ResultSet result = testStatement.executeQuery(getModifiedEntry);

            String description = "";

            while (result.next()) {
                description = result.getString("description");
            }

            assertEquals(testDescription, description);

        } catch (RuntimeException | SQLException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    @Test(timeout = TEST_TIMEOUT)
    public void testModifyEntryValue() {
        try {
            int testEntryID = 1;
            String testValue = "113.45";

            this.testGateway.modifyEntry(this.testBillID, testEntryID, "Value", testValue);

            Statement testStatement = this.testConnection.createStatement();

            String getModifiedEntry = String.format("SELECT * FROM bill_%d WHERE entry_id = %d",
                    this.testBillID, testEntryID);

            ResultSet result = testStatement.executeQuery(getModifiedEntry);

            double value = 0.0;

            while (result.next()) {
                value = result.getDouble("value");
            }

            assertEquals(Double.parseDouble(testValue), value, 1e-8);

        } catch (RuntimeException | SQLException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    @Test(timeout = TEST_TIMEOUT)
    public void testModifyEntryDate() {
        try {
            int testEntryID = 1;
            ZonedDateTime testDate = ZonedDateTime.of(2021,
                    9,
                    1,
                    5,
                    30,
                    0,
                    0,
                    ZoneId.systemDefault());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                    BillGatesUtilities.DATETIME_PATTERN);

            this.testGateway.modifyEntry(this.testBillID, testEntryID, "Date",
                    testDate.format(formatter));

            Statement testStatement = this.testConnection.createStatement();

            String getModifiedEntry = String.format("SELECT * FROM bill_%d WHERE entry_id = %d",
                    this.testBillID, testEntryID);

            ResultSet result = testStatement.executeQuery(getModifiedEntry);

            Timestamp date = new Timestamp(0);

            while (result.next()) {
                date = result.getTimestamp("date");
            }

            ZonedDateTime zDate = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

            assertEquals(zDate, testDate);

        } catch (RuntimeException | SQLException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    @Test(timeout = TEST_TIMEOUT)
    public void testModifyEntryAll() {
        // This is a test that uses the overwrite method of modifyEntry
        try {
            int testEntryID = 1;
            ZonedDateTime testTime = ZonedDateTime.of(2019,
                    1,
                    2,
                    1,
                    2,
                    3,
                    4,
                    ZoneId.systemDefault());
            double testValue = 100.00;
            String testCurrency = "USD";
            String testDescription = "This is modified test entry 1";
            String testFrom = "Debit Card";
            String testTo = "Amazon";
            String testLocation = "Online";
            int testSplitBillID = -1;

            Entry testModifiedEntry1 = new EntryBuilder()
                    .setId(testEntryID)
                    .setDate(testTime)
                    .setValue(testValue)
                    .setCurrency(testCurrency)
                    .setDescription(testDescription)
                    .setFrom(testFrom)
                    .setTo(testTo)
                    .setLocation(testLocation)
                    .setSplitterBillId(testSplitBillID)
                    .buildEntry();

            this.testGateway.modifyEntry(this.testBillID, testModifiedEntry1);

            Statement testStatement = this.testConnection.createStatement();

            String getModifiedEntry = String.format("SELECT * FROM bill_%d WHERE entry_id = %d",
                    this.testBillID, testModifiedEntry1.getId().getAttribute());

            ResultSet result = testStatement.executeQuery(getModifiedEntry);

            int obtainedID = -1;
            double value = 0.0;
            String currency = "";
            String description = "";
            String from = "";
            String to = "";
            String location = "";
            ZonedDateTime zDate;
            Timestamp date = new Timestamp(0);
            int splitBillID = -1;

            while (result.next()) {
                obtainedID = result.getInt("entry_id");
                value = result.getDouble("value");
                date = result.getTimestamp("date");
                currency = result.getString("currency");
                description = result.getString("description");
                from = result.getString("from");
                to = result.getString("to");
                location = result.getString("location");
                splitBillID = result.getInt("split_bill_id");
            }

            Instant i = Instant.ofEpochMilli(date.getTime());

            // We can pass in the different zones we want to convert in, and we can obtain the value
            // we want
            zDate = ZonedDateTime.ofInstant(i, ZoneId.systemDefault());

            assertEquals(obtainedID, (int) testModifiedEntry1.getId().getAttribute());
            assertEquals(value, testModifiedEntry1.getValue().getAttribute(), 1e-8);
            assertEquals(zDate, testModifiedEntry1.getDate().getAttribute());
            assertEquals(currency, testModifiedEntry1.getCurrency().getAttribute());
            assertEquals(description, testModifiedEntry1.getDescription().getAttribute());
            assertEquals(from, testModifiedEntry1.getFrom().getAttribute());
            assertEquals(to, testModifiedEntry1.getTo().getAttribute());
            assertEquals(location, testModifiedEntry1.getLocation().getAttribute());
            assertEquals(splitBillID, (int) testModifiedEntry1.getSplitterBillId().getAttribute());

        } catch (RuntimeException | SQLException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    @Test(timeout = TEST_TIMEOUT)
    public void testDeleteEntry() {
        try {
            // Now, we are testing to delete entry with the ID of 1, which is created in the Setup
            this.testGateway.deleteEntry(this.testBillID, 1);

            Statement testStatement = this.testConnection.createStatement();

            String getAllEntries = String.format("SELECT * FROM bill_%d",
                    this.testBillID);

            ResultSet result = testStatement.executeQuery(getAllEntries);

            int entryCount = 0;

            while (result.next()) {
                entryCount += 1;
            }

            // We should only have 1 entry left after deleting the entry with ID 1
            assertEquals(entryCount, 1);

        } catch (RuntimeException | SQLException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

}

