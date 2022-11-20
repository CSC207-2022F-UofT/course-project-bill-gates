package billgates.entities;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class Entry extends AbstractEntry {

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
                this.getDescription(), this.getFrom(), this.getTo(), this.getLocation(), this.getSplitterBillId());
    }

    @Override
    public List<Object> toObjects() {
        return Arrays.asList(this.getId().getAttribute(), this.getDate().getAttribute(), this.getValue().getAttribute(),
                this.getCurrency().getAttribute(), this.getDescription().getAttribute(), this.getFrom().getAttribute(),
                this.getTo().getAttribute(), this.getLocation().getAttribute(),
                this.getSplitterBillId().getAttribute());
    }

    public Attribute<Integer> getSplitterBillId() {
        return splitterBillId;
    }

    public void setSplitterBillId(Attribute<Integer> splitterBillId) {
        this.splitterBillId = splitterBillId;
    }
}
