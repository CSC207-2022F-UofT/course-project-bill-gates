package billgates.entities;

import java.time.ZonedDateTime;

public class Entry extends AbstractEntry {

    public Entry(Attribute<Integer> id, Attribute<ZonedDateTime> date, Attribute<Double> value,
                 Attribute<String> currency, Attribute<String> description,
                 Attribute<String> from, Attribute<String> to, Attribute<String> location) {
        super(id, date, value, currency, description, from, to, location);
    }


}
