package billgates.database;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Connector {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill",
                    "root",
                    "");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM bill1");

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
                ZonedDateTime zonedDate = ZonedDateTime.ofInstant(i, ZoneId.of("US/Eastern"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
