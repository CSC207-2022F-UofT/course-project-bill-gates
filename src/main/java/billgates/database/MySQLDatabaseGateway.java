package billgates.database;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MySQLDatabaseGateway implements DatabaseGateway {
    private Connection con = null;

    public void initializeConnection() {
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill",
                    "root",
                    "");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public QueryUserData getUserData() {
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();

        try{
            Statement statement = con.createStatement();

            String query = "SELECT " + "*" + " FROM user";

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

        // This is the date of 2030-01-01-00:00:00:0000
        Instant instantEnd = Instant.ofEpochMilli(1893474000000L);
        ZoneId zoneId = ZoneId.of("US/Eastern");

        ZonedDateTime start = instantStart.atZone(zoneId);
        ZonedDateTime end = instantEnd.atZone(zoneId);

        return getBillData(billId, start, end);
    }

    @Override
    public QueryBillData getBillData(int billId, ZonedDateTime startDate, ZonedDateTime endDate) {
        ArrayList<QueryEntryData> entries = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try{
            Statement statement = con.createStatement();

            String query = "SELECT " + "*" + " FROM bill" + billId +
                    " WHERE" + " date >= CAST('" + startDate.format(formatter) + "' AS DATE)" +
                    " AND" + " date <= CAST('" + endDate.format(formatter) + "' AS DATE)";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                // Note that, aside from the general types that we have here
                // All the rest objects will be parsed in a string format
                int entryId = resultSet.getInt("entry_id");
                double value = resultSet.getDouble("value");
                Timestamp date = resultSet.getTimestamp("date");
                String currency = resultSet.getString("currency");
                String description = resultSet.getString("description");
                String from = resultSet.getString("from");
                String to = resultSet.getString("to");
                String location = resultSet.getString("location");

                Instant i = Instant.ofEpochMilli(date.getTime());

                // We can pass in the different zones we want to convert in, and we can obtain the value we want
                ZonedDateTime zDate = ZonedDateTime.ofInstant(i, ZoneId.of("US/Eastern"));

                QueryEntryData entry = new QueryEntryData(entryId,
                        zDate,
                        value,
                        currency,
                        description,
                        from,
                        to,
                        location);

                entries.add(entry);
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
        ZonedDateTime zDate = null;

        try{
            Statement statement = con.createStatement();

            String query = "SELECT " + "*" + " FROM bill" + billId + " WHERE entry_id=" + entryId;

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
                zDate = ZonedDateTime.ofInstant(i, ZoneId.of("US/Eastern"));
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
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("\"yyyy-MM-dd HH:mm:ss\"");

            Statement statement = con.createStatement();

            String query = "INSERT INTO " + "bill" + billId + " (entry_id, value, date) " + "VALUES ("
                    + entry.getId() + ", "
                    + entry.getValue() + ", "
                    + entry.getDate().format(formatter) + ")";

            statement.execute(query);

            // Should call Update and pass all the information there to replace the Nulls?

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteEntry(int billId, int entryId) {

    }

    @Override
    public void modifyEntry(int billId, QueryEntryData entry) {

    }

    @Override
    public void createBill(int billId) {
        try{
            Statement statement = con.createStatement();

            String query = "create table bill" + billId + "\n" +
                    "(\n" +
                    "    entry_id    int auto_increment\n" +
                    "        primary key,\n" +
                    "    value       decimal(16, 2)     not null,\n" +
                    "    date        timestamp  not null,\n" +
                    "    currency    varchar(5) null,\n" +
                    "    description text       null,\n" +
                    "    `from`      text       null,\n" +
                    "    `to`        text       null,\n" +
                    "    location    text       null\n" +
                    ");\n" +
                    "\n";

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
//                25,
//                0,
//                0,
//                0,
//                0,
//                ZoneId.of("US/Eastern"));
//
        QueryBillData b = a.getBillData(1);

        for (QueryEntryData i : b.getEntries()) {
            System.out.println(i.getValue());
            System.out.println(i.getDate().toInstant().toEpochMilli());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            System.out.println(i.getDate().format(formatter));
        }

        ZonedDateTime insertedTime = ZonedDateTime.of(2022,
                10,
                25,
                5,
                24,
                36,
                1234,
                ZoneId.of("US/Eastern"));

        QueryEntryData entry = new QueryEntryData(3,
                insertedTime,
                94.0,
                "CAD",
                "NULL",
                "NULL",
                "NULL",
                "NULL");

        a.insertEntry(1, entry);
    }

}
