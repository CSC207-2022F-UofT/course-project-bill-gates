package billgates.use_cases.bill_import;

public interface BillImportInputPort {

    /**
     * This method will insert file of entries in the database corresponding to the information in the model.
     *
     * @param model the information about the entries that need to be inserted into the g database.
     */

    void importBill(BillImportRequestModel model);
}
