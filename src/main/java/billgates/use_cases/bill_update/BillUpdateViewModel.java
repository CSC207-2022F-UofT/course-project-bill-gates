package billgates.use_cases.bill_update;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public BillUpdateViewModel(String[] columns, List<List<Object>> entries,
                               boolean isSplitterBill) {
        this.columns = columns;
        this.entries = entries;
        this.isSplitterBill = isSplitterBill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        BillUpdateViewModel that = (BillUpdateViewModel) o;
        return this.isSplitterBill == that.isSplitterBill &&
                Arrays.equals(this.columns, that.columns) &&
                this.entries.equals(that.entries);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(this.entries, this.isSplitterBill);
        result = 31 * result + Arrays.hashCode(this.columns);
        return result;
    }

    public String[] getColumns() {
        return columns;
    }

    // This warning shouldn't be resolved because this is just a setter.
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

    // This warning shouldn't be resolved because this is just a setter.
    public void setSplitterBill(boolean splitterBill) {
        isSplitterBill = splitterBill;
    }
}
