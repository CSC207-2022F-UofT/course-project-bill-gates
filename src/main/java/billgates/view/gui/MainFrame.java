package billgates.view.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainFrame extends JFrame{

    // window width
    public static final int DEFAULT_WIDTH = 1000;
    // window height
    public static final int DEFAULT_HEIGHT = 618;
    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(220, 120, 150, 100);

    private final JPanel contentPane = new JPanel(new BorderLayout());
    private static final ActionPanel actionPanel = new ActionPanel();
    private static final BillPanel billPanel = new BillPanel();
    private final JMenuBar menu = new TopMenuBar();

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    public MainFrame() {
        // Set the title
        super("Bill Gates");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Set the size of the window, and the user cannot resize the window
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setResizable(false);

        // Display the window in the center of the screen
        SwingUtil.centerInScreen(this);

        this.setContentPane(this.contentPane);

        // Set the menu bar
        this.setJMenuBar(menu);

        // Add components
        this.add(this.actionPanel, BorderLayout.WEST);
        this.add(this.billPanel, BorderLayout.CENTER);
    }

    public static BillPanel getBillPanel() {
        return billPanel;
    }

    public static ActionPanel getActionPanel() {
        return actionPanel;
    }
}
