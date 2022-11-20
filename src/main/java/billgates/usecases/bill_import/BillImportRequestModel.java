package billgates.usecases.bill_import;

import billgates.database.QueryBillData;
import billgates.database.QueryEntryData;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

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




