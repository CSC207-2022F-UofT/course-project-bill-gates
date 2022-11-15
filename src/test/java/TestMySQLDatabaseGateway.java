import org.junit.*;
import static org.junit.Assert.*;
import billgates.database.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
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
        ZonedDateTime testTime = ZonedDateTime.now();
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
            testGateway.createUsersTable();

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

    // Test for creating a bill table with the ID being testBillID
    @Test(timeout = TEST_TIMEOUT)
    public void testCreateBillTable() {
        try {
            // Creating a test Bill 9999
            testGateway.createBillTable(this.testBillID);
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
            testGateway.insertEntry(this.testBillID, this.testEntry1);
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
            testGateway.insertEntry(this.testBillID, this.testEntry2);
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
            QueryEntryData obtainedEntry = testGateway.getEntryData(this.testBillID, this.testEntry1.getId());

            assertEquals(testEntry1.getId(), obtainedEntry.getId());
            assertEquals(testEntry1.getDate(), obtainedEntry.getDate());
            assertEquals(testEntry1.getValue(), obtainedEntry.getValue(), 1e-8);
            assertEquals(testEntry1.getCurrency(), obtainedEntry.getCurrency());
            assertEquals(testEntry1.getDescription(), obtainedEntry.getDescription());
            assertEquals(testEntry1.getFrom(), obtainedEntry.getFrom());
            assertEquals(testEntry1.getTo(), obtainedEntry.getTo());
            assertEquals(testEntry1.getLocation(), obtainedEntry.getLocation());
        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }

    @Test(timeout = TEST_TIMEOUT)
    public void testGetEntryMainAttributes() {
        try {
            // Using testEntry1 to test the obtained result
            QueryEntryData obtainedEntry = testGateway.getEntryData(this.testBillID, this.testEntry2.getId());

            assertEquals(testEntry2.getId(), obtainedEntry.getId());
            assertEquals(testEntry2.getDate(), obtainedEntry.getDate());
            assertEquals(testEntry2.getValue(), obtainedEntry.getValue(), 1e-8);

            // Specifically, all of these tests should be empty String tests
            assertEquals(testEntry2.getCurrency(), obtainedEntry.getCurrency());
            assertEquals(testEntry2.getDescription(), obtainedEntry.getDescription());
            assertEquals(testEntry2.getFrom(), obtainedEntry.getFrom());
            assertEquals(testEntry2.getTo(), obtainedEntry.getTo());
            assertEquals(testEntry2.getLocation(), obtainedEntry.getLocation());
        } catch (RuntimeException e) {
            // Fails the test whenever we encounter an Error
            fail();
        }
    }




}

