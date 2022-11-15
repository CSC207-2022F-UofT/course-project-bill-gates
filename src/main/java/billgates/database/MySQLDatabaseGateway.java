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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MySQLDatabaseGateway implements DatabaseGateway {
    private Connection connection = null;

    public MySQLDatabaseGateway() {
        this.initializeConnection();
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
    public QueryUserData getUserData() {
        List<String> usernames = new ArrayList<>();
        List<String> passwords = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM users";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                usernames.add(username);
                passwords.add(password);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new QueryUserData(usernames, passwords);
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

            String query = String.format("""
                    SELECT * FROM bill%d
                    WHERE date BETWEEN CAST('%s' AS DATETIME) AND CAST('%s' AS DATETIME)
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
        int splitBillId = -1;
        double value = 0.0;
        String currency = "";
        String description = "";
        String from = "";
        String to = "";
        String location = "";
        ZonedDateTime zDate = ZonedDateTime.now();
        try {
            Statement statement = connection.createStatement();

            String query = String.format("""
                    SELECT * FROM bill%d WHERE entry_id = %d
                    """, billId, entryId);

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
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

            String query = String.format("""
                            INSERT INTO bill%d (value, date, currency, description, `from`, `to`, location, split_bill_id) VALUE (
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

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEntry(int billId, int entryId) {
        try {
            Statement statement = connection.createStatement();

            String query = String.format("""
                    DELETE FROM bill%d WHERE entry_id=%d
                    """, billId, entryId);

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

            String query = String.format("""
                            UPDATE bill%d
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
                    entry.getSplitBillId(),
                    entry.getId());

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
                    CREATE TABLE bill%d
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
                    """, billId);

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


}
