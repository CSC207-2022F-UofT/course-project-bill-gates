package billgates.database;

import billgates.interface_adapters.DatabaseGateway;

import java.io.FileInputStream;
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
 *
 * @author Ray, Scott
 */
public class MySQLDatabaseGateway implements DatabaseGateway {
    private int userId = -1;
    private Connection connection = null;
    public final Map<String, String> columnToDatabaseColumn = new HashMap<>();

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
        this.columnToDatabaseColumn.put("Splitter", "splitter");
    }

    public void initializeConnection() {
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {

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
    public QueryBillData getBillData(int billId) {
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
    public QueryBillData getBillData(int billId, ZonedDateTime startDate, ZonedDateTime endDate) {
        List<QueryEntryData> entries = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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

        return new QueryBillData(billId, entries);
    }

    @Override
    public QueryEntryData getEntryData(int billId, int entryId) {
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
                value = resultSet.getDouble("value");
                Timestamp date = resultSet.getTimestamp("date");
                currency = resultSet.getString("currency");
                description = resultSet.getString("description");
                from = resultSet.getString("from");
                to = resultSet.getString("to");
                location = resultSet.getString("location");
                splitBillId = resultSet.getInt("split_bill_id");

                Instant i = Instant.ofEpochMilli(date.getTime());

                // We can pass in the different zones we want to convert in, and we can obtain the value we want
                zDate = ZonedDateTime.ofInstant(i, ZoneId.systemDefault());
            } else {
                // Return null to denote that we didn't find any entry with this ID in the bill
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new QueryEntryData(entryId,
                zDate,
                value,
                currency,
                description,
                from,
                to,
                location,
                splitBillId);
    }

    @Override
    public void insertEntry(int billId, QueryEntryData entry) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            Statement statement = connection.createStatement();

            String query;

            // This is the case where we don't want autoincrement
            if (!(entry.getId() == -1)) {
                query = String.format("""
                        INSERT INTO bill_%d (entry_id, value, date, currency, description, `from`, `to`, location, split_bill_id) VALUE (
                        %d, %f, "%s", "%s", "%s", "%s", "%s", "%s", %d
                        )
                        """,
                        billId,
                        entry.getId(),
                        entry.getValue(),
                        entry.getDate().format(formatter),
                        entry.getCurrency(),
                        entry.getDescription(),
                        entry.getFrom(),
                        entry.getTo(),
                        entry.getLocation(),
                        entry.getSplitBillId());
            } else {
                // This is the case where we want auto increment
                query = String.format("""
                        INSERT INTO bill_%d (value, date, currency, description, `from`, `to`, location, split_bill_id) VALUE (
                        %f, "%s", "%s", "%s", "%s", "%s", "%s", %d
                        )
                        """,
                        billId,
                        entry.getValue(),
                        entry.getDate().format(formatter),
                        entry.getCurrency(),
                        entry.getDescription(),
                        entry.getFrom(),
                        entry.getTo(),
                        entry.getLocation(),
                        entry.getSplitBillId());
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
                        SET %s = "%s"
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
    public void modifyEntry(int billId, QueryEntryData entry) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            Statement statement = connection.createStatement();

            String query;

            // If equal, we are modifying the entry from the user's bill
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
                    entry.getValue(),
                    entry.getDate().format(formatter),
                    entry.getCurrency(),
                    entry.getDescription(),
                    entry.getFrom(),
                    entry.getTo(),
                    entry.getLocation(),
                    entry.getId(),
                    entry.getSplitBillId());

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
                        split_bill_id    INT DEFAULT -1  NOT NULL
                    )
                    """, billId);

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createSplitBillTable(int billId) {
        try {
            Statement statement = connection.createStatement();

            String query = String.format("""
                    CREATE TABLE bill_%d_%d
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
    public void createUsersTable() {
        // Creates the users table
        // Checks if the users table exists, if not, create it, if yes, do nothing.
        try {
            Statement statement = connection.createStatement();

            String checkQuery = "SHOW TABLES LIKE 'users'";

            ResultSet resultSet = statement.executeQuery(checkQuery);

            // Checks if the table already exists, if not, continue creating the table
            if (resultSet.next()) {
                return;
            }


            String query = """
                    CREATE TABLE users
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
