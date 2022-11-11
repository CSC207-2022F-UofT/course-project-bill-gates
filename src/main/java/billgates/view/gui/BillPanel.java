package billgates.view.gui;

import javax.swing.*;
import java.awt.*;

public class BillPanel extends JPanel {

    public static final int DEFAULT_WIDTH = MainFrame.DEFAULT_WIDTH - ActionPanel.DEFAULT_WIDTH - 14;
    public static final int DEFAULT_HEIGHT = MainFrame.DEFAULT_HEIGHT - 37;

    public BillPanel() {
        super(new BorderLayout());
        this.setBackground(MainFrame.DEFAULT_BACKGROUND_COLOR);
    }
}
