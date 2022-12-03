package billgates.database;

import java.util.List;

/**
 * Clean Architecture Layer: Application Business Rules
 *
 * @author Ray, Scott
 */
public class QueryBillData {
    private final int billId;
    private final List<QueryEntryData> entries;

    public QueryBillData(int billId, List<QueryEntryData> entries) {
        this.billId = billId;
        this.entries = entries;
    }

    public int getBillId() {
        return billId;
    }

    public List<QueryEntryData> getEntries() {
        return entries;
    }
}
