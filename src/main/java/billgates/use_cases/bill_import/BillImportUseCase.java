package billgates.use_cases.bill_import;

import billgates.Main;
import billgates.entities.Entry;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;
import billgates.entities.EntryBuilder;
import billgates.use_cases.insert_entry.InsertEntryRequestModel;
import billgates.view.gui.MainFrame;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Clean Architecture Layer: Application Business Rules
 * A concrete implementation of the <code>BillImportInputPort</code>.
 *
 * @author Eva
 * @see BillImportInputPort
 */

public class BillImportUseCase implements BillImportInputPort {
    private final DatabaseGateway gateway;
    private final MainFrame mainFrame;

    public BillImportUseCase(DatabaseGateway gateway, MainFrame mainFrame) {
        this.gateway = gateway;
        this.mainFrame = mainFrame;
    }

    @Override
    public void importBill(BillImportRequestModel model) {
        for (List<String> entry: model.getEntries()) {
            ZonedDateTime date = LocalDate.parse(entry.get(0), DateTimeFormatter.ISO_DATE).atStartOfDay(ZoneOffset.UTC);
            double value = Double.parseDouble(entry.get(1));
            InsertEntryRequestModel model1 = new InsertEntryRequestModel(date, value, entry.get(2), entry.get(3), entry.get(4), entry.get(5),entry.get(6));
            this.mainFrame.getInsertEntryController().insert(model1);
        }
    }
}
