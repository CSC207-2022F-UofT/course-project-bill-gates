package billgates;

import billgates.database.MySQLDatabaseGateway;
import billgates.entities.User;
import billgates.interface_adapters.BillPanelUpdatable;
import billgates.interface_adapters.DatabaseGateway;
import billgates.usecases.bill_update.BillUpdateController;
import billgates.usecases.bill_update.BillUpdateOutputPort;
import billgates.usecases.bill_update.BillUpdatePresenter;
import billgates.usecases.bill_update.BillUpdateUseCase;
import billgates.usecases.delete_entry.DeleteEntryController;
import billgates.usecases.delete_entry.DeleteEntryInputPort;
import billgates.usecases.delete_entry.DeleteEntryUseCase;
import billgates.view.gui.MainFrame;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Scott
 */
public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new MetalLookAndFeel());

        // TODO: remove this: this is for debugging purposes
        User.getInstance(0, "Scott", "12345678", 0);

        // init database gateway
        DatabaseGateway databaseGateway = new MySQLDatabaseGateway();

        databaseGateway.setUserId(User.getInstance().getBillId());

        // init the main frame
        MainFrame mainFrame = new MainFrame();
        mainFrame.setBillUpdateController(initBillUpdateUseCase(databaseGateway, mainFrame.getBillPanel()));
        mainFrame.setDeleteEntryController(initDeleteEntryUseCase(databaseGateway));

        mainFrame.setVisible(true);
        // init column widths
        mainFrame.getBillPanel().getBillTable().initTableColumns();
    }

    private static BillUpdateController initBillUpdateUseCase(DatabaseGateway databaseGateway,
                                                              BillPanelUpdatable updatable) {
        // init bill update use case
        BillUpdateOutputPort billUpdatePresenter = new BillUpdatePresenter(updatable);
        BillUpdateUseCase billUpdateUseCase = new BillUpdateUseCase(billUpdatePresenter, databaseGateway);
        return new BillUpdateController(billUpdateUseCase);
    }

    private static DeleteEntryController initDeleteEntryUseCase(DatabaseGateway databaseGateway) {
        DeleteEntryInputPort useCase = new DeleteEntryUseCase(databaseGateway);
        return new DeleteEntryController(useCase);
    }

    // add new functions to init all other use cases

}
