package billgates.use_cases.alter_entry;

import billgates.database.QueryEntryData;

import java.time.ZonedDateTime;

public class AlterEntryRequestModel {
    private String currency;
    private ZonedDateTime date;
    private double value;
    private String description;
    private String from;
    private String to;
    private String location;

    public AlterEntryRequestModel(QueryEntryData oldEntry) {
        date = oldEntry.getDate();
        value = oldEntry.getValue();
        currency = oldEntry.getCurrency();
        description = oldEntry.getDescription();
        from = oldEntry.getFrom();
        to = oldEntry.getTo();
        location = oldEntry.getLocation();
    }


    public void setDate(ZonedDateTime date) {
        this.date = date;
    }


    public void setValue(double value) {
        this.value = value;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public QueryEntryData getQueryEntryData(int entryID) {
        return new QueryEntryData(date, value, currency, description, from, to, location, -1);

    }
}
