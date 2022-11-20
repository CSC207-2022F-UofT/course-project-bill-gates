package billgates.entities;

import java.time.ZonedDateTime;

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

    // TODO for splitter, payee

    public EntryBuilder() {
    }

    public Entry build() {
        if (this.id == -1) {
            throw new InvalidEntryException("Invalid entry ID!");
        }
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
        return this;
    }

    public EntryBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public EntryBuilder setDate(ZonedDateTime date) {
        this.date = date;
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
}
