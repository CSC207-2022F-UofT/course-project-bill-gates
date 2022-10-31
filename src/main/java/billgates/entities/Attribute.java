package billgates.entities;

import java.util.Objects;

public class Attribute<T> {

    private T attribute;
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

    public void setAttribute(T attribute) {
        this.attribute = attribute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
