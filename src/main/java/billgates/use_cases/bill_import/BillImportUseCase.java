package billgates.use_cases.bill_import;

import billgates.database.QueryEntryData;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;
import billgates.use_cases.insert_entry.InsertEntryInputPort;

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
        List<QueryEntryData> result = this.gateway.getBillData(User.getInstance().getCurrentBillID()).getEntries();

        for (List<String> entry: model.getEntries()) {
            ZonedDateTime date = ZonedDateTime.parse(entry.get(0));
            double value = Double.parseDouble(entry.get(1));
            QueryEntryData entries = new QueryEntryData(date, value,
                    entry.get(2), entry.get(3),
                    entry.get(4), entry.get(5), entry.get(6), -1);
            gateway.insertEntry(User.getInstance().getCurrentBillID(), entries);
        }
    }
}


