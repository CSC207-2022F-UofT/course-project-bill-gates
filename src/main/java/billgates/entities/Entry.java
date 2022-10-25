package billgates.entities;

import java.time.ZonedDateTime;

public class Entry extends AbstractEntry {

    private Attribute<Double> value;

    public Entry(Attribute<Integer> id, Attribute<ZonedDateTime> date,
                 Attribute<String> currency, Attribute<String> description,
                 Attribute<String> from, Attribute<String> to, Attribute<String> location) {
        super(id, date, currency, description, from, to, location);
    }
}
