package billgates.usecases.bill_import;

import billgates.database.QueryEntryData;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

import java.time.ZonedDateTime;
import java.util.List;

public class BillImportUseCase implements BillImportInputPort {
    private final DatabaseGateway gateway;

    public BillImportUseCase(DatabaseGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void importBill(BillImportRequestModel model) {
        List<QueryEntryData> result = this.gateway.getBillData(User.getInstance().getCurrentBillID()).getEntries();

        int entryID = result.size() + 1;

        for (List<String> entry: model.getEntries()) {
            ZonedDateTime date = ZonedDateTime.parse(entry.get(0));
            double value = Double.parseDouble(entry.get(1));
            QueryEntryData entries = new QueryEntryData(entryID, date, value,
                    entry.get(2), entry.get(3),
                    entry.get(4), entry.get(5), entry.get(6));
            gateway.insertEntry(User.getInstance().getCurrentBillID(), entries);
            entryID += 1;
        }
    }
}


