package billgates.database;

import java.time.ZonedDateTime;

public class QueryEntryData {
    private final int id;
    private final ZonedDateTime date;
    private final double value;
    private String currency = "NULL";
    private String description = "NULL";
    private String from = "NULL";
    private String to = "NULL";
    private String location = "NULL";

    public QueryEntryData(int id,
                          ZonedDateTime date,
                          double value,
                          String currency,
                          String description,
                          String from,
                          String to,
                          String location) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.currency = currency;
        this.description = description;
        this.from = from;
        this.to = to;
        this.location = location;

        // If this entry has null values, we will fill in "NULL" in their place
    }

    public QueryEntryData(int id,
                          ZonedDateTime date,
                          double value) {
        // Constructor for not all values provided

        this.id = id;
        this.date = date;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getLocation() {
        return location;
    }
}
