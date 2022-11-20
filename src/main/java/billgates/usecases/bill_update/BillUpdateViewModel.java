package billgates.usecases.bill_update;

import java.util.List;

public class BillUpdateViewModel {

    private String[] columns;
    private List<List<Object>> entries;

    public BillUpdateViewModel(String[] columns, List<List<Object>> entries) {
        this.columns = columns;
        this.entries = entries;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public List<List<Object>> getEntries() {
        return entries;
    }

    public void setEntries(List<List<Object>> entries) {
        this.entries = entries;
    }

}
