package billgates.use_cases.insert_entry;

import billgates.entities.Entry;
import billgates.entities.EntryBuilder;
import billgates.entities.SplitterEntry;
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
        // Check if we are adding a normal entry to the main bill
        // or a splitter entry to the splitter bill.
        if (User.getInstance().getBillId() == User.getInstance().getCurrentBillID()) {
            // Construct an entry using the entry builder
            // Default with a splitterBillId being -1
            // Default with the entryId with -1
            Entry entry = new EntryBuilder()
                    .setDate(model.getDate())
                    .setValue(model.getValue())
                    .setCurrency(model.getCurrency())
                    .setDescription(model.getDescription())
                    .setFrom(model.getFrom())
                    .setTo(model.getTo())
                    .setLocation(model.getLocation())
                    .setSplitterBillId(-1)
                    .buildEntry();

            // Pass the new QueryEntryData in the Gateway#insertEntry.
            this.gateway.insertEntry(User.getInstance().getCurrentBillID(), entry);
        } else {
            // Construct a QuerySplitEntryData for new splitter entry.
            SplitterEntry entry = new EntryBuilder()
                    .setDate(model.getDate())
                    .setValue(model.getValue())
                    .setCurrency(model.getCurrency())
                    .setDescription(model.getDescription())
                    .setFrom(model.getFrom())
                    .setTo(model.getTo())
                    .setLocation(model.getLocation())
                    .setPayee(model.getPayee())
                    .setIsPaidBack(model.getIsPaidBack())
                    .buildSplitterEntry();

            // Pass the new QuerySplitEntryData in the Gateway #insertSplitEntry.
            this.gateway.insertSplitEntry(User.getInstance().getCurrentBillID(), entry);
        }

    }
}
