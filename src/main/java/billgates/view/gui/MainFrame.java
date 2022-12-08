package billgates.view.gui;

import billgates.use_cases.alter_entry.AlterEntryController;
import billgates.use_cases.bill_update.BillUpdateController;
import billgates.use_cases.delete_entry.DeleteEntryController;
import billgates.use_cases.insert_entry.InsertEntryController;
import billgates.use_cases.user_join.UserJoinController;

import javax.swing.*;
import java.awt.*;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte, Scott, Eva, Ruby
 */
public class MainFrame extends JFrame {

    // window width
    public static final int DEFAULT_WIDTH = 1250;
    // window height
    public static final int DEFAULT_HEIGHT = 750;
    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(220, 120, 150, 100);

    private final ActionPanel actionPanel = new ActionPanel(this);
    private final BillPanel billPanel = new BillPanel(this);
    private final JMenuBar menu = new TopMenuBar(this);

    private boolean isSplitterBill = false;
    private boolean isSignedIn = false;
    private UserJoinController userJoinController;

    // controllers will be set after constructing the view objects
    private BillUpdateController billUpdateController;
    private DeleteEntryController deleteEntryController;
    private InsertEntryController insertEntryController;
    private AlterEntryController alterEntryController;

    public MainFrame() {
        // Set the title
        super("Bill Gates");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set full screen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set the size of the window, and the user can resize the window
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setResizable(true);

        // Display the window in the center of the screen
        SwingUtil.centerInScreen(this);

        JPanel contentPane = new JPanel(new BorderLayout());
        this.setContentPane(contentPane);

        // Set the menu bar
        this.setJMenuBar(menu);

        // Add components
        this.add(this.actionPanel, BorderLayout.WEST);
        this.add(this.billPanel, BorderLayout.CENTER);
    }

    public BillPanel getBillPanel() {
        return this.billPanel;
    }

    public ActionPanel getActionPanel() {
        return this.actionPanel;
    }

    // This warning shouldn't be resolved because this is just a getter.
    public JMenuBar getMenu() {
        return menu;
    }

    public boolean isSplitterBill() {
        return isSplitterBill;
    }

    public void setSplitterBill(boolean splitterBill) {
        isSplitterBill = splitterBill;
    }

    public boolean isSignedIn() {
        return isSignedIn;
    }

    public void setSignedIn(boolean signedIn) {
        isSignedIn = signedIn;
    }

    public BillUpdateController getBillUpdateController() {
        return this.billUpdateController;
    }

    public void setBillUpdateController(BillUpdateController billUpdateController) {
        this.billUpdateController = billUpdateController;
    }

    public DeleteEntryController getDeleteEntryController() {
        return this.deleteEntryController;
    }

    public void setDeleteEntryController(DeleteEntryController deleteEntryController) {
        this.deleteEntryController = deleteEntryController;
    }

    public UserJoinController getUserJoinController() {
        return this.userJoinController;
    }

    public void setUserJoinController(UserJoinController userJoinController) {
        this.userJoinController = userJoinController;
    }

    public InsertEntryController getInsertEntryController() {
        return this.insertEntryController;
    }

    public void setInsertEntryController(InsertEntryController insertEntryController) {
        this.insertEntryController = insertEntryController;
    }

    public AlterEntryController getAlterEntryController() {
        return alterEntryController;
    }

    public void setAlterEntryController(AlterEntryController alterEntryController) {
        this.alterEntryController = alterEntryController;
    }

}