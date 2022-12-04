package billgates.use_cases.bill_import;

import billgates.use_cases.insert_entry.InsertEntryController;
import billgates.use_cases.insert_entry.InsertEntryInputPort;
import billgates.use_cases.insert_entry.InsertEntryUseCase;

import java.util.List;

/**
 * Clean Architecture Layer: Application Business Rules
 * A request model of the Bill Import use case.
 * It is used to transfer input data as a whole
 * from the controller to the use case.
 *
 * @author Eva
 * @see BillImportInputPort
 * @see BillImportController
 * @see BillImportUseCase
 */

public class BillImportRequestModel {
    private List<List<String>> csvFile;

    public BillImportRequestModel(List<List<String>> csvFile) {
        this.csvFile = csvFile;
    }

    public List<List<String>> getEntries() {
        return csvFile;
    }

    public void setEntries(List<List<String>> csvFile) {
        this.csvFile = csvFile;
    }
}