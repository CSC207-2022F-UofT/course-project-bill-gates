package billgates.use_cases.bill_import;

import billgates.database.QueryEntryData;

import java.util.List;

public interface BillImportInputPort {

    /**
     * This method will insert file of entries in the database corresponding to the information in the model.
     *
     * @param model the information about the entries that need to be inserted into the database.
     */

    void importBill(BillImportRequestModel model);
}
