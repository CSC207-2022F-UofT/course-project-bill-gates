package billgates.use_cases.delete_entry;

import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

/**
 * Clean Architecture Layer: Application Business Rules
 * A concrete implementation of the <code>DeleteEntryInputPort</code>.
 *
 * @author Ellen, Scott
 * @see DeleteEntryInputPort
 */

public class DeleteEntryUseCase implements DeleteEntryInputPort {
    /**
     * The database gateway for input/output with the database.
     */
    private final DatabaseGateway gateway;

    public DeleteEntryUseCase(DatabaseGateway gateway) {
        this.gateway = gateway;
    }


    @Override
    public void deleteEntry(int entryId) {
        User user = User.getInstance();
        int billId = user.getCurrentBillID();
        if (billId == user.getBillId()) {
            this.gateway.deleteEntry(billId, entryId);
        } else {
            this.gateway.deleteSplitEntry(billId, entryId);
        }
    }
}
