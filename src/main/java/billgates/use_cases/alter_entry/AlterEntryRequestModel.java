package billgates.use_cases.alter_entry;

import billgates.entities.Entry;
import billgates.entities.EntryBuilder;

import java.time.ZonedDateTime;

/**
 * Clean Architecture Layer: Application Business Rules
 * A response model of the AlterEntry use case.
 * It is used to build up data from the use case to form a changed entry.
 *
 * @author Brandon Fu
 * @see AlterEntryUseCase
 */

public class AlterEntryRequestModel {
    private String currency;
    private ZonedDateTime date;
    private double value;
    private String description;
    private String from;
    private String to;
    private String location;

    public AlterEntryRequestModel(Entry oldEntry) {
        date = oldEntry.getDate().getAttribute();
        value = oldEntry.getValue().getAttribute();
        currency = oldEntry.getCurrency().getAttribute();
        description = oldEntry.getDescription().getAttribute();
        from = oldEntry.getFrom().getAttribute();
        to = oldEntry.getTo().getAttribute();
        location = oldEntry.getLocation().getAttribute();
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

    // This warning shouldn't be resolved because it is necessary to have this variable.
    // We may use it in future extension.
    public Entry getEntryData(int entryID) {
        return new EntryBuilder()
                .setDate(this.date)
                .setValue(this.value)
                .setCurrency(this.currency)
                .setDescription(this.description)
                .setFrom(this.from)
                .setTo(this.to)
                .setLocation(this.location)
                .setSplitterBillId(-1)
                .buildEntry();

    }
}
