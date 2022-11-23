package billgates.view.gui;

import billgates.use_cases.bill_update.BillUpdateController;
import billgates.use_cases.delete_entry.DeleteEntryController;

import javax.swing.*;
import java.awt.*;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte, Scott
 */
public class MainFrame extends JFrame {

    // window width
    public static final int DEFAULT_WIDTH = 1000;
    // window height
    public static final int DEFAULT_HEIGHT = 618;
    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(220, 120, 150, 100);

    private final JPanel contentPane = new JPanel(new BorderLayout());
    private final ActionPanel actionPanel = new ActionPanel(this);
    private final BillPanel billPanel = new BillPanel(this);
    private final JMenuBar menu = new TopMenuBar();

    // controllers will be set after constructing the view objects
    private BillUpdateController billUpdateController;
    private DeleteEntryController deleteEntryController;

    public MainFrame() {
        // Set the title
        super("Bill Gates");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set the size of the window, and the user cannot resize the window
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setResizable(true);

        // Display the window in the center of the screen
        SwingUtil.centerInScreen(this);

        this.setContentPane(this.contentPane);

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
        return actionPanel;
    }

    public BillUpdateController getBillUpdateController() {
        return billUpdateController;
    }

    public void setBillUpdateController(BillUpdateController billUpdateController) {
        this.billUpdateController = billUpdateController;
    }

    public DeleteEntryController getDeleteEntryController() {
        return deleteEntryController;
    }

    public void setDeleteEntryController(DeleteEntryController deleteEntryController) {
        this.deleteEntryController = deleteEntryController;
    }
}