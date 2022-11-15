package billgates.interface_adapters;


import billgates.usecases.bill_update.BillUpdateViewModel;

// Interace Adapter Layer
public interface Updatable {

    void update(BillUpdateViewModel viewModel);


    // we can add more methods for different view models

}
