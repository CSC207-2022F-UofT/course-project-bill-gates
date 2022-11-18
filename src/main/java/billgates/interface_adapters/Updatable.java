package billgates.interface_adapters;


import billgates.usecase_shared_abstract.ViewModel;

// Interace Adapter Layer
public interface Updatable {

    void update(ViewModel viewModel);


    // we can add more methods for different view models

}
