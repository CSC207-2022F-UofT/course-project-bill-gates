package billgates.database;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MySQLDatabaseGateway extends DatabaseGateway {
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
        return null;
    }

    @Override
    public QueryBillData getBillData(int billId) {
        return null;
    }

    @Override
    public QueryBillData getBillData(int billId, ZonedDateTime startDate, ZonedDateTime endDate) {
        return null;
    }

    @Override
    public QueryEntryData getEntryData(int entryId, int billId) {
        double value = 0.0;
        String currency = "";
        String description = "";
        String from = "";
        String to = "";
        String location = "";
        ZonedDateTime zDate = null;

        try{
            Statement statement = con.createStatement();

            String query = "SELECT " + "*" + " FROM bill" + billId + " WHERE entry_id == " + entryId;

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

}
