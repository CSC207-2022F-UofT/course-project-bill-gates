package billgates.view;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

// Frameworks and Drivers Layer
public class BillTableModel extends AbstractTableModel {

    // columnNames has initial values as defined
    private String[] columnNames = new String[]
            {"ID", "Date", "Value", "Currency", "Description", "From", "To", "Location", "Splitter"};
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
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        this.data.get(rowIndex).set(columnIndex, aValue);
        // TODO: alter entry use case here
        // TODO: insert entry use case maybe also here
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

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }
}
