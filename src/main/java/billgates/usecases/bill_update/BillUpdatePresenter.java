package billgates.usecases.bill_update;

import billgates.interface_adapters.BillPanelUpdatable;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// An MVC implementation
// Interface Adapter Layer
public class BillUpdatePresenter implements BillUpdateOutputPort {

    public static final String[] MAIN_BILL_COLUMNS = new String[]
            {"ID", "Date", "Value", "Currency", "Description", "From", "To", "Location", "Splitter"};
    public static final String[] SPLITTER_BILL_COLUMNS = new String[]
            {"ID", "Date", "Value", "Currency", "Payee", "Paid Back", "Description", "From", "To", "Location"};

    private final BillPanelUpdatable view;

    public BillUpdatePresenter(BillPanelUpdatable view) {
        this.view = view;
    }

    @Override
    public void updateBill(BillUpdateResponseModel model) {
        List<List<Object>> entries = model.getEntries();
        // format dates
        for (List<Object> list : entries) {
            ZonedDateTime date = (ZonedDateTime) list.get(1);
            String formatted = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            list.set(1, formatted);
        }
        String[] columns;
        if (model.isSplitterBill()) {
            columns = SPLITTER_BILL_COLUMNS;
        } else {
            columns = MAIN_BILL_COLUMNS;
            // format splitters
            for (List<Object> list : entries) {
                int index = list.size() - 1;
                int splitter = (int) list.get(index);
                list.set(index, splitter == -1 ? "No" : "Yes");
            }
        }
        BillUpdateViewModel viewModel = new BillUpdateViewModel(columns, entries);
        this.view.update(viewModel);
    }
}
