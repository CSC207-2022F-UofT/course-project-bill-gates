package billgates.database;

import billgates.entities.Entry;
import billgates.entities.EntryBuilder;
import billgates.entities.QueryUserData;
import billgates.entities.SplitterEntry;
import billgates.interface_adapters.DatabaseGateway;
import billgates.view.BillGatesUtilities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 * This is the class that is responsible for connecting the application to the Database
 *
 * @author Ray, Scott
 */
public class MySQLDatabaseGateway implements DatabaseGateway {
    public final Map<String, String> columnToDatabaseColumn = new HashMap<>();
    private int userId = -1;
    private Connection connection = null;

    public MySQLDatabaseGateway() {
        this.initializeConnection();

        this.columnToDatabaseColumn.put("ID", "entry_id");
        this.columnToDatabaseColumn.put("Value", "value");
        this.columnToDatabaseColumn.put("Date", "date");
        this.columnToDatabaseColumn.put("Currency", "currency");
        this.columnToDatabaseColumn.put("Description", "description");
        this.columnToDatabaseColumn.put("From", "from");
        this.columnToDatabaseColumn.put("To", "to");
        this.columnToDatabaseColumn.put("Location", "location");
        this.columnToDatabaseColumn.put("Splitter", "split_bill_id");
        this.columnToDatabaseColumn.put("Payee", "payee");
        this.columnToDatabaseColumn.put("Paid Back", "paid_back");
    }

