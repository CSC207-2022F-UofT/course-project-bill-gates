package billgates.usecases.insert_entry;

import billgates.database.QueryEntryData;
import billgates.entities.Entry;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;
import billgates.usecases.bill_update.BillUpdateUseCase;

import java.util.List;

public class InsertEntryUseCase implements InsertEntryInputPort{

    private final InsertEntryOutputPort presenter;
    private final DatabaseGateway gateway;
    private final BillUpdateUseCase update;

    public InsertEntryUseCase(InsertEntryOutputPort presenter, DatabaseGateway gateway, BillUpdateUseCase update){
        this.presenter = presenter;
        this.gateway = gateway;
        this.update = update;
    }

    @Override
    public void insertEntry(QueryEntryData entry) {
        gateway.insertEntry(entry.getId(), entry);
        update.updateBill();
        List<Entry> result = this.gateway.getBillData(User.getInstance().getCurrentBillID()).getEntries()
                .stream().map(d -> d.toEntryBuilder().build()).toList();
        System.out.println(result); // TODO remove this
        this.presenter.insertEntry(new InsertEntryResponseModel(result.stream().map(Entry::toObjects).toList()));
    }
}
