package billgates.use_cases.insert_entry;

import billgates.database.QueryEntryData;
import billgates.database.QuerySplitEntryData;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

/**
 * Clean Architecture Layer: Application Business Rules
 * A concrete implementation of the <code>InsertEntryInputPort</code>.
 *
 * @author Ruby, Ellen
 * @see InsertEntryInputPort
 */

public class InsertEntryUseCase implements InsertEntryInputPort {

    /**
     * The database gateway for input/output with the database.
     */
    private final DatabaseGateway gateway;

    public InsertEntryUseCase(DatabaseGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void insertEntry(InsertEntryRequestModel model) {

        //Check if we are adding a normal entry to the main bill or a splitter entry to the splitter bill.
        if (User.getInstance().getBillId() == User.getInstance().getCurrentBillID()) {
            //Construct a QueryEntryData for new entry, and the entry id of it is the size of all entries plus one.
            QueryEntryData entry = new QueryEntryData(
                    model.getDate(), model.getValue(),
                    model.getCurrency(), model.getDescription(),
                    model.getFrom(), model.getTo(), model.getLocation(), -1);

            //Pass the new QueryEntryData in the Gateway#insertEntry.
            this.gateway.insertEntry(User.getInstance().getCurrentBillID(), entry);
        } else {
            //Construct a QuerySplitEntryData for new splitter entry.
            QuerySplitEntryData entry = new QuerySplitEntryData(model.getDate(),
                    model.getValue(), model.getCurrency(),
                    model.getDescription(), model.getFrom(), model.getTo(), model.getLocation(), model.getPayee(),
                    model.getIsPaidBack());

            //Pass the new QuerySplitEntryData in the Gateway #insertSplitEntry.
            this.gateway.insertSplitEntry(User.getInstance().getCurrentBillID(), entry);
        }

    }
}
