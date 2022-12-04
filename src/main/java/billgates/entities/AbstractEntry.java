package billgates.entities;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Clean Architecture Layer: Enterprise Business Rules
 * This is an abstraction of the most fundamental aspect of our software: An entry.
 * An entry is a collection of attributes with various type.
 * An entry represents a record of an occurrence of expenditure or income.
 *
 * @author Scott
 */
public abstract class AbstractEntry {

    /**
     * The id of this entry.
     * The id is guaranteed to be unique within a bill.
     */
    private Attribute<Integer> id;

    /**
     * The date and time of this occurrence of expenditure or income.
     */
    private Attribute<ZonedDateTime> date;

    /**
     * The value of this occurrence of expenditure or income.
     * Positive value represents expenditure.
     * Negative values represents income.
     * The value should have at most 16 integer places and 2 decimal places.
     * Values with more than 2 decimal places will be truncated to 2 decimal places (floor).
     */
    private Attribute<Double> value;

    /**
     * The currency of the value.
     * As defined in ISO 4217, currency codes all have a length of 3.
     */
    private Attribute<String> currency;

    /**
     * A description of this occurrence of expenditure or income.
     */
    private Attribute<String> description;

    /**
     * This is where the flow of money originate.
     * For example, Scott paid 20$ to Amazon using a credit card.
     * `from` in this example would be the name of the credit card.
     */
    private Attribute<String> from;

    /**
     * This is where the flow of money end.
     * For example, Scott paid 20$ to Amazon using a credit card.
     * `to` in this example would be Amazon.
     */
    private Attribute<String> to;

    /**
     * The physical location where this record of expenditure or income happened.
     */
    private Attribute<String> location;

    protected AbstractEntry(Attribute<Integer> id, Attribute<ZonedDateTime> date,
                            Attribute<Double> value, Attribute<String> currency,
                            Attribute<String> description, Attribute<String> from,
                            Attribute<String> to, Attribute<String> location) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.currency = currency;
        this.description = description;
        this.from = from;
        this.to = to;
        this.location = location;
    }

    /**
     * This method returns a list of packaged attributes.
     * This method could be deleted in the future.
     *
     * @return a list of packaged attributes.
     */
    @Deprecated
    public abstract List<Attribute<?>> toList();

    /**
     * This method convert every attribute of this entry to the attribute's raw form.
     *
     * @return a list of raw objects representing the attributes of this entry.
     */
    public abstract List<Object> toObjects();

    /**
     * Convert this entry to a human-readable string.
     * This method may be overwritten by the subclasses.
     *
     * @return a String representing this entry
     */
    @Override
    public String toString() {
        return Arrays.toString(new String[]{
                this.id.toString(), this.date.toString(), this.value.toString(),
                this.currency.toString(), this.description.toString(), this.from.toString(),
                this.to.toString(), this.location.toString()});
    }

    /**
     * Check if the given object is equal to this entry.
     * Precondition: if obj is an entry, then obj is in the same bill as this entry.
     *
     * @param obj the object to compare to
     * @return true if the id of obj entry is equal to this entry's id.
     */
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
