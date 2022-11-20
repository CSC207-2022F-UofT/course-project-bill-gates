package billgates.interface_adapters;

import billgates.usecases.bill_update.BillUpdateViewModel;

// Interface Adapter Layer
public interface BillPanelUpdatable {

    void update(BillUpdateViewModel viewModel);

}
