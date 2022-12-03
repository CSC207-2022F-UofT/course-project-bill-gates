package billgates.use_cases.insert_entry;

import java.time.ZonedDateTime;

public class InsertEntryRequestModel {

    //These are the information about the new normal entry and new splitter entry added.
    private final ZonedDateTime date;
    private final double value;
    private String currency = "";
    private String description = "";
    private String from = "";
    private String to = "";
    private String location = "";

    private String payee = "";

    private boolean isPaidBack = false;

    public InsertEntryRequestModel(ZonedDateTime date,
                                   double value,
                                   String currency,
                                   String description,
                                   String from,
                                   String to,
                                   String location) {
        this.date = date;
        this.value = value;
        this.currency = currency;
        this.description = description;
        this.from = from;
        this.to = to;
        this.location = location;
    }

    public InsertEntryRequestModel(ZonedDateTime date,
                                   double value,
                                   String currency,
                                   String description,
                                   String from,
                                   String to,
                                   String location,
                                   String payee,
                                   boolean isPaidBack) {
        this.date = date;
        this.value = value;
        this.currency = currency;
        this.description = description;
        this.from = from;
        this.to = to;
        this.location = location;
        this.payee = payee;
        this.isPaidBack = isPaidBack;
    }

    //Get the "date" information of the new entry.
    public ZonedDateTime getDate() {
        return date;
    }

    //Get the "value" information of the new entry.
    public double getValue() {
        return value;
    }

    //Get the "currency" information of the new entry.
    public String getCurrency() {
        return currency;
    }

    //Get the "description" information of the new entry.
    public String getDescription() {
        return description;
    }

    //Get the "from" information of the new entry.
    public String getFrom() {
        return from;
    }

    //Get the "to" information of the new entry.
    public String getTo() {
        return to;
    }

    //Get the "location" information of the new entry.
    public String getLocation() {
        return location;
    }

    //Get the "payee" information of the new entry.
    public String getPayee() {
        return payee;
    }

    // Get the "if is paid back" information of the new entry.
    public boolean getIsPaidBack() {
        return isPaidBack;
    }
}
