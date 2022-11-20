package billgates.usecases.bill_update;

import java.util.List;

public class BillUpdateResponseModel {

    private List<List<Object>> entries;
    private boolean isSplitterBill;

    public BillUpdateResponseModel(List<List<Object>> entries, boolean isSplitterBill) {
        this.entries = entries;
        this.isSplitterBill = isSplitterBill;
    }

    public List<List<Object>> getEntries() {
        return entries;
    }

    public void setEntries(List<List<Object>> entries) {
        this.entries = entries;
    }

    public boolean isSplitterBill() {
        return isSplitterBill;
    }

    public void setSplitterBill(boolean splitterBill) {
        isSplitterBill = splitterBill;
    }
}
