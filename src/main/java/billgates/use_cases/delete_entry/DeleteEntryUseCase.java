package billgates.use_cases.delete_entry;

import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

/**
 * Clean Architecture Layer: Application Business Rules
 *
 * A concrete implementation of the <code>DeleteEntryInputPort</code>.
 *
 * @author Ellen, Scott
 *
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
        int billId = User.getInstance().getCurrentBillID();
        this.gateway.deleteEntry(billId, entryId);
    }
}
