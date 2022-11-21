package billgates.usecases.bill_update;

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
