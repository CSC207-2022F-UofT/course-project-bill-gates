package billgates.entities;

import java.time.ZonedDateTime;
import java.util.Objects;

public abstract class AbstractEntry {

    private Attribute<Integer> id;
    private Attribute<ZonedDateTime> date;
    private Attribute<String> currency;
    private Attribute<String> description;
    private Attribute<String> from;
    private Attribute<String> to;
    private Attribute<String> location;

    public AbstractEntry(Attribute<Integer> id, Attribute<ZonedDateTime> date,
                         Attribute<String> currency, Attribute<String> description,
                         Attribute<String> from, Attribute<String> to, Attribute<String> location) {
        this.id = id;
        this.date = date;
        this.currency = currency;
        this.description = description;
        this.from = from;
        this.to = to;
        this.location = location;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        AbstractEntry entry = (AbstractEntry) obj;
        return this.id.getAttribute().equals(entry.id.getAttribute());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    public Attribute<Integer> getId() {
        return id;
    }

    public void setId(Attribute<Integer> id) {
        this.id = id;
    }

    public Attribute<ZonedDateTime> getDate() {
        return date;
    }

    public void setDate(Attribute<ZonedDateTime> date) {
        this.date = date;
    }

    public Attribute<String> getCurrency() {
        return currency;
    }

    public void setCurrency(Attribute<String> currency) {
        this.currency = currency;
    }

    public Attribute<String> getDescription() {
        return description;
    }

    public void setDescription(Attribute<String> description) {
        this.description = description;
    }

    public Attribute<String> getFrom() {
        return from;
    }

    public void setFrom(Attribute<String> from) {
        this.from = from;
    }

    public Attribute<String> getTo() {
        return to;
    }

    public void setTo(Attribute<String> to) {
        this.to = to;
    }

    public Attribute<String> getLocation() {
        return location;
    }

    public void setLocation(Attribute<String> location) {
        this.location = location;
    }
}
