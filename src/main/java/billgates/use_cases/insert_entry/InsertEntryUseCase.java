package billgates.use_cases.insert_entry;

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

        //Construct a QueryEntryData for new entry, and the entry id of it is the size of all entries plus one.
        QueryEntryData entry = new QueryEntryData(
                model.getDate(), model.getValue(),
                model.getCurrency(), model.getDescription(),
                model.getFrom(), model.getTo(), model.getLocation(), -1);

        //Pass the new QueryEntryData in the Gateway#insertEntry.
        gateway.insertEntry(User.getInstance().getCurrentBillID(), entry);
    }
}
