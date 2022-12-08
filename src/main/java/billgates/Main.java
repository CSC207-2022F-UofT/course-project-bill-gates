package billgates;

import billgates.database.MySQLDatabaseGateway;
import billgates.interface_adapters.BillPanelUpdatable;
import billgates.interface_adapters.DatabaseGateway;
import billgates.interface_adapters.UserJoinUpdatable;
import billgates.use_cases.alter_entry.AlterEntryController;
import billgates.use_cases.alter_entry.AlterEntryInputPort;
import billgates.use_cases.alter_entry.AlterEntryUseCase;
import billgates.use_cases.bill_update.BillUpdateController;
import billgates.use_cases.bill_update.BillUpdateOutputPort;
import billgates.use_cases.bill_update.BillUpdatePresenter;
import billgates.use_cases.bill_update.BillUpdateUseCase;
import billgates.use_cases.delete_entry.DeleteEntryController;
import billgates.use_cases.delete_entry.DeleteEntryInputPort;
import billgates.use_cases.delete_entry.DeleteEntryUseCase;
import billgates.use_cases.insert_entry.InsertEntryController;
import billgates.use_cases.insert_entry.InsertEntryInputPort;
import billgates.use_cases.insert_entry.InsertEntryUseCase;
import billgates.use_cases.user_join.*;
import billgates.view.gui.MainFrame;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Scott
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException ignored) {
        }

        // init database gateway
        DatabaseGateway databaseGateway = new MySQLDatabaseGateway();

        // init the main frame
        MainFrame mainFrame = new MainFrame();
        mainFrame.setBillUpdateController(initBillUpdateUseCase(databaseGateway,
                mainFrame.getBillPanel()));
        mainFrame.setDeleteEntryController(initDeleteEntryUseCase(databaseGateway));
        mainFrame.setUserJoinController(initUserJoinUseCase(databaseGateway,
                mainFrame.getActionPanel()));
        mainFrame.setInsertEntryController(initInsertEntryUseCase(databaseGateway));
        mainFrame.setAlterEntryController(initAlterEntryUseCase(databaseGateway));

        mainFrame.setVisible(true);
        // init column widths
        mainFrame.getBillPanel().getBillTable().initTableColumns();
    }

    private static BillUpdateController initBillUpdateUseCase(DatabaseGateway databaseGateway,
                                                              BillPanelUpdatable updatable) {
        // init bill update use case
        BillUpdateOutputPort billUpdatePresenter = new BillUpdatePresenter(updatable);
        BillUpdateUseCase billUpdateUseCase = new BillUpdateUseCase(billUpdatePresenter,
                databaseGateway);
        return new BillUpdateController(billUpdateUseCase);
    }

    private static DeleteEntryController initDeleteEntryUseCase(DatabaseGateway databaseGateway) {
        DeleteEntryInputPort useCase = new DeleteEntryUseCase(databaseGateway);
        return new DeleteEntryController(useCase);
    }

    private static UserJoinController initUserJoinUseCase(DatabaseGateway databaseGateway,
                                                          UserJoinUpdatable updatable) {
        UserJoinOutputPort userJoinPresenter = new UserJoinPresenter(updatable);
        UserJoinInputPort userJoinUseCase = new UserJoinUseCase(databaseGateway, userJoinPresenter);
        return new UserJoinController(userJoinUseCase);
    }

    private static InsertEntryController initInsertEntryUseCase(DatabaseGateway databaseGateway) {
        InsertEntryInputPort useCase = new InsertEntryUseCase(databaseGateway);
        return new InsertEntryController(useCase);
    }

    private static AlterEntryController initAlterEntryUseCase(DatabaseGateway databaseGateway) {
        AlterEntryInputPort useCase = new AlterEntryUseCase(databaseGateway);
        return new AlterEntryController(useCase);
    }

    // add new functions to init all other use cases

}
