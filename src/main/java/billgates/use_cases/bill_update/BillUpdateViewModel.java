package billgates.use_cases.bill_update;

import java.util.List;

/**
 * Clean Architecture Layer: Interface Adapters
 * A view model holding information for the GUi to display.
 *
 * @author Scott
 * @see billgates.interface_adapters.BillPanelUpdatable
 * @see BillUpdatePresenter
 */
public class BillUpdateViewModel {

    /**
     * The column names of the bill table.
     */
    private String[] columns;

    /**
     * The data (entries) of the bill table.
     * Objects in this list of list have various types.
     */
    private List<List<Object>> entries;

    /**
     * A flag signifying if the current bill is a splitter bill.
     */
    private boolean isSplitterBill;

    public BillUpdateViewModel(String[] columns, List<List<Object>> entries, boolean isSplitterBill) {
        this.columns = columns;
        this.entries = entries;
        this.isSplitterBill = isSplitterBill;
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

    public boolean isSplitterBill() {
        return isSplitterBill;
    }

    public void setSplitterBill(boolean splitterBill) {
        isSplitterBill = splitterBill;
    }
}
