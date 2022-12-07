package billgates.view.gui;

import billgates.interface_adapters.BillPanelUpdatable;
import billgates.use_cases.bill_update.BillUpdateViewModel;
import billgates.view.BillTableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte, Scott, Eva
 */
public class BillPanel extends JPanel implements BillPanelUpdatable {

    public static final int DEFAULT_WIDTH =
            MainFrame.DEFAULT_WIDTH - ActionPanel.DEFAULT_WIDTH - 14;
    // public static final int DEFAULT_HEIGHT = MainFrame.DEFAULT_HEIGHT - 37;

    private final MainFrame mainFrame;

    private final BillTable billTable = new BillTable();

    public BillPanel(MainFrame mainFrame) {
        super(new BorderLayout());
        this.mainFrame = mainFrame;

        JScrollPane scrollPane = new JScrollPane(this.billTable);
        this.add(scrollPane, BorderLayout.CENTER);
        this.initBorder();

        // The bill table is disabled at the beginning
        this.billTable.setEnabled(false);
        this.billTable.setVisible(false);

        // Add mouse event
        this.billTable.addMouseListener(new BillTableMouseAdapter());
        this.billTable.getModel().addTableModelListener(this::billTableModelAltered);
    }

    private void initBorder() {
        TitledBorder billsBorder = new CustomTitleBorder("Bills");
        this.setBorder(billsBorder);
    }

    /**
     * this method will be invoked when there is any change in the table.
     *
     * @param event a TableModelEvent representing any change in the table
     */
    private void billTableModelAltered(TableModelEvent event) {
        if (event.getType() == TableModelEvent.UPDATE &&
                event.getFirstRow() != TableModelEvent.HEADER_ROW) {
            BillTableModel tableModel = this.billTable.getModel();
            String columnName = tableModel.getColumnName(event.getColumn());
            Object value = tableModel.getValueAt(event.getFirstRow(), event.getColumn());
            int entryId = (int) tableModel.getValueAt(event.getFirstRow(), 0);
            this.mainFrame.getAlterEntryController().alterEntry(entryId, value, columnName);
            // we want to update current table
            SwingUtilities.invokeLater(() -> this.mainFrame.getBillUpdateController().update(-1));
        }
    }

    public BillTable getBillTable() {
        return this.billTable;
    }

    // Change color of table header and border in bill panel
    public void changeColor(Color c) {
        this.billTable.getTableHeader().setBackground(c);
        this.setBorder(new CustomTitleBorder("Bills", c));
    }

    // Change font of table header and border in bill panel
    public void changeFont(String f) {
        Font newTableFont = new FontSettings(f, BillTable.DEFAULT_FONT_SIZE);
        this.billTable.getTableHeader().setFont(newTableFont);
    }

    @Override
    public void update(BillUpdateViewModel viewModel) {
        // we use invoke later here because this method is called from other threads.
        SwingUtilities.invokeLater(() -> {
            String[] columns = viewModel.getColumns();
            List<List<Object>> entries = viewModel.getEntries();
            boolean isSplitterBill = viewModel.isSplitterBill();
            BillTableModel model = this.getBillTable().getModel();
            model.setData(entries);
            if (isSplitterBill != this.mainFrame.isSplitterBill()) {
                model.setColumnNames(columns);
                model.fireTableStructureChanged();
                this.billTable.initTableColumns();
                JButton backButton = this.mainFrame.getActionPanel().getBackButton();
                backButton.setEnabled(!backButton.isEnabled() && this.mainFrame.isSignedIn());
            }
            this.billTable.updateUI();
            this.mainFrame.setSplitterBill(isSplitterBill);
        });
    }

    /**
     * This class serves as the mouse events listener for the bill table.
     */
    private class BillTableMouseAdapter extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                ActionButton deleteEntryButton = (ActionButton) BillPanel.this.mainFrame.
                        getActionPanel().getDeleteEntryButton();
                deleteEntryButton.setEnabled(true);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 2) {
                // trigger to splitter bill
                Point point = new Point(e.getX(), e.getY());
                int row = BillPanel.this.billTable.rowAtPoint(point);
                int column = BillPanel.this.billTable.columnAtPoint(point);
                if (row == -1 || column == -1)
                    return;
                String name = BillPanel.this.billTable.getColumnName(column);
                if (!"Splitter".equals(name))
                    return;
                // get the entry id
                int entryId = (int) BillPanel.this.getBillTable().getModel().getValueAt(row, 0);
                // call the bill update use case on the entryId
                SwingUtilities.invokeLater(() ->
                        BillPanel.this.mainFrame.getBillUpdateController().update(entryId));
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            String text = BillPanel.this.billTable.getData().toString().replaceAll("[\\[\\]]", "");
            BillPanel.this.mainFrame.getActionPanel().getStatisticsTextArea().setText(text);
        }
    }
}
