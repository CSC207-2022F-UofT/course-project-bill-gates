package billgates.entities;

import java.util.List;
import java.util.Objects;

public class Bill {

    private int id;
    private List<AbstractEntry> entries;

    public Bill(int id, List<AbstractEntry> entries) {
        this.id = id;
        this.entries = entries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return this.id == bill.id && this.entries.equals(bill.entries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entries);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<AbstractEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<AbstractEntry> entries) {
        this.entries = entries;
    }
}
