package billgates.usecases.bill_update;

import billgates.entities.Entry;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

import java.util.List;

/**
 * Clean Architecture Layer: Application Business Rules
 * A concrete implementation of the <code>BillUpdateInputPort</code>.
 *
 * @author Scott
 * @see BillUpdateInputPort
 * @see BillUpdatePresenter
 */
public class BillUpdateUseCase implements BillUpdateInputPort {

    /**
     * The presenter for converting the response model to a view model.
     */
    private final BillUpdateOutputPort presenter;

    /**
     * The database gateway for input/output with the database.
     */
    private final DatabaseGateway gateway;

    public BillUpdateUseCase(BillUpdateOutputPort presenter, DatabaseGateway gateway) {
        this.presenter = presenter;
        this.gateway = gateway;
    }

    @Override
    public void updateBill(int billId) {
        User user = User.getInstance();
        switch (billId) {
            case -1 -> billId = user.getCurrentBillID();
            case -2 -> billId = user.getBillId();
        }
        user.setCurrentBillID(billId);
        // get all entries of the current bill
        List<Entry> result = this.gateway.getBillData(user.getCurrentBillID()).getEntries()
                .stream().map(d -> d.toEntryBuilder().buildEntry()).toList();
        List<List<Object>> list = result.stream().map(Entry::toObjects).toList();
        // if the current bill id is not the same as the bill id, then the current bill is a splitter bill
        this.presenter.updateBill(new BillUpdateResponseModel(list, user.getCurrentBillID() != user.getBillId()));
    }
}
