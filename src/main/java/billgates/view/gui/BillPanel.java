package billgates.view.gui;

import billgates.interface_adapters.BillPanelUpdatable;
import billgates.usecases.bill_update.BillUpdateViewModel;
import billgates.view.BillTableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BillPanel extends JPanel implements BillPanelUpdatable {

    public static final int DEFAULT_WIDTH = MainFrame.DEFAULT_WIDTH - ActionPanel.DEFAULT_WIDTH - 14;
    // public static final int DEFAULT_HEIGHT = MainFrame.DEFAULT_HEIGHT - 37;
    private final BillTable billTable = new BillTable();
    private final JScrollPane scrollPane = new JScrollPane(this.billTable);

    public BillPanel() {
        super(new BorderLayout());
        this.add(this.scrollPane, BorderLayout.CENTER);
        this.initBorder();

        // The bill table is disabled at the beginning
        this.billTable.setEnabled(false);
        this.billTable.setVisible(false);

        // Add mouse event
        this.billTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    billClick();
                }
            }
        });
    }

    private void initBorder() {
        TitledBorder billsBorder = new CustomTitleBorder("Bills");
        this.setBorder(billsBorder);
    }

    private void billClick() {
        MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
        ActionButton db = (ActionButton) mf.getActionPanel().getDeleteEntryButton();
        db.setEnabled(true);
    }

    public BillTable getBillTable() {
        return this.billTable;
    }

    @Override
    public void update(BillUpdateViewModel viewModel) {
        String[] columns = viewModel.getColumns();
        List<List<Object>> entries = viewModel.getEntries();
        BillTableModel model = (BillTableModel) this.getBillTable().getModel();
        model.setColumnNames(columns);
        model.setData(entries);
        this.getBillTable().updateUI();
    }
}
