package billgates.use_cases.delete_entry;

import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

/**
 * Clean Architecture Layer: Application Business Rules
 *
 * @author Ellen, Scott
 */
public class DeleteEntryUseCase implements DeleteEntryInputPort {

    private final DatabaseGateway gateway;

    public DeleteEntryUseCase(DatabaseGateway gateway) {
        this.gateway = gateway;
    }


    @Override
    public void deleteEntry(int entryId) {
        int billId = User.getInstance().getCurrentBillID();
        this.gateway.deleteEntry(billId, entryId);
    }
}
