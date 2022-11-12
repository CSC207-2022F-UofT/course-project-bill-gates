package billgates.usecases.bill_update;

import billgates.interface_adapters.Updatable;

import java.util.List;

// An MVC implementation
// Interface Adapter Layer
public class BillUpdatePresenter implements BillUpdateOutputPort {

    private final Updatable view;

    public BillUpdatePresenter(Updatable view) {
        this.view = view;
    }

    @Override
    public void updateBill(BillUpdateResponseModel model) {
        List<List<Object>> entries = model.getEntries();
        // TODO: We should format the column in a more intuitive way.
        BillUpdateViewModel viewModel = new BillUpdateViewModel(entries);
        this.view.update(viewModel);
    }
}
