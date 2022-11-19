package usecase.alter_entry;

import billgates.database.QueryEntryData;
import billgates.interface_adapters.DatabaseGateway;

public class AlterEntryUseCase implements AlterEntryInputPort {
    private final DatabaseGateway gateway;

    public AlterEntryUseCase(DatabaseGateway gateway) {
        this.gateway = gateway;
    }


    @Override
    public void alterEntry(int billId, QueryEntryData entry) {
        gateway.modifyEntry(billId, entry);
    }
}
