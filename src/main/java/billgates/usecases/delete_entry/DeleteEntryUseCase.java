package billgates.usecases.delete_entry;

import billgates.interface_adapters.DatabaseGateway;


public class DeleteEntryUseCase implements DeleteEntryInputPort {

    private final DatabaseGateway gateway;



    public DeleteEntryUseCase(DatabaseGateway gateway) {
        this.gateway = gateway;
    }


    @Override
    public void deleteEntry(int billId, int entryId) {
        gateway.deleteEntry(billId, entryId);

    }
}
