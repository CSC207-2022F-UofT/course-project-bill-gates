package billgates.alter_entry;

import billgates.interface_adapters.Updatable;
import billgates.interface_adapters.Updatable_alter;

import java.util.List;

public class AlterEntryPresenter implements AlterEntryOutputPort{

    private final Updatable_alter view;

    public AlterEntryPresenter(Updatable_alter view) {
        this.view = view;
    }

    @Override
    public void updateBill(AlterEntryResponseModel model) {
        List<List<Object>> entries = model.getEntries();
        // TODO: We should format the column in a more intuitive way.
        AlterEntryViewModel viewModel = new AlterEntryViewModel(entries);
        this.view.update(viewModel);

    }
}
