package billgates.usecases.insert_entry;

import billgates.interface_adapters.Updatable;

import java.util.List;

public class InsertEntryPresenter implements InsertEntryOutputPort{

    private final Updatable view;

    public InsertEntryPresenter(Updatable view) {
        this.view = view;
    }

    @Override
    public void insertEntry(InsertEntryResponseModel model){
        List<List<Object>> entries = model.getEntries();
        InsertEntryViewModel viewModel = new InsertEntryViewModel(entries);
        this.view.insert(viewModel);
    }
}
