package billgates.usecases.alter_entry;

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
    public void alterEntry(int entryID, Object newValue, String alterColumn) {
        int billID = User.getInstance().getCurrentBillID();

        QueryEntryData oldEntry = this.gateway.getEntryData(billID, entryID);
        AlterEntryRequestModel model = new AlterEntryRequestModel(oldEntry);

        if (alterColumn.equals("date")) {
            model.setDate((ZonedDateTime) newValue);
        }
        if (alterColumn.equals("value")) {
            model.setValue((double) newValue);
        }
        if (alterColumn.equals("currency")) {
            model.setCurrency((String) newValue);
        }
        if (alterColumn.equals("description")) {
            model.setDescription((String) newValue);
        }
        if (alterColumn.equals("from")) {
            model.setFrom((String) newValue);
        }
        if (alterColumn.equals("to")) {
            model.setTo((String) newValue);
        }
        if (alterColumn.equals("location")) {
            model.setLocation((String) newValue);
        }

        gateway.modifyEntry(billID, model.getQueryEntryData(entryID));

    }
}
