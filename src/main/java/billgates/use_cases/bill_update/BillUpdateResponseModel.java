package billgates.use_cases.bill_update;

import java.util.List;

/**
 * Clean Architecture Layer: Application Business Rules
 * A response model of the Bill Update use case.
 * It is used to transfer data from the use case to the presenter.
 *
 * @author Scott
 * @see BillUpdateUseCase
 * @see BillUpdateOutputPort
 * @see BillUpdatePresenter
 */
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

    // This warning shouldn't be resolved because this is just a setter.
    public void setSplitterBill(boolean splitterBill) {
        isSplitterBill = splitterBill;
    }
}
