package billgates.use_cases.alter_entry;

import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

public class AlterEntryUseCase implements AlterEntryInputPort {
    private final DatabaseGateway gateway;

    public AlterEntryUseCase(DatabaseGateway gateway) {
        this.gateway = gateway;
    }


    @Override
    public void alterEntry(int entryID, Object newValue, String alterColumn) {
        int billID = User.getInstance().getCurrentBillID();
        String newValueString = newValue.toString();

        gateway.modifyEntry(billID, entryID, alterColumn, newValueString);

    }
}
