package billgates.view.gui;

import billgates.view.BillTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte, Scott
 */
public class BillTable extends JTable {

    public static final int DEFAULT_HEADER_HEIGHT = 50;
    public static final int DEFAULT_ROW_HEIGHT = 40;
    public static final int DEFAULT_FONT_SIZE = 15;
    public static final Font DEFAULT_FONT = new FontSettings(DEFAULT_FONT_SIZE);

    private final BillTableModel model = new BillTableModel();

    public BillTable() {
        // Set the data and header
        this.setModel(this.model);

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

    @Override
    public BillTableModel getModel() {
        return this.model;
    }

    /**
     * Initialize the widths of columns of the bill table.
     * This method should only be invoked after the component became visible.
     */
    public void initTableColumns() {
        FontMetrics fontMetrics = this.getGraphics().getFontMetrics(new FontSettings(DEFAULT_FONT_SIZE));
        // id column width
        this.getColumnModel().getColumn(0).setMinWidth(fontMetrics.stringWidth("000"));
        this.getColumnModel().getColumn(0).setPreferredWidth(fontMetrics.stringWidth("0000"));
        // date column width
        this.getColumnModel().getColumn(1).setMinWidth(fontMetrics.stringWidth("yyyy-MM-dd HH:mm:ss"));
    }

}
