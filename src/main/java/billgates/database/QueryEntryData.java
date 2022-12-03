package billgates.database;

import billgates.entities.EntryBuilder;

import java.time.ZonedDateTime;

/**
 * Clean Architecture Layer: Application Business Rules
 *
 * @author Ray
 */
public class QueryEntryData extends QueryEntry{
    private int splitBillId = -1;

    public QueryEntryData(int id,
                          ZonedDateTime date,
                          double value,
                          String currency,
                          String description,
                          String from,
                          String to,
                          String location,
                          int splitBillId) {
        super(id, date, value, currency, description, from, to, location);
        this.splitBillId = splitBillId;
    }

    public QueryEntryData(ZonedDateTime date,
                          double value,
                          String currency,
                          String description,
                          String from,
                          String to,
                          String location,
                          int splitBillId) {
        super(date, value, currency, description, from, to, location);
        this.splitBillId = splitBillId;
    }

    public QueryEntryData(int id,
                          ZonedDateTime date,
                          double value) {
        // Constructor for not all values provided
        super(id, date, value);
    }

    public QueryEntryData(ZonedDateTime date,
                          double value) {
        // Constructor for not all values provided, with no ID provided
        super(date, value);
    }

    public EntryBuilder toEntryBuilder() {
        return new EntryBuilder()
                .setId(this.getId())
                .setDate(this.getDate())
                .setValue(this.getValue())
                .setCurrency(this.getCurrency())
                .setDescription(this.getDescription())
                .setFrom(this.getFrom())
                .setTo(this.getTo())
                .setLocation(this.getLocation())
                .setSplitterBillId(this.getSplitBillId());
    }

    public int getSplitBillId() {
        return splitBillId;
    }

}
