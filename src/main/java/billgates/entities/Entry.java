package billgates.entities;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Clean Architecture Layer: Enterprise Business Rules
 * Entry represents an entry in the main bill of the current user.
 * An entry is defined in AbstractEntry.
 *
 * @author Scott
 */
public class Entry extends AbstractEntry {

    /**
     * The id of the splitter bill of this entry.
     * We currently define it to be equal to the id of this entry.
     */
    private Attribute<Integer> splitterBillId;

    protected Entry(Attribute<Integer> id, Attribute<ZonedDateTime> date, Attribute<Double> value,
                    Attribute<String> currency, Attribute<String> description,
                    Attribute<String> from, Attribute<String> to, Attribute<String> location,
                    Attribute<Integer> splitterBillId) {
        super(id, date, value, currency, description, from, to, location);
        this.splitterBillId = splitterBillId;
    }

    @Override
    public List<Attribute<?>> toList() {
        return Arrays.asList(this.getId(), this.getDate(), this.getValue(), this.getCurrency(),
                this.getDescription(), this.getFrom(), this.getTo(), this.getLocation(),
                this.getSplitterBillId());
    }

    @Override
    public List<Object> toObjects() {
        return Arrays.asList(this.getId().getAttribute(), this.getDate().getAttribute(),
                this.getValue().getAttribute(), this.getCurrency().getAttribute(),
                this.getDescription().getAttribute(), this.getFrom().getAttribute(),
                this.getTo().getAttribute(), this.getLocation().getAttribute(),
                this.getSplitterBillId().getAttribute());
    }

    public Attribute<Integer> getSplitterBillId() {
        return splitterBillId;
    }

    // This warning shouldn't be resolved because this is just a setter.
    public void setSplitterBillId(Attribute<Integer> splitterBillId) {
        this.splitterBillId = splitterBillId;
    }
}
