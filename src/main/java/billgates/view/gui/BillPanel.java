package billgates.view.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BillPanel extends JPanel {

    public static final int DEFAULT_WIDTH = MainFrame.DEFAULT_WIDTH - ActionPanel.DEFAULT_WIDTH - 14;
    // public static final int DEFAULT_HEIGHT = MainFrame.DEFAULT_HEIGHT - 37;
    private static final String[] COLUMN_NAMES = new String[]
            {"ID", "Date", "Value", "Currency", "Description", "From", "To", "Location"};
    // This data is just for test, will be modified in the future
    private final Object[][] testData = {{0, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1}};
    private final JTable billTable = new BillTable(this.testData, COLUMN_NAMES);
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

    public JTable getBillTable() {
        return this.billTable;
    }

    public void changeColor(Color c){
        billTable.getTableHeader().setBackground(c);
        this.setBorder(new CustomTitleBorder("Bills",c));
    }

    public void changeFont(String f){
        Font newTableFont = new FontSettings(f, BillTable.DEFAULT_FONT_SIZE);
        this.billTable.getTableHeader().setFont(newTableFont);
    }
}
