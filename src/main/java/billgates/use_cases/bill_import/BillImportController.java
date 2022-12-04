package billgates.use_cases.bill_import;

import billgates.use_cases.insert_entry.InsertEntryInputPort;

/**
 * Clean Architecture Layer: Interface Adapters
 * This class serves as the controller of the Bill Import Use Case.
 * It is only responsible for accepting the input (<code> model </code>) from the user and
 * invoke the corresponding use case.
 *
 * @author Eva
 * @see BillImportInputPort
 */

public class BillImportController {
    private final BillImportInputPort useCase;

    public BillImportController(BillImportInputPort useCase) {
        this.useCase = useCase;
    }

    public void imports(BillImportRequestModel model){
        this.useCase.importBill(model);
    }
}
