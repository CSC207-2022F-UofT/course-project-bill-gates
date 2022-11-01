package billgates.database;

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

            String query = "SELECT * FROM user";

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
        // The maximum date that we can obtain using Epoch Milliseconds
        Instant instantEnd = Instant.ofEpochMilli(Instant.MAX.toEpochMilli());
        ZoneId zoneId = ZoneId.of("US/Eastern");

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
                location);
    }

    @Override
    public void insertEntry(int billId, QueryEntryData entry) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            Statement statement = connection.createStatement();

            String query = String.format("""
                            INSERT INTO bill%d VALUE (
                            %d, %f, "%s", "%s", "%s", "%s", "%s", "%s"
                            )
                            """, billId,
                    entry.getId(),
                    entry.getValue(),
                    entry.getDate().format(formatter),
                    entry.getCurrency(),
                    entry.getDescription(),
                    entry.getFrom(),
                    entry.getTo(),
                    entry.getLocation());

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
                            location = "%s"
                            WHERE entry_id = %d
                            """, billId,
                    entry.getValue(),
                    entry.getDate().format(formatter),
                    entry.getCurrency(),
                    entry.getDescription(),
                    entry.getFrom(),
                    entry.getTo(),
                    entry.getLocation(),
                    entry.getId());

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createBill(int billId) {
        try {
            Statement statement = connection.createStatement();

            String query = String.format("""
                    CREATE TABLE bill%d
                    (
                        entry_id    INT             AUTO_INCREMENT
                                                    PRIMARY KEY,
                        value       DECIMAL(16, 2)  NOT NULL,
                        date        TIMESTAMP       NOT NULL,
                        currency    VARCHAR(5)      NOT NULL,
                        description TEXT            NOT NULL,
                        `from`      TEXT            NOT NULL,
                        `to`        TEXT            NOT NULL,
                        location    TEXT            NOT NULL
                    )
                    """, billId);

            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        MySQLDatabaseGateway a = new MySQLDatabaseGateway();

        a.initializeConnection();

//        ZonedDateTime startTime = ZonedDateTime.of(2022,
//                10,
//                24,
//                0,
//                0,
//                0,
//                0,
//                ZoneId.of("US/Eastern"));
//
//        ZonedDateTime endTime = ZonedDateTime.of(2022,
//                10,
//                26,
//                0,
//                0,
//                0,
//                0,
//                ZoneId.of("US/Eastern"));
//
        QueryBillData b = a.getBillData(1);
//        QueryBillData b = a.getBillData(1, startTime, endTime);

//        QueryEntryData c = a.getEntryData(1, 1);

//        a.createBill(2);

        for (QueryEntryData i : b.getEntries()) {
            System.out.println(i.getValue());
            System.out.println(i.getDate().toInstant().toEpochMilli());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println(i.getDate().format(formatter));
        }

//        ZonedDateTime insertedTime = ZonedDateTime.of(2022,
//                9,
//                20,
//                20,
//                10,
//                30,
//                1234,
//                ZoneId.of("US/Eastern"));
//
//        QueryEntryData entry = new QueryEntryData(3,
//                insertedTime,
//                145.0,
//                "CAD",
//                "",
//                "Credit",
//                "Amazon",
//                "Outer space");
//
//        a.insertEntry(1, entry);
//        a.deleteEntry(1, 3);
//        a.modifyEntry(1, entry);
    }

}
