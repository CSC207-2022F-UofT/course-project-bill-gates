package billgates.view.gui;

import billgates.database.MySQLDatabaseGateway;
import billgates.entities.User;
import billgates.interface_adapters.Updatable;
import billgates.usecase_shared_abstract.ViewModel;
import billgates.usecases.bill_update.*;
import billgates.view.BillTableModel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;

/**
 * This is a demo class for demonstrating the UpdateBillUseCase.
 */
public class BillDemo extends JFrame implements Updatable {

    private final JPanel contentPane = new JPanel(new BorderLayout());
    private final JTable table = new JTable();
    private final JScrollPane scrollPane = new JScrollPane(this.table);

    public BillDemo() throws HeadlessException {
        super("Bill Demo");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 618);
        this.setResizable(false);
        this.setLocation(200, 200);
        this.setContentPane(this.contentPane);

        this.initTable();

        JButton testButton = new JButton("Test");
        this.add(testButton, BorderLayout.NORTH);
        testButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.getInstance(0, "Scott", "12345678", 0);
                MySQLDatabaseGateway gateway = new MySQLDatabaseGateway();
                gateway.initializeConnection();
                BillUpdateOutputPort presenter = new BillUpdatePresenter(BillDemo.this);
                BillUpdateInputPort interactor = new BillUpdateUseCase(presenter, gateway);
                interactor.updateBill();
            }
        });
    }

    public static void main(String[] args) {
        BillDemo frame = new BillDemo();
        frame.setVisible(true);

        // a simulation
        User.getInstance(0, "Scott", "12345678", 0);
        MySQLDatabaseGateway gateway = new MySQLDatabaseGateway();
        gateway.initializeConnection();
        BillUpdateOutputPort presenter = new BillUpdatePresenter(frame);
        BillUpdateInputPort useCase = new BillUpdateUseCase(presenter, gateway);
        BillUpdateController controller = new BillUpdateController(useCase);
        controller.update();
    }

    private void initTable() {
        this.add(this.scrollPane, BorderLayout.CENTER);
        this.table.setModel(new BillTableModel(Collections.emptyList()));
        this.table.setRowHeight(40);
//        this.table.setModel(new DefaultTableModel(new Object[][]{{"1"}}, new Object[] {"1"}));
        this.table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                System.out.println(e.getType());
            }
        });
    }

    public JTable getTable() {
        return this.table;
    }

    public JScrollPane getScrollPane() {
        return this.scrollPane;
    }

    @Override
    public void update(ViewModel viewModel) {
        BillTableModel model = (BillTableModel) this.table.getModel();
        model.setData(viewModel.getEntries());
        this.table.updateUI();
    }
}