    public void initializeConnection() {
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.user");
            String password = prop.getProperty("db.password");

            try {
                connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/bill", url),
                        user,
                        password);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public QueryUserData getUserData(String username) {
        QueryUserData user;
        try {
            Statement statement = connection.createStatement();

            String query = String.format("SELECT * FROM users where username = '%s'", username);

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                int userID = resultSet.getInt("user_id");
                int billID = resultSet.getInt("bill_id");
                String password = resultSet.getString("password");

                user = new QueryUserData(userID, billID, username, password);
            } else {
                // Returns null back to the caller to denote that the user wasn't found
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    @Override
    public List<QueryUserData> getUserData() {
        List<QueryUserData> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM users";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int userID = resultSet.getInt("user_id");
                int billID = resultSet.getInt("bill_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                users.add(new QueryUserData(userID, billID, username, password));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    @Override
    public List<Entry> getBillData(int billId) {
        Instant instantStart = Instant.ofEpochMilli(0);

        // This is the date of the end of the world
        // The date is 9999-01-01 00:00:00
        Instant instantEnd = Instant.ofEpochMilli(253370782800000L);
        ZoneId zoneId = ZoneId.systemDefault();

        ZonedDateTime start = instantStart.atZone(zoneId);
        ZonedDateTime end = instantEnd.atZone(zoneId);

        return getBillData(billId, start, end);
    }

    @Override
    public List<SplitterEntry> getSplitBillData(int splitBillId) {
        List<SplitterEntry> entries = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            String query;

            query = String.format("""
                    SELECT * FROM bill_%d_%d
                    ORDER BY date DESC,
                    entry_id ASC
                    """, this.userId, splitBillId);

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                entries.add(getSplitEntryData(splitBillId, resultSet.getInt("entry_id")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entries;
    }

    @Override
    public List<Entry> getBillData(int billId, ZonedDateTime startDate, ZonedDateTime endDate) {
        List<Entry> entries = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BillGatesUtilities.DATETIME_PATTERN);

        try {
            Statement statement = connection.createStatement();

            String query;

            query = String.format("""
                    SELECT * FROM bill_%d
                    WHERE date BETWEEN CAST('%s' AS DATETIME) AND CAST('%s' AS DATETIME)
                    ORDER BY date DESC,
                    entry_id ASC
                    """, billId, startDate.format(formatter), endDate.format(formatter));

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                entries.add(getEntryData(billId, resultSet.getInt("entry_id")));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return entries;
    }

    // This is a method specifically used to obtain:
    // Value, time, currency, description, from, to, location
    public List<Object> getBasicEntryInfo(ResultSet resultSet) throws SQLException {
        double value = resultSet.getDouble("value");
        Timestamp date = resultSet.getTimestamp("date");
        String currency = resultSet.getString("currency");
        String description = resultSet.getString("description");
        String from = resultSet.getString("from");
        String to = resultSet.getString("to");
        String location = resultSet.getString("location");

        return new ArrayList<>(List.of(value,
                date,
                currency,
                description,
                from,
                to,
                location));
    }

    @Override
    public Entry getEntryData(int billId, int entryId) {
        int splitBillId;
        double value;
        String currency;
        String description;
        String from;
        String to;
        String location;
        ZonedDateTime zDate;
        try {
            Statement statement = connection.createStatement();

            String query;

            // If equal, we are getting the bill corresponding to this user
            query = String.format("""
                    SELECT * FROM bill_%d WHERE entry_id = %d
                    """, billId, entryId);

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                // Note that, aside from the general types that we have here
                // All the rest objects will be parsed in a string format

                splitBillId = resultSet.getInt("split_bill_id");

                List<Object> result = getBasicEntryInfo(resultSet);

                value = (double) result.get(0);
                Timestamp date = (Timestamp) result.get(1);
                currency = (String) result.get(2);
                description = (String) result.get(3);
                from = (String) result.get(4);
                to = (String) result.get(5);
                location = (String) result.get(6);

                Instant i = Instant.ofEpochMilli(date.getTime());

                // We can pass in the different zones we want to convert in,
                // and we can obtain the value we want
                zDate = ZonedDateTime.ofInstant(i, ZoneId.systemDefault());
            } else {
                // Return null to denote that we didn't find any entry with this ID in the bill
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new EntryBuilder()
                .setId(entryId)
                .setValue(value)
                .setDate(zDate)
                .setCurrency(currency)
                .setDescription(description)
                .setFrom(from)
                .setTo(to)
                .setLocation(location)
                .setSplitterBillId(splitBillId)
                .buildEntry();
    }

    @Override
    public SplitterEntry getSplitEntryData(int billId, int entryId) {
        double value;
        String currency;
        String description;
        String from;
        String to;
        String location;
        ZonedDateTime zDate;
        String payee;
        boolean isPaidBack;
        try {
            Statement statement = connection.createStatement();

            String query;

            // If equal, we are getting the bill corresponding to this user
            query = String.format("""
                    SELECT * FROM bill_%d_%d WHERE entry_id = %d
                    """, this.userId, billId, entryId);

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                // Note that, aside from the general types that we have here
                // All the rest objects will be parsed in a string format

                value = resultSet.getDouble("value");
                Timestamp date = resultSet.getTimestamp("date");
                currency = resultSet.getString("currency");
                description = resultSet.getString("description");
                from = resultSet.getString("from");
                to = resultSet.getString("to");
                location = resultSet.getString("location");
                payee = resultSet.getString("payee");
                isPaidBack = resultSet.getBoolean("paid_back");

                Instant i = Instant.ofEpochMilli(date.getTime());

                // We can pass in the different zones we want to convert in,
                // and we can obtain the value we want
                zDate = ZonedDateTime.ofInstant(i, ZoneId.systemDefault());
            } else {
                // Return null to denote that we didn't find any entry with this ID in the bill
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new EntryBuilder()
                .setId(entryId)
                .setValue(value)
                .setDate(zDate)
                .setCurrency(currency)
                .setDescription(description)
                .setFrom(from)
                .setTo(to)
                .setLocation(location)
                .setPayee(payee)
                .setIsPaidBack(isPaidBack)
                .buildSplitterEntry();
    }

    @Override
    public void insertEntry(int billId, Entry entry) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BillGatesUtilities.DATETIME_PATTERN);

            Statement statement = connection.createStatement();

            String query;

            // This is the case where we don't want autoincrement
            if (!(entry.getId().getAttribute() == -1)) {
                query = String.format("""
                                INSERT INTO bill_%d (entry_id, value, date, currency, description,
                                `from`, `to`, location, split_bill_id) VALUE (
                                %d, %f, "%s", "%s", "%s", "%s", "%s", "%s", %d
                                )
                                """,
                        billId,
                        entry.getId().getAttribute(),
                        entry.getValue().getAttribute(),
                        entry.getDate().getAttribute().format(formatter),
                        entry.getCurrency().getAttribute(),
                        entry.getDescription().getAttribute(),
                        entry.getFrom().getAttribute(),
                        entry.getTo().getAttribute(),
                        entry.getLocation().getAttribute(),
                        entry.getSplitterBillId().getAttribute());
            } else {
                // This is the case where we want auto increment
                query = String.format("""
                                INSERT INTO bill_%d (value, date, currency, description, `from`,
                                `to`, location, split_bill_id) VALUE (
                                %f, "%s", "%s", "%s", "%s", "%s", "%s", %d
                                )
                                """,
                        billId,
                        entry.getValue().getAttribute(),
                        entry.getDate().getAttribute().format(formatter),
                        entry.getCurrency().getAttribute(),
                        entry.getDescription().getAttribute(),
                        entry.getFrom().getAttribute(),
                        entry.getTo().getAttribute(),
                        entry.getLocation().getAttribute(),
                        entry.getSplitterBillId().getAttribute());
            }

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertSplitEntry(int billId, SplitterEntry entry) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BillGatesUtilities.DATETIME_PATTERN);

            Statement statement = connection.createStatement();

            String query;

            // This is the case where we don't want autoincrement
            if (!(entry.getId().getAttribute() == -1)) {
                query = String.format("""
                                INSERT INTO bill_%d_%d (entry_id, value, date, currency,
                                description, `from`, `to`, location, payee, paid_back) VALUE (
                                %d, %f, "%s", "%s", "%s", "%s", "%s", "%s", "%s", "%s"
                                )
                                """,
                        this.userId,
                        billId,
                        entry.getId().getAttribute(),
                        entry.getValue().getAttribute(),
                        entry.getDate().getAttribute().format(formatter),
                        entry.getCurrency().getAttribute(),
                        entry.getDescription().getAttribute(),
                        entry.getFrom().getAttribute(),
                        entry.getTo().getAttribute(),
                        entry.getLocation().getAttribute(),
                        entry.getPayee().getAttribute(),
                        entry.getIsPaidBack().getAttribute() ? "1" : "0");
            } else {
                // This is the case where we want auto increment
                query = String.format("""
                                INSERT INTO bill_%d_%d (value, date, currency, description, `from`,
                                `to`, location, payee, paid_back) VALUE (
                                %f, "%s", "%s", "%s", "%s", "%s", "%s", "%s", "%s"
                                )
                                """,
                        this.userId,
                        billId,
                        entry.getValue().getAttribute(),
                        entry.getDate().getAttribute().format(formatter),
                        entry.getCurrency().getAttribute(),
                        entry.getDescription().getAttribute(),
                        entry.getFrom().getAttribute(),
                        entry.getTo().getAttribute(),
                        entry.getLocation().getAttribute(),
                        entry.getPayee().getAttribute(),
                        entry.getIsPaidBack().getAttribute() ? "1" : "0");
            }

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insertUser(QueryUserData user) {
        try {
            Statement statement = connection.createStatement();

            String insert;

            // This is the case that we want to specify a userId
            if (!(user.getUserID() == -1)) {
                insert = String.format("""
                                INSERT INTO users (user_id, username, password, bill_id) VALUE (
                                %d, "%s", "%s", %d
                                )
                                """,
                        user.getUserID(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getBillID());
                statement.execute(insert);
            } else {
                // This is the case that we want to Auto-Increment User
                insert = String.format("""
                                INSERT INTO users (username, password, bill_id) VALUE (
                                "%s", "%s", -1
                                )
                                """,
                        user.getUsername(),
                        user.getPassword());
                statement.execute(insert);
                int userId = this.getUserData(user.getUsername()).getUserID();
                String update = String.format("""
                        UPDATE users SET bill_id = %d WHERE user_id = %d
                        """, userId, userId);
                statement.execute(update);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(QueryUserData user) {
        if (user.getUserID() == -1) {
            this.deleteUser(user.getUsername());
        } else {
            // use user id
            this.deleteUser(user.getUserID());
        }
    }

    @Override
    public void deleteUser(int userid) {
        try {
            Statement statement = this.connection.createStatement();
            String delete;
            delete = String.format("""
                            DELETE FROM users WHERE user_id = %d
                            """,
                    userid);
            statement.execute(delete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(String username) {
        try {
            Statement statement = this.connection.createStatement();
            String delete;
            delete = String.format("""
                            DELETE FROM users WHERE username = '%s'
                            """,
                    username);
            statement.execute(delete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUser(QueryUserData user) {
        List<Entry> entries = this.getBillData(user.getBillID());
        entries.forEach(entry -> {
            if (entry.getSplitterBillId().getAttribute() != -1) {
                this.dropSplitBillTable(entry.getSplitterBillId().getAttribute());
            }
        });
        this.dropBillTable(user.getBillID());
        this.deleteUser(user);
    }

    @Override
    public void deleteEntry(int billId, int entryId) {
        try {
            Statement statement = connection.createStatement();

            String query;

            query = String.format("""
                    DELETE FROM bill_%d WHERE entry_id = %d
                    """, billId, entryId);

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSplitEntry(int billId, int entryId) {
        try {
            Statement statement = connection.createStatement();

            String query;

            query = String.format("""
                    DELETE FROM bill_%d_%d WHERE entry_id = %d
                    """, this.userId, billId, entryId);

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyEntry(int billId, int entryId, String column, String newValue) {
        try {
            // This method assumed that the newValue being passed in already has the right format.
            // E.g. For ZonedDateTime, it is already in the format of "yyyy-MM-dd HH:mm:ss"
            // For value, since it is double, SQL can parse "123.45" as double as well

            Statement statement = connection.createStatement();

            String query;

            // If equal, we are modifying the entry from the user's bill
            query = String.format("""
                            UPDATE bill_%d
                            SET `%s` = '%s'
                            WHERE entry_id = %d
                            """, billId,
                    this.columnToDatabaseColumn.get(column),
                    newValue,
                    entryId);

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifySplitEntry(int billId, int entryId, String column, String newValue) {
        try {
            Statement statement = connection.createStatement();

            String query;

            // If equal, we are modifying the entry from the user's bill
            query = String.format("""
                            UPDATE bill_%d_%d
                            SET `%s` = '%s'
                            WHERE entry_id = %d
                            """, this.userId,
                    billId,
                    this.columnToDatabaseColumn.get(column),
                    newValue,
                    entryId);

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifyEntry(int billId, Entry entry) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BillGatesUtilities.DATETIME_PATTERN);

            Statement statement = connection.createStatement();

            String query;

            query = String.format("""
                            UPDATE bill_%d
                            SET value = %f,
                                date = "%s",
                                currency = "%s",
                                description = "%s",
                                `from` = "%s",
                                `to` = "%s",
                                location = "%s",
                                split_bill_id = %d
                            WHERE entry_id = %d
                            """, billId,
                    entry.getValue().getAttribute(),
                    entry.getDate().getAttribute().format(formatter),
                    entry.getCurrency().getAttribute(),
                    entry.getDescription().getAttribute(),
                    entry.getFrom().getAttribute(),
                    entry.getTo().getAttribute(),
                    entry.getLocation().getAttribute(),
                    entry.getSplitterBillId().getAttribute(),
                    entry.getId().getAttribute());

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void modifySplitEntry(int billId, SplitterEntry entry) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(BillGatesUtilities.DATETIME_PATTERN);

            Statement statement = connection.createStatement();

            String query;

            query = String.format("""
                            UPDATE bill_%d_%d
                            SET value = %f,
                                date = "%s",
                                currency = "%s",
                                description = "%s",
                                `from` = "%s",
                                `to` = "%s",
                                location = "%s",
                                payee = "%s",
                                paid_back = "%s"
                            WHERE entry_id = %d
                            """, this.userId,
                    billId,
                    entry.getValue().getAttribute(),
                    entry.getDate().getAttribute().format(formatter),
                    entry.getCurrency().getAttribute(),
                    entry.getDescription().getAttribute(),
                    entry.getFrom().getAttribute(),
                    entry.getTo().getAttribute(),
                    entry.getLocation().getAttribute(),
                    entry.getPayee().getAttribute(),
                    entry.getIsPaidBack().getAttribute(),
                    entry.getId().getAttribute());

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createBillTable(int billId) {
        try {
            Statement statement = connection.createStatement();

            String query = String.format("""
                    CREATE TABLE IF NOT EXISTS bill_%d
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
                        split_bill_id    INT DEFAULT -1  NOT NULL
                    )
                    """, billId);

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropBillTable(int billId) {
        try {
            Statement statement = this.connection.createStatement();
            String drop = String.format("""
                    DROP TABLE IF EXISTS bill_%d
                    """, billId);
            statement.execute(drop);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createSplitBillTable(int billId) {
        try {
            Statement statement = connection.createStatement();

            String query = String.format("""
                    CREATE TABLE IF NOT EXISTS bill_%d_%d
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
                        payee            TEXT            NOT NULL,
                        paid_back        BOOLEAN         NOT NULL
                    )
                    """, this.userId, billId);

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropSplitBillTable(int billId) {
        try {
            Statement statement = connection.createStatement();
            String drop = String.format("""
                    DROP TABLE IF EXISTS bill_%d_%d
                    """, this.userId, billId);
            statement.execute(drop);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createUsersTable() {
        // Creates the users table
        // Checks if the users table exists, if not, create it, if yes, do nothing.
        try {
            Statement statement = connection.createStatement();

            String query = """
                    CREATE TABLE IF NOT EXISTS users
                    (
                        user_id  INT AUTO_INCREMENT
                                 PRIMARY KEY,
                        username VARCHAR(10) NOT NULL,
                        password VARCHAR(16) NOT NULL,
                        bill_id  INT         NOT NULL
                    );
                    """;

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

}
