package billgates.usecases.bill_update;

import billgates.interface_adapters.BillPanelUpdatable;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        List<List<Object>> result = new ArrayList<>();
        for (List<Object> list : entries) {
            List<Object> temp = new ArrayList<>();
            for (Object o : list) {
                if (o instanceof ZonedDateTime date) {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    o = date.format(dateTimeFormatter);
                }
                temp.add(o);
            }
            result.add(temp);
        }
        String[] columns;
        if (model.isSplitterBill()) {
            columns = SPLITTER_BILL_COLUMNS;
        } else {
            columns = MAIN_BILL_COLUMNS;
        }
        BillUpdateViewModel viewModel = new BillUpdateViewModel(columns, result);
        System.out.println(result);
        this.view.update(viewModel);
    }
}
