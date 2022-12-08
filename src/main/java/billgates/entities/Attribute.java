package billgates.entities;

import java.util.Objects;

/**
 * Clean Architecture Layer: Enterprise Business Rules
 * This is a class that represents an attribute of an entry in a bill.
 *
 * @param <T>: the actual type of this attribute.
 * @author Scott
 */
public class Attribute<T> {

    /**
     * The raw data of this attribute.
     */
    private T attribute;

    /**
     * The name of this attribute.
     */
    private String name;

    public Attribute(T attribute, String name) {
        this.attribute = attribute;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.attribute.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Attribute<?> attribute1 = (Attribute<?>) o;

        if (!Objects.equals(this.attribute, attribute1.attribute)) return false;
        return Objects.equals(this.name, attribute1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.attribute, this.name);
    }

    public T getAttribute() {
        return attribute;
    }

    // This warning shouldn't be resolved because this is just a setter.
    public void setAttribute(T attribute) {
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    // This warning shouldn't be resolved because this is just a setter.
    public void setName(String name) {
        this.name = name;
    }
}
