package billgates.view;

import javax.swing.table.AbstractTableModel;
import java.util.List;

// Frameworks and Drivers Layer
public class BillTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = new String[]
            {"ID", "Date", "Value", "Currency", "Description", "From", "To", "Location"};
    private List<List<Object>> data;

    public BillTableModel(List<List<Object>> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data.get(rowIndex).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return this.data.get(0).get(columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        this.data.get(rowIndex).set(columnIndex, aValue);
        this.fireTableCellUpdated(rowIndex, columnIndex);
    }

    // TODO override more methods

    public List<List<Object>> getData() {
        return this.data;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }
}
