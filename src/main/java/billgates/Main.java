package billgates;

import billgates.entities.Entry;
import billgates.entities.EntryFactory;

import java.time.ZonedDateTime;

public class Main {

    public static void main(String[] args) {
        Entry entry = new EntryFactory(1)
                .setDate(ZonedDateTime.now())
                .setValue(123)
                .setCurrency("CAD")
                .setDescription("RAY")
                .setFrom("Scott's credit card")
                .setTo("T&T supermarket")
                .createEntry();
        System.out.println(entry);
    }

}
