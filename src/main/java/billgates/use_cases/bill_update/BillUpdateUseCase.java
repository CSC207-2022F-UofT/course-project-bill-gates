package billgates.use_cases.bill_update;

import billgates.entities.AbstractEntry;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private final Set<Thread> threads = new HashSet<>(5);

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
        // we query the database asynchronously to make the program run smoother
        Thread thread = new Thread(() -> {
            List<? extends AbstractEntry> result;
            if (user.getBillId() != user.getCurrentBillID()) {
                // if we are updating the splitter bill, then we create the splitter bill if
                // not exist
                this.gateway.createSplitBillTable(user.getCurrentBillID());
                // modify the split_bill_id column
                this.gateway.modifyEntry(user.getBillId(), user.getCurrentBillID(),
                        "Splitter", String.valueOf(user.getCurrentBillID()));
                // get all entries of the current bill
                result = this.gateway.getSplitBillData(user.getCurrentBillID());
            } else {
                result = this.gateway.getBillData(user.getCurrentBillID());
            }
            // transform all entries to lists of raw objects like int, ZonedDateTime, ...
            List<List<Object>> list = result.stream().map(AbstractEntry::toObjects).toList();
            // if the current bill id is not the same as the bill id,
            // then the current bill is a splitter bill
            this.presenter.updateBill(new BillUpdateResponseModel(list,
                    user.getCurrentBillID() != user.getBillId()));
        }, "bill_update_thread_" + user.getBillId());
        thread.start();
        this.threads.add(thread);
        this.threads.removeIf(t -> !t.isAlive());
    }

    public Set<Thread> getThreads() {
        return threads;
    }
}
