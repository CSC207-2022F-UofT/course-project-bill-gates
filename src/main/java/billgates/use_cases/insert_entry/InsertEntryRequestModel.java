package billgates.use_cases.insert_entry;

import java.time.ZonedDateTime;

/**
 * Clean Architecture Layer: Application Business Rules
 * A request model of the Insert Entry use case.
 * It is used to transfer input data as a whole
 * from the controller to the use case.
 *
 * @author Ruby, Ellen
 * @see InsertEntryInputPort
 * @see InsertEntryController
 * @see InsertEntryUseCase
 */

public class InsertEntryRequestModel {

    //These are the information about the new normal entry and new splitter entry added.
    private final ZonedDateTime date;
    private final double value;
    private final String currency;
    private final String description;
    private final String from;
    private final String to;
    private final String location;

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
        return this.date;
    }

    //Get the "value" information of the new entry.
    public double getValue() {
        return this.value;
    }

    //Get the "currency" information of the new entry.
    public String getCurrency() {
        return this.currency;
    }

    //Get the "description" information of the new entry.
    public String getDescription() {
        return this.description;
    }

    //Get the "from" information of the new entry.
    public String getFrom() {
        return this.from;
    }

    //Get the "to" information of the new entry.
    public String getTo() {
        return this.to;
    }

    //Get the "location" information of the new entry.
    public String getLocation() {
        return this.location;
    }

    //Get the "payee" information of the new entry.
    public String getPayee() {
        return this.payee;
    }

    // Get the "if is paid back" information of the new entry.
    public boolean getIsPaidBack() {
        return this.isPaidBack;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public void setPaidBack(boolean paidBack) {
        isPaidBack = paidBack;
    }
}
