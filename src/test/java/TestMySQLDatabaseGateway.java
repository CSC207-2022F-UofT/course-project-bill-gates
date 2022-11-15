import org.junit.*;
import static org.junit.Assert.*;
import billgates.database.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestMySQLDatabaseGateway {
    public MySQLDatabaseGateway testGateway;
    public int testBillID = 9999;
    public QueryEntryData testEntry1;
    public QueryEntryData testEntry2;

    public Connection testConnection;

    public static final int TEST_TIMEOUT = 100000;

    @Before
    public void setUp() {
        this.testGateway = new MySQLDatabaseGateway();

        // Setting up testEntry1
        int testEntryID = 1;
        ZonedDateTime testTime = ZonedDateTime.of(1970,
                1,
                1,
                0,
                0,
                0,
                0, ZoneId.of("EST"));
        double testValue = 123.45;
        String testCurrency = "CAD";
        String testDescription = "This is a test entry";
        String testFrom = "Credit Card";
        String testTo = "T&T Supermarket";
        String testLocation = "T&T Supermarket";

        this.testEntry1 = new QueryEntryData(testEntryID,
                testTime,
                testValue,
                testCurrency,
                testDescription,
                testFrom,
                testTo,
                testLocation);

        testEntryID = 2;
        testTime = ZonedDateTime.of(1970,
                1,
                5,
                0,
                0,
                0,
                0, ZoneId.of("EST"));
        testValue = 678.90;

        this.testEntry2 = new QueryEntryData(testEntryID,
                testTime,
                testValue);

        // Trying to create the test connection to execute other Queries that needs to be tested
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            try {
                this.testConnection = DriverManager.getConnection(String.format("jdbc:mysql://%s/bill", url),
                        user,
                        password);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        // TODO: Add code to remove the bill that we created when testing

        // Releasing the testConnection
        try { this.testConnection.close(); } catch (Exception e) {/* Do nothing */}
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

            // Checks if the table already exists, if not, continue creating the table
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
            QueryUserData obtainedUsers = this.testGateway.getUserData();
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
            // Creating a test Bill
            this.testGateway.createBillTable(this.testBillID);
        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            // When trying to run getBillData method on BillID 9999
            fail();
        }
    }

    // Test for inserting an entry
    @Test(timeout = TEST_TIMEOUT)
    public void testInsertEntryAllAttributes() {
        try {
            // If nothing happens when we try to insert, then we declare that this is a success
            this.testGateway.insertEntry(this.testBillID, this.testEntry1);
        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    // Test for inserting an entry with only the ID, Date, Value
    @Test(timeout = TEST_TIMEOUT)
    public void testInsertEntryMainAttributes() {
        try {
            // If nothing happens when we try to insert, then we declare that this is a success
            this.testGateway.insertEntry(this.testBillID, this.testEntry2);
        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    // Test for getting the Entry that we inserted in the previous test
    @Test(timeout = TEST_TIMEOUT)
    public void testGetEntryAllAttributes() {
        try {
            // Using testEntry1 to test the obtained result
            QueryEntryData obtainedEntry = this.testGateway.getEntryData(this.testBillID, this.testEntry1.getId());

            assertEquals(this.testEntry1.getId(), obtainedEntry.getId());
            assertEquals(this.testEntry1.getDate(), obtainedEntry.getDate());
            assertEquals(this.testEntry1.getValue(), obtainedEntry.getValue(), 1e-8);
            assertEquals(this.testEntry1.getCurrency(), obtainedEntry.getCurrency());
            assertEquals(this.testEntry1.getDescription(), obtainedEntry.getDescription());
            assertEquals(this.testEntry1.getFrom(), obtainedEntry.getFrom());
            assertEquals(this.testEntry1.getTo(), obtainedEntry.getTo());
            assertEquals(this.testEntry1.getLocation(), obtainedEntry.getLocation());
        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    @Test(timeout = TEST_TIMEOUT)
    public void testGetEntryMainAttributes() {
        try {
            // Using testEntry2 to test the obtained result
            QueryEntryData obtainedEntry = this.testGateway.getEntryData(this.testBillID, this.testEntry2.getId());

            assertEquals(this.testEntry2.getId(), obtainedEntry.getId());
            assertEquals(this.testEntry2.getDate(), obtainedEntry.getDate());
            assertEquals(this.testEntry2.getValue(), obtainedEntry.getValue(), 1e-8);

            // Specifically, all of these tests should be empty String tests
            assertEquals(this.testEntry2.getCurrency(), obtainedEntry.getCurrency());
            assertEquals(this.testEntry2.getDescription(), obtainedEntry.getDescription());
            assertEquals(this.testEntry2.getFrom(), obtainedEntry.getFrom());
            assertEquals(this.testEntry2.getTo(), obtainedEntry.getTo());
            assertEquals(this.testEntry2.getLocation(), obtainedEntry.getLocation());
        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    @Test(timeout = TEST_TIMEOUT)
    public void testGetBillDataAll() {
        try {
            QueryBillData obtainedBillData = this.testGateway.getBillData(this.testBillID);

            // Testing if the size is 2, since if there is 2, then it means we have obtained all entries
            assertEquals(obtainedBillData.getEntries().size(), 2);

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
                    0, ZoneId.of("EST"));

            ZonedDateTime endTime = ZonedDateTime.of(1970,
                    1,
                    5,
                    0,
                    0,
                    0,
                    0, ZoneId.of("EST"));

            QueryBillData obtainedBillData = this.testGateway.getBillData(this.testBillID, startTime, endTime);

            // If we only have 1 entry obtained, then it is a success
            assertEquals(obtainedBillData.getEntries().size(), 1);

        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    @Test(timeout = TEST_TIMEOUT)
    public void testModifyEntry() {
        try {
            // Setting up test modify entry for Entry 1
            int testEntryID = 1;
            ZonedDateTime testTime = ZonedDateTime.now();
            double testValue = 100.00;
            String testCurrency = "USD";
            String testDescription = "This is modified test entry 1";
            String testFrom = "Debit Card";
            String testTo = "Amazon";
            String testLocation = "Online";

            QueryEntryData testModifiedEntry1 = new QueryEntryData(testEntryID,
                    testTime,
                    testValue,
                    testCurrency,
                    testDescription,
                    testFrom,
                    testTo,
                    testLocation);

            this.testGateway.modifyEntry(this.testBillID, testModifiedEntry1);

            // Now, we try to obtain the modified entry
            // Since we modified the entry with ID 1, we should be able to retrieve the entry with ID 1
            // And we should have all the attributes being equal to the attributes in testModifiedEntry1
            QueryEntryData obtainedEntry = this.testGateway.getEntryData(this.testBillID, this.testEntry1.getId());

            assertEquals(testModifiedEntry1.getId(), obtainedEntry.getId());
            assertEquals(testModifiedEntry1.getDate(), obtainedEntry.getDate());
            assertEquals(testModifiedEntry1.getValue(), obtainedEntry.getValue(), 1e-8);
            assertEquals(testModifiedEntry1.getCurrency(), obtainedEntry.getCurrency());
            assertEquals(testModifiedEntry1.getDescription(), obtainedEntry.getDescription());
            assertEquals(testModifiedEntry1.getFrom(), obtainedEntry.getFrom());
            assertEquals(testModifiedEntry1.getTo(), obtainedEntry.getTo());
            assertEquals(testModifiedEntry1.getLocation(), obtainedEntry.getLocation());

        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    @Test(timeout = TEST_TIMEOUT)
    public void testDeleteEntry() {
        try {
            // Now, we are testing to delete entry with the ID of 1, which is modified earlier
            this.testGateway.deleteEntry(this.testBillID, this.testEntry1.getId());


            QueryBillData obtainedBillData = this.testGateway.getBillData(this.testBillID);
            // Testing if we only have 1 entry left in the bill, if yes, then we have deleted entry 1
            // And only entry 2 exists
            assertEquals(obtainedBillData.getEntries().size(), 1);
            assertEquals(obtainedBillData.getEntries().get(0).getId(), 2);

        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

}

