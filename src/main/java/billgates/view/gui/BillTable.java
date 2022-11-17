package billgates.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BillTable extends JTable {
    public static final int DEFAULT_HEADER_HEIGHT = 50;
    public static final int DEFAULT_ROW_HEIGHT = 40;
    public static final int DEFAULT_FONT_SIZE = 15;
    public static final Font DEFAULT_FONT = new FontSettings(DEFAULT_FONT_SIZE);

    public BillTable(Object[][] data, Object[] columnNames) {
        // Set the data and header
        super(data, columnNames);

        // Set the font
        this.setFont(DEFAULT_FONT);
        this.getTableHeader().setFont(DEFAULT_FONT);

        // Set the color
        this.setForeground(ActionButton.DEFAULT_BACKGROUND_COLOR);
        this.getTableHeader().setBackground(ActionButton.DEFAULT_BACKGROUND_COLOR);
        this.getTableHeader().setForeground(ActionButton.DEFAULT_TEXT_COLOR);

        // Set the size
        this.setRowHeight(DEFAULT_ROW_HEIGHT);
        this.getTableHeader().setPreferredSize(new Dimension(BillPanel.DEFAULT_WIDTH, DEFAULT_HEADER_HEIGHT));

        // Set functionality
        this.setCellSelectionEnabled(true);
    }

}
