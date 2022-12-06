package billgates.view.gui;

import billgates.view.BillGatesUtilities;
import billgates.view.BillTableModel;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte, Scott
 */
public class BillTable extends JTable {

    public static final int DEFAULT_HEADER_HEIGHT = 50;
    public static final int DEFAULT_ROW_HEIGHT = 40;
    public static final int DEFAULT_FONT_SIZE = 12;
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
        this.getTableHeader().setPreferredSize(
                new Dimension(BillPanel.DEFAULT_WIDTH, DEFAULT_HEADER_HEIGHT));

        // Set functionality
        this.setCellSelectionEnabled(true);
    }

    @Override
    public BillTableModel getModel() {
        return this.model;
    }

    /**
     * Initializes the widths of columns of the bill table and the cell editors for some columns.
     * This method should only be invoked after the component became visible.
     */
    public void initTableColumns() {
        FontMetrics fontMetrics = this.getGraphics().getFontMetrics(
                new FontSettings(DEFAULT_FONT_SIZE));
        // id column width
        this.getColumnModel().getColumn(0).setMinWidth(fontMetrics.stringWidth("000"));
        this.getColumnModel().getColumn(0).setPreferredWidth(fontMetrics.stringWidth("0000"));
        // date column width
        this.getColumnModel().getColumn(1).setMinWidth(new DateTimePicker().getMinimumSize().width);
        this.getColumnModel().getColumn(3).setMaxWidth(fontMetrics.stringWidth("Currency   "));
        try {
            this.getColumn("Splitter").setMaxWidth(fontMetrics.stringWidth("Splitter   "));
        } catch (IllegalArgumentException ignore) {
        }

        this.getColumn("Currency").setCellEditor(new ConstraintTableCellEditor(new JTextField(),
                s -> s.length() == 3));
        this.getColumn("Date").setCellEditor(new DateTableCellEditor());
    }

    public List<String> getData() {
        int[] selectedColumns= this.getSelectedColumns();
        List<String> statistics = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        int valueCol = 2;
        int currencyCol = 3;

        if (selectedColumns.length == 1) {
            if (selectedColumns[0] == valueCol) {
                int[] selectedRows = this.getSelectedRows();
                for (int i:selectedRows) {
                    Double value = (Double) this.getValueAt(i, valueCol);
                    values.add(value);
                }
                Collections.sort(values);
                String sum = this.calculateSum(values);
                String mean = this.calculateMean(Double.parseDouble(sum), this.getSelectedRowCount());
                String median = this.calculateMedian(values, values.size());
                statistics.add("Sum: " + sum);
                statistics.add("\n\nMean: " + mean);
                statistics.add("\n\nMedian: " + median);
            }

            if (selectedColumns[0] == currencyCol) {
                statistics.add("The number of selected rows: " + String.valueOf(this.getSelectedRowCount()));
            }
        }

        return statistics;
    }

    private String calculateSum(List<Double> values) {
        double sum = 0;
        for (double i:values) {
            sum += i;
        }
        return String.format("%.2f", sum);
    }

    private String calculateMean(double sum, int rowsCount) {
        return String.format("%.2f", sum / rowsCount);
    }

    private String calculateMedian(List<Double> values, int n) {
        String median;
        int mid = n / 2;

        if (n % 2 == 0) {
            median = this.calculateMean(values.get(mid - 1) + values.get(mid), 2);
        }
        else {
            median = this.calculateMean(values.get(mid), 1);
        }
        return median;
    }

}
