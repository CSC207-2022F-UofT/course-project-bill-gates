package billgates.usecases.insert_entry;

import billgates.database.QueryEntryData;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

public class InsertEntryUseCase implements InsertEntryInputPort{

    private final DatabaseGateway gateway;

    public InsertEntryUseCase(DatabaseGateway gateway){
        this.gateway = gateway;
    }

    @Override
    public void insertEntry(InsertEntryRequestModel model) {

        QueryEntryData entry = new QueryEntryData(
                model.getDate(), model.getValue(),
                model.getCurrency(), model.getDescription(),
                model.getFrom(), model.getTo(), model.getLocation(), model.getSplitBillId());

        //Pass the new QueryEntryData in the Gateway#insertEntry.
        gateway.insertEntry(User.getInstance().getCurrentBillID(), entry);
    }

    public void insertEntry_Splitter(InsertEntryRequestModel model){
        QueryEntryData entry = new QueryEntryData(
                model.getDate(), model.getValue(),
                model.getCurrency(), model.getDescription(),
                model.getFrom(), model.getTo(), model.getLocation(), model.getSplitBillId());

        gateway.insertEntry(entry.getSplitBillId(), entry);
    }


}
