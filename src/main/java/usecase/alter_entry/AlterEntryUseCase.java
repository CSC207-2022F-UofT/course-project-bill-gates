package usecase.alter_entry;

import billgates.database.QueryEntryData;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

import java.time.ZonedDateTime;

public class AlterEntryUseCase implements AlterEntryInputPort {
    private final DatabaseGateway gateway;

    public AlterEntryUseCase(DatabaseGateway gateway) {
        this.gateway = gateway;
    }


    @Override
    public void alterEntry(int entry_id, Object new_value, String alter_column) {
        int billID = User.getInstance().getCurrentBillID();

        QueryEntryData old_entry = this.gateway.getEntryData(billID, entry_id);
        AlterEntryRequestModel model = new AlterEntryRequestModel(old_entry);

        if (alter_column.equals("date")) {
            model.setDate((ZonedDateTime) new_value);
        }
        if (alter_column.equals("value")) {
            model.setValue((double) new_value);
        }
        if (alter_column.equals("currency")) {
            model.setCurrency((String) new_value);
        }
        if (alter_column.equals("description")) {
            model.setDescription((String) new_value);
        }
        if (alter_column.equals("from")) {
            model.setFrom((String) new_value);
        }
        if (alter_column.equals("to")) {
            model.setTo((String) new_value);
        }
        if (alter_column.equals("location")) {
            model.setLocation((String) new_value);
        }

        gateway.modifyEntry(billID, model.getQueryEntryData(entry_id));

    }
}
