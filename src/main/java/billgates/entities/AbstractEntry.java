package billgates.entities;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class AbstractEntry {

    private Attribute<Integer> id; //Entry id
    private Attribute<ZonedDateTime> date; //enteried date
    private Attribute<Double> value;//value of money
    private Attribute<String> currency;//what currency is used, so this will be more convenient for international users
    private Attribute<String> description;//simple description
    private Attribute<String> from;//money flow from
    private Attribute<String> to;//money flow to
    private Attribute<String> location;//location of this bill

    protected AbstractEntry(Attribute<Integer> id, Attribute<ZonedDateTime> date, Attribute<Double> value,
                            Attribute<String> currency, Attribute<String> description,
                            Attribute<String> from, Attribute<String> to, Attribute<String> location) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.currency = currency;
        this.description = description;
        this.from = from;
        this.to = to;
        this.location = location;
    }

    public abstract List<Attribute<?>> toList();

    public abstract List<Object> toObjects();

    @Override
    public String toString() {
        return Arrays.toString(new String[]{
                this.id.toString(), this.date.toString(), this.value.toString(), this.currency.toString(),
                this.description.toString(), this.from.toString(), this.to.toString(), this.location.toString()});
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
        return this.id;
    }

    public void setId(Attribute<Integer> id) {
        this.id = id;
    }

    public Attribute<ZonedDateTime> getDate() {
        return this.date;
    }

    public void setDate(Attribute<ZonedDateTime> date) {
        this.date = date;
    }

    public Attribute<Double> getValue() {
        return this.value;
    }

    public void setValue(Attribute<Double> value) {
        this.value = value;
    }

    public Attribute<String> getCurrency() {
        return this.currency;
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
