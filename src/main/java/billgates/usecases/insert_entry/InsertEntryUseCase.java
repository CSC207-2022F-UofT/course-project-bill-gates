package billgates.usecases.insert_entry;

import billgates.database.QueryEntryData;
import billgates.interface_adapters.DatabaseGateway;

public class InsertEntryUseCase implements InsertEntryInputPort{

    private final DatabaseGateway gateway;

    public InsertEntryUseCase(DatabaseGateway gateway){
        this.gateway = gateway;
    }

    @Override
    public void insertEntry(int billId, QueryEntryData entry) {
        gateway.insertEntry(billId, entry);
    }
}
