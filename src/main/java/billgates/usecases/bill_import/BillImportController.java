package billgates.usecases.bill_import;

import billgates.database.QueryEntryData;

public class BillImportController {
    private final BillImportInputPort useCase;

    public BillImportController(BillImportInputPort useCase) {
        this.useCase = useCase;
    }

    public void imports(BillImportRequestModel model){
        this.useCase.importBill(model);
    }
}
