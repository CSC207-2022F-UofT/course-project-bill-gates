package billgates.database;

import java.time.ZonedDateTime;

public class QueryEntryData {
    private final int id;
    private final ZonedDateTime date;
    private final double value;
    private final String currency;
    private final String description;
    private final String from;
    private final String to;
    private final String location;

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
