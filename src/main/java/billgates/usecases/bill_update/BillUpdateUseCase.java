package billgates.usecases.bill_update;

import billgates.entities.Entry;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

import java.util.List;

// Use Case Layer
public class BillUpdateUseCase implements BillUpdateInputPort {

    private final BillUpdateOutputPort presenter;
    private final DatabaseGateway gateway;

    public BillUpdateUseCase(BillUpdateOutputPort presenter, DatabaseGateway gateway) {
        this.presenter = presenter;
        this.gateway = gateway;
    }

    @Override
    public void updateBill() {
        List<Entry> result = this.gateway.getBillData(User.getInstance().getCurrentBillID()).getEntries()
                .stream().map(d -> d.toEntryBuilder().build()).toList();
        System.out.println(result); // TODO remove this
        this.presenter.updateBill(new BillUpdateResponseModel(result.stream().map(Entry::toObjects).toList()));
    }
}
