package billgates.view.gui;

import billgates.view.BillGatesUtilities;
import billgates.view.BillTableModel;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

        // The user cannot reorder the columns of the table
        this.getTableHeader().setReorderingAllowed(false);
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

        ActionComboBox<String> comboBox = new ActionComboBox<>(BillGatesUtilities.CURRENCY_CODES);
        comboBox.setFont(ActionTextField.DEFAULT_FONT);
        this.getColumn("Currency").setCellEditor(new BillDefaultTableCellEditor(comboBox));
        this.getColumn("Date").setCellEditor(new DateTableCellEditor());
    }

    public List<String> getData() {
        int[] selectedColumns = this.getSelectedColumns();
        List<String> statistics = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        List<String> currencyValues = new ArrayList<>();
        int valueCol = this.getColumn("Value").getModelIndex();
        int currencyCol = this.getColumn("Currency").getModelIndex();

        if (selectedColumns.length == 1) {
            int[] selectedRows = this.getSelectedRows();
            for (int i : selectedRows) {
                Double value = (Double) this.getValueAt(i, valueCol);
                String currency = (String) this.getValueAt(i, currencyCol);
                values.add(value);
                currencyValues.add(currency);
            }

            if (selectedColumns[0] == valueCol) {
                // Sort the list of values
                Collections.sort(values);
                int length = this.getSelectedRowCount();

                // Calculate the statistics
                String sum = this.calculateSum(values);
                String mean = this.calculateMean(Double.parseDouble(sum), length);
                String median = this.calculateMedian(values, length);
                String max = String.valueOf(values.get(length - 1));
                String min = String.valueOf(values.get(0));

                statistics.add("Sum: " + sum);
                statistics.add("\n\nMean: " + mean);
                statistics.add("\n\nMedian: " + median);
                statistics.add("\n\nMaximum: " + max);
                statistics.add("\n\nMinimum: " + min);
            } else if (selectedColumns[0] == currencyCol) {
                HashMap<String, Integer> currencyToCount = new HashMap<>();
                statistics.add("The number of selected rows: " + this.getSelectedRowCount());

                for (String value : currencyValues) {
                    if (!currencyToCount.containsKey(value)) {
                        currencyToCount.put(value, 1);
                    } else {
                        int count = currencyToCount.get(value) + 1;
                        currencyToCount.put(value, count);
                    }
                }

                for (String key : currencyToCount.keySet()) {
                    statistics.add("\n\n" + key + ": " + currencyToCount.get(key));
                }
            } else {
                statistics.add("The number of selected rows: " + this.getSelectedRowCount());
            }
        }

        return statistics;
    }

    private String calculateSum(List<Double> values) {
        double sum = 0;
        for (double i : values) {
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
        } else {
            median = this.calculateMean(values.get(mid), 1);
        }
        return median;
    }

}
