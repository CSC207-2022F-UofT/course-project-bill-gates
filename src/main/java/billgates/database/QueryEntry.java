package billgates.database;

import billgates.entities.EntryBuilder;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class QueryEntry {

/**
 * Clean Architecture Layer: Application Business Rules
 * This is the class for the general Entry
 * @author Ray
 */
    private int id = -1;
    private final ZonedDateTime date;
    private final double value;
    private String currency = "";
    private String description = "";
    private String from = "";
    private String to = "";
    private String location = "";

    public QueryEntry(int id,
                          ZonedDateTime date,
                          double value,
                          String currency,
                          String description,
                          String from,
                          String to,
                          String location) {
        this.id = id;
        this.date = date.truncatedTo(ChronoUnit.SECONDS);
        this.value = value;
        this.currency = currency;
        this.description = description;
        this.from = from;
        this.to = to;
        this.location = location;
    }

    public QueryEntry(ZonedDateTime date,
                          double value,
                          String currency,
                          String description,
                          String from,
                          String to,
                          String location) {
        this.date = date.truncatedTo(ChronoUnit.SECONDS);
        this.value = value;
        this.currency = currency;
        this.description = description;
        this.from = from;
        this.to = to;
        this.location = location;
    }

    public QueryEntry(int id,
                          ZonedDateTime date,
                          double value) {
        // Constructor for not all values provided
        this.id = id;
        this.date = date.truncatedTo(ChronoUnit.SECONDS);
        this.value = value;
    }

    public QueryEntry(ZonedDateTime date,
                          double value) {
        // Constructor for not all values provided, with no ID provided
        this.date = date.truncatedTo(ChronoUnit.SECONDS);
        this.value = value;
    }

    public EntryBuilder toEntryBuilder() {
        return new EntryBuilder()
                .setId(this.id)
                .setDate(this.date)
                .setValue(this.value)
                .setCurrency(this.currency)
                .setDescription(this.description)
                .setFrom(this.from)
                .setTo(this.to)
                .setLocation(this.location);
    }

    public int getId() { return id; }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        billgates.database.QueryEntry entry = (billgates.database.QueryEntry) obj;
        return this.getId() == entry.getId();
    }

}