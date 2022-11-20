package billgates.usecases.bill_import;

import billgates.database.QueryEntryData;

import java.util.List;

public interface BillImportInputPort {
    void importBill(BillImportRequestModel model);
}
