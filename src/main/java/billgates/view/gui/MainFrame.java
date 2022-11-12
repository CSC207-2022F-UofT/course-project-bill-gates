package billgates.view.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainFrame extends JFrame {

    // window width
    public static final int DEFAULT_WIDTH = 1000;
    // window height
    public static final int DEFAULT_HEIGHT = 618;
    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(220, 120, 150, 100);

    private final JPanel contentPane = new JPanel(new BorderLayout());
    private final ActionPanel actionPanel = new ActionPanel();
    private final BillPanel billPanel = new BillPanel();

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);

        //System.out.println(mainFrame.getContentPane().getSize());
    }

    public MainFrame() {
        super("Bill Gates");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.setResizable(true);
        this.setContentPane(this.contentPane);
        SwingUtil.centerInScreen(this);

//        System.out.println(this.getWidth());
//        System.out.println(this.actionPanel.getWidth());
//        System.out.println(this.billPanel.getWidth());

        this.add(this.actionPanel, BorderLayout.WEST);
        this.add(this.billPanel, BorderLayout.CENTER);

    }
}