package billgates.usecases.insert_entry;

import billgates.database.QueryEntryData;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

import java.util.List;

public class InsertEntryUseCase implements InsertEntryInputPort{

    private final DatabaseGateway gateway;

    public InsertEntryUseCase(DatabaseGateway gateway){
        this.gateway = gateway;
    }

    @Override
    public void insertEntry(InsertEntryRequestModel model) {

        //Query all the entries.
        List<QueryEntryData> result = this.gateway.getBillData(User.getInstance().getCurrentBillID()).getEntries();

        //Construct a QueryEntryData for new entry, and the entry id of it is the size of all entries plus one.
        QueryEntryData entry = new QueryEntryData(result.size() + 1,
                model.getDate(), model.getValue(),
                model.getCurrency(), model.getDescription(),
                model.getFrom(), model.getTo(), model.getLocation());

        //Pass the new QueryEntryData in the Gateway#insertEntry.
        gateway.insertEntry(User.getInstance().getCurrentBillID(), entry);
    }
}
