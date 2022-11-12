package billgates.usecases.bill_update;

import java.util.List;

public class BillUpdateViewModel {

    private List<List<Object>> entries;

    public BillUpdateViewModel(List<List<Object>> entries) {
        this.entries = entries;
    }

    public List<List<Object>> getEntries() {
        return entries;
    }

    public void setEntries(List<List<Object>> entries) {
        this.entries = entries;
    }

}
