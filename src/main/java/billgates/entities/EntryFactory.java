package billgates.entities;

import java.time.ZonedDateTime;

public class EntryFactory {

    private int id = -1;
    private ZonedDateTime date = ZonedDateTime.now();
    private double value = 0.0;
    private String currency = "";
    private String description = "";
    private String from = "";
    private String to = "";
    private String location = "";

    // TODO splitter

    // TODO for splitter, payee

    public EntryFactory(int id) {
        this.id = id;
    }

    public Entry createEntry() {
        return new Entry(
                new Attribute<>(this.id, "id"),
                new Attribute<>(this.date, "date"),
                new Attribute<>(this.value, "value"),
                new Attribute<>(this.currency, "currency"),
                new Attribute<>(this.description, "description"),
                new Attribute<>(this.from, "from"),
                new Attribute<>(this.to, "to"),
                new Attribute<>(this.location, "location")
                );
    }

    public EntryFactory setDate(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public EntryFactory setValue(double value) {
        this.value = value;
        return this;
    }

    public EntryFactory setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public EntryFactory setDescription(String description) {
        this.description = description;
        return this;
    }

    public EntryFactory setFrom(String from) {
        this.from = from;
        return this;
    }

    public EntryFactory setTo(String to) {
        this.to = to;
        return this;
    }

    public EntryFactory setLocation(String location) {
        this.location = location;
        return this;
    }
}
