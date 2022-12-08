package billgates.entities;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Clean Architecture Layer: Enterprise Business Rules
 * Design Pattern: Builder
 * This is a builder class that help to build an Entry or Splitter Entry.
 * <p>
 * The instance variables define the default values for each attribute.
 * Each setter method set an attribute of the entry and returns this EntryBuilder.
 * In this way, you can chain setters and build an Entry in one line.
 * After setting all required attributes, you should use build______ method to build an entry or a
 * splitter entry.
 *
 * @author Scott, Ray
 */
public class EntryBuilder {

    private int id = -1;
    private ZonedDateTime date = ZonedDateTime.now();
    private double value = 0.0;
    private String currency = "";
    private String description = "";
    private String from = "";
    private String to = "";
    private String location = "";
    private int splitterBillId = -1;
    private String payee = "";
    private boolean isPaidBack = false;

    public EntryBuilder() {
    }

    public Entry buildEntry() {
        return new Entry(
                new Attribute<>(this.id, "id"),
                new Attribute<>(this.date, "date"),
                new Attribute<>(this.value, "value"),
                new Attribute<>(this.currency, "currency"),
                new Attribute<>(this.description, "description"),
                new Attribute<>(this.from, "from"),
                new Attribute<>(this.to, "to"),
                new Attribute<>(this.location, "location"),
                new Attribute<>(this.splitterBillId, "splitter_bill_id")
        );
    }

    public SplitterEntry buildSplitterEntry() {
        return new SplitterEntry(
                new Attribute<>(this.id, "id"),
                new Attribute<>(this.date, "date"),
                new Attribute<>(this.value, "value"),
                new Attribute<>(this.currency, "currency"),
                new Attribute<>(this.description, "description"),
                new Attribute<>(this.from, "from"),
                new Attribute<>(this.to, "to"),
                new Attribute<>(this.location, "location"),
                new Attribute<>(this.payee, "payee"),
                new Attribute<>(this.isPaidBack, "is_paid_back")
        );
    }

    // This warning shouldn't be resolved because it is necessary to have this method.
    public EntryBuilder reset() {
        this.id = -1;
        this.date = ZonedDateTime.now();
        this.value = 0.0;
        this.currency = "";
        this.description = "";
        this.from = "";
        this.to = "";
        this.location = "";
        this.splitterBillId = -1;
        this.payee = "";
        this.isPaidBack = false;
        return this;
    }

    public EntryBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public EntryBuilder setDate(ZonedDateTime date) {
        // Truncates the date unit to only seconds
        this.date = date.truncatedTo(ChronoUnit.SECONDS);
        return this;
    }

    public EntryBuilder setValue(double value) {
        this.value = value;
        return this;
    }

    public EntryBuilder setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public EntryBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public EntryBuilder setFrom(String from) {
        this.from = from;
        return this;
    }

    public EntryBuilder setTo(String to) {
        this.to = to;
        return this;
    }

    public EntryBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public EntryBuilder setSplitterBillId(int splitterBillId) {
        this.splitterBillId = splitterBillId;
        return this;
    }

    public EntryBuilder setPayee(String payee) {
        this.payee = payee;
        return this;
    }

    public EntryBuilder setIsPaidBack(boolean isPaidBack) {
        this.isPaidBack = isPaidBack;
        return this;
    }
}
