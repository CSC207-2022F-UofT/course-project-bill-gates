package billgates.view;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Scott
 */
public class BillTableModel extends AbstractTableModel {

    // columnNames has initial values as defined
    private String[] columnNames = new String[]{"ID", "Date", "Value", "Currency",
            "Description", "From", "To", "Location", "Splitter"};
    private List<List<Object>> data = new ArrayList<>();

    public BillTableModel() {
    }

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.data.get(0).get(columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // splitter is not editable
        if ("Splitter".equals(this.columnNames[columnIndex])) {
            return false;
        }
        // id is not editable
        return columnIndex != 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        this.data.get(rowIndex).set(columnIndex, aValue);
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public int findColumn(String columnName) {
        return super.findColumn(columnName);
    }

    public List<List<Object>> getData() {
        return this.data;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }

    // This warning shouldn't be resolved because this is just a getter.
    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }
}
