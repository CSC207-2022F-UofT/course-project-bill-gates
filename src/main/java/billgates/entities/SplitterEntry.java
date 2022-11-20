package billgates.entities;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class SplitterEntry extends AbstractEntry {

    private Attribute<String> payee;
    private Attribute<Boolean> isPaidBack;

    protected SplitterEntry(Attribute<Integer> id, Attribute<ZonedDateTime> date, Attribute<Double> value,
                            Attribute<String> currency, Attribute<String> description, Attribute<String> from,
                            Attribute<String> to, Attribute<String> location, Attribute<String> payee,
                            Attribute<Boolean> isPaidBack) {
        super(id, date, value, currency, description, from, to, location);
        this.payee = payee;
        this.isPaidBack = isPaidBack;
    }

    @Override
    public List<Attribute<?>> toList() {
        return Arrays.asList(this.getId(), this.getDate(), this.getValue(), this.getCurrency(),
                this.getPayee(), this.getIsPaidBack(), this.getDescription(), this.getFrom(), this.getTo(),
                this.getLocation());
    }

    @Override
    public List<Object> toObjects() {
        return Arrays.asList(
                this.getId().getAttribute(), this.getDate().getAttribute(), this.getValue().getAttribute(),
                this.getCurrency().getAttribute(), this.getPayee().getAttribute(), this.getIsPaidBack().getAttribute(),
                this.getDescription().getAttribute(), this.getFrom().getAttribute(),
                this.getTo().getAttribute(), this.getLocation().getAttribute());
    }

    public Attribute<String> getPayee() {
        return payee;
    }

    public void setPayee(Attribute<String> payee) {
        this.payee = payee;
    }

    public Attribute<Boolean> getIsPaidBack() {
        return isPaidBack;
    }

    public void setIsPaidBack(Attribute<Boolean> isPaidBack) {
        this.isPaidBack = isPaidBack;
    }
}
