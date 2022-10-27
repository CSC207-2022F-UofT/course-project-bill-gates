package billgates.database;

import java.util.ArrayList;

public class QueryBillData {
    private final int billId;
    private final ArrayList<QueryEntryData> entries;

    public QueryBillData(int billId, ArrayList<QueryEntryData> entries) {
        this.billId = billId;
        this.entries = entries;
    }

    public int getBillId() {
        return billId;
    }

    public ArrayList<QueryEntryData> getEntries() {
        return entries;
    }
}
