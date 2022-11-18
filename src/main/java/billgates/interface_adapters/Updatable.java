package billgates.interface_adapters;

import billgates.usecases.bill_update.BillUpdateViewModel;
import billgates.usecases.insert_entry.InsertEntryViewModel;

// Interface Adapter Layer
public interface Updatable {

    void update(BillUpdateViewModel viewModel);

    void insert(InsertEntryViewModel viewModel);

    // we can add more methods for different view models

}
