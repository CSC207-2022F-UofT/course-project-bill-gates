package billgates.use_cases.bill_import;

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
    private List<List<String>> importedData;

    public BillImportRequestModel(List<List<String>> importedData) {
        this.importedData = importedData;
    }

    public List<List<String>> getEntries() {
        return importedData;
    }

    public void setEntries(List<List<String>> importedData) {
        this.importedData = importedData;
    }
}