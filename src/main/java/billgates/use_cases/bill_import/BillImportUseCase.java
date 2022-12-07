package billgates.use_cases.bill_import;

import billgates.entities.Entry;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;
import billgates.entities.EntryBuilder;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Clean Architecture Layer: Application Business Rules
 * A concrete implementation of the <code>BillImportInputPort</code>.
 *
 * @author Eva
 * @see BillImportInputPort
 */

public class BillImportUseCase implements BillImportInputPort {
    private final DatabaseGateway gateway;

    public BillImportUseCase(DatabaseGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void importBill(BillImportRequestModel model) {
        for (List<String> entry: model.getEntries()) {
            ZonedDateTime date = ZonedDateTime.parse(entry.get(0));
            double value = Double.parseDouble(entry.get(1));
            Entry entries = new EntryBuilder()
                    .setDate(date)
                    .setValue(value)
                    .setCurrency(entry.get(2))
                    .setDescription(entry.get(3))
                    .setFrom(entry.get(4))
                    .setTo(entry.get(5))
                    .setLocation(entry.get(6))
                    .setSplitterBillId(-1)
                    .buildEntry();
            gateway.insertEntry(User.getInstance().getCurrentBillID(), entries);
        }
    }
}
