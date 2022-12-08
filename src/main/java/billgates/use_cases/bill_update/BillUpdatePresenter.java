package billgates.use_cases.bill_update;

import billgates.interface_adapters.BillPanelUpdatable;
import billgates.view.BillGatesUtilities;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Clean Architecture Layer: Interface Adapters
 * A presenter in Clean Architecture for the Update Bill use case.
 * This presenter is implemented based on the MVC architecture
 * (specifically, MVC in Clean Architecture).
 *
 * @author Scott
 * @see BillPanelUpdatable
 */
public class BillUpdatePresenter implements BillUpdateOutputPort {

    /**
     * The default column names of the main bill.
     */
    public static final String[] MAIN_BILL_COLUMNS = new String[]{
            "ID", "Date", "Value", "Currency", "Description", "From", "To", "Location", "Splitter"
    };

    /**
     * The default column names of the splitter bill.
     */
    public static final String[] SPLITTER_BILL_COLUMNS = new String[]{
            "ID", "Date", "Value", "Currency", "Payee", "Paid Back", "Description", "From", "To",
            "Location"};

    /**
     * An updatable view.
     */
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
            String formatted = date.format(DateTimeFormatter.ofPattern(
                    BillGatesUtilities.DATETIME_PATTERN));
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
        BillUpdateViewModel viewModel = new BillUpdateViewModel(columns, entries,
                model.isSplitterBill());
        this.view.update(viewModel);
    }
}
