package billgates.database;

import billgates.entities.EntryBuilder;

import java.time.ZonedDateTime;

/**
 * Clean Architecture Layer: Application Business Rules
 *
 * @author Ray
 */
public class QuerySplitEntryData extends QueryEntry{
    private String payee = "";
    private boolean isPaidBack = false;

    public QuerySplitEntryData(int id,
                          ZonedDateTime date,
                          double value,
                          String currency,
                          String description,
                          String from,
                          String to,
                          String location,
                          String payee,
                               boolean isPaidBack) {
        super(id, date, value, currency, description, from, to, location);
        this.payee = payee;
        this.isPaidBack = isPaidBack;

    }

    public QuerySplitEntryData(ZonedDateTime date,
                          double value,
                          String currency,
                          String description,
                          String from,
                          String to,
                          String location,
                          String payee,
                               boolean isPaidBack) {
        super(date, value, currency, description, from, to, location);
        this.payee = payee;
        this.isPaidBack = isPaidBack;
    }

    public QuerySplitEntryData(int id,
                          ZonedDateTime date,
                          double value) {
        // Constructor for not all values provided
        super(id, date, value);
    }

    public QuerySplitEntryData(ZonedDateTime date,
                          double value) {
        // Constructor for not all values provided, with no ID provided
        super(date, value);
    }

    public String getPayee() {
        return this.payee;
    }

    public boolean getIsPaidBack() {
        return this.isPaidBack;
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
                .setPayee(this.getPayee())
                .setIsPaidBack(this.getIsPaidBack());
    }

}
