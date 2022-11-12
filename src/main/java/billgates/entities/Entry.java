package billgates.entities;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class Entry extends AbstractEntry {

    // TODO splitter attribute

    protected Entry(Attribute<Integer> id, Attribute<ZonedDateTime> date, Attribute<Double> value,
                    Attribute<String> currency, Attribute<String> description,
                    Attribute<String> from, Attribute<String> to, Attribute<String> location) {
        super(id, date, value, currency, description, from, to, location);
    }

    @Override
    public List<Attribute<?>> toList() {
        return Arrays.asList(this.getId(), this.getDate(), this.getValue(), this.getCurrency(),
                this.getDescription(), this.getFrom(), this.getTo(), this.getLocation());
    }

    @Override
    public List<Object> toObjects() {
        return Arrays.asList(this.getId().getAttribute(), this.getDate().getAttribute(), this.getValue().getAttribute(),
                this.getCurrency().getAttribute(), this.getDescription().getAttribute(), this.getFrom().getAttribute(),
                this.getTo().getAttribute(), this.getLocation().getAttribute());
    }


}
