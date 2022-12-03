package billgates.database;

import java.util.List;

/**
 * Clean Architecture Layer: Application Business Rules
 *
 * @author Ray, Scott
 */
public class QuerySplitBillData {
    private final int splitBillId;
    private final List<QuerySplitEntryData> splitEntries;

    public QuerySplitBillData(int billId, List<QuerySplitEntryData> splitEntries) {
        this.splitBillId = billId;
        this.splitEntries = splitEntries;
    }

    public int getBillId() {
        return splitBillId;
    }

    public List<QuerySplitEntryData> getEntries() {
        return splitEntries;
    }
}
