package billgates.alter_entry;

import billgates.database.QueryEntryData;
import billgates.entities.Entry;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;


import java.util.List;

public class AlterEntryUseCase implements AlterEntryInputPort {
    private final AlterEntryOutputPort presenter;
    private final DatabaseGateway gateway;

    public AlterEntryUseCase(AlterEntryOutputPort presenter,DatabaseGateway gateway){
        this.presenter=presenter;
        this.gateway=gateway;
    }


    @Override
    public void alterEntry(int billId, QueryEntryData entry) {
        gateway.modifyEntry(billId,entry);
        List<Entry> result = this.gateway.getBillData(User.getInstance().getCurrentBillID()).getEntries()
                .stream().map(d -> d.toEntryBuilder().build()).toList();
        System.out.println(result); // TODO remove this
        this.presenter.updateBill(new AlterEntryResponseModel(result.stream().map(Entry::toObjects).toList()));
    }
}
