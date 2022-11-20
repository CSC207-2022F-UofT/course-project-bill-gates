package usecase.alter_entry;

import billgates.database.QueryEntryData;
import billgates.interface_adapters.DatabaseGateway;

import java.time.ZonedDateTime;

public class AlterEntryUseCase implements AlterEntryInputPort {
    private final DatabaseGateway gateway;

    public AlterEntryUseCase(DatabaseGateway gateway) {
        this.gateway = gateway;
    }


    @Override
    public void alterEntry(int billId, int entry_id, Object new_value, String alter_column) {

        QueryEntryData old_entry = this.gateway.getEntryData(billId, entry_id);
        ZonedDateTime date = old_entry.getDate();
        double value = old_entry.getValue();
        String currency = old_entry.getCurrency();
        String description = old_entry.getDescription();
        String from = old_entry.getFrom();
        String to = old_entry.getTo();
        String location = old_entry.getLocation();

        if (alter_column.equals("date")) {
            date = (ZonedDateTime) new_value;
        }
        if (alter_column.equals("value")) {
            value = (double) new_value;
        }
        if (alter_column.equals("currency")) {
            currency = (String) new_value;
        }
        if (alter_column.equals("description")) {
            description = (String) new_value;
        }
        if (alter_column.equals("from")) {
            from = (String) new_value;
        }
        if (alter_column.equals("to")) {
            to = (String) new_value;
        }
        if (alter_column.equals("location")) {
            location = (String) new_value;
        }


        QueryEntryData entry = new QueryEntryData(entry_id, date, value, currency, description, from, to, location);

        gateway.modifyEntry(billId, entry);

    }
}
