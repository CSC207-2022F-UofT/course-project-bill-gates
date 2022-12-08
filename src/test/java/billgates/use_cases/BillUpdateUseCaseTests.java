package billgates.use_cases;

import billgates.TestUtilities;
import billgates.database.MySQLDatabaseGateway;
import billgates.entities.AbstractEntry;
import billgates.entities.Entry;
import billgates.entities.SplitterEntry;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;
import billgates.use_cases.bill_update.BillUpdateController;
import billgates.use_cases.bill_update.BillUpdatePresenter;
import billgates.use_cases.bill_update.BillUpdateUseCase;
import billgates.use_cases.bill_update.BillUpdateViewModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * A test for <code>BillUpdateUseCase</code>
 * This test utilizes 666666 as the user id and bill id.
 * The test user and test bills will be deleted after the test.
 *
 * @see billgates.use_cases.bill_update
 */
public class BillUpdateUseCaseTests {

    public static final int USER_ID = 666666;

    private final DatabaseGateway gateway = new MySQLDatabaseGateway();
    private BillUpdateController controller;
    private BillUpdateUseCase useCase;
    private BillUpdateViewModel viewModel;
    private final User user = User.createInstance(USER_ID, "test_user", "test_user", USER_ID);

    @Before
    public void setUp() {
        this.gateway.setUserId(this.user.getId());
        this.gateway.insertUser(this.user.getQueryUserData());
        this.gateway.createBillTable(this.user.getBillId());
        BillUpdatePresenter presenter = new BillUpdatePresenter(
                viewModel -> BillUpdateUseCaseTests.this.viewModel = viewModel);
        this.useCase = new BillUpdateUseCase(presenter, this.gateway);
        this.controller = new BillUpdateController(this.useCase);
    }

    @After
    public void tearDown() {
        this.gateway.cleanUser(this.user.getQueryUserData());
    }

    @Test
    public void testEmptyBill() throws InterruptedException {
        BillUpdateViewModel expected = new BillUpdateViewModel(
                BillUpdatePresenter.MAIN_BILL_COLUMNS, new ArrayList<>(), false);
        this.controller.update(this.user.getBillId());
        for (Thread thread : this.useCase.getThreads()) {
            thread.join();
        }
        Assert.assertEquals(expected, this.viewModel);
    }

    @Test
    public void testRandomBill() throws InterruptedException {
        List<AbstractEntry> entries = TestUtilities.generateRandomEntries(0, 50, 0, 50, false);
        entries.forEach(entry -> this.gateway.insertEntry(this.user.getBillId(), (Entry) entry));
        List<List<Object>> list = TestUtilities.toFormattedEntries(entries, false);
        BillUpdateViewModel expected = new BillUpdateViewModel(
                BillUpdatePresenter.MAIN_BILL_COLUMNS, list, false);
        this.controller.update(this.user.getBillId());
        for (Thread thread : this.useCase.getThreads()) {
            thread.join();
        }
        Assert.assertEquals(expected, this.viewModel);
    }

    @Test
    public void testRandomSplitterBill() throws InterruptedException {
        int splitterBillId = 1;
        this.gateway.createSplitBillTable(splitterBillId);
        List<AbstractEntry> entries = TestUtilities.generateRandomEntries(0, 50, 0, 50, true);
        entries.forEach(entry ->
                this.gateway.insertSplitEntry(splitterBillId, (SplitterEntry) entry));
        List<List<Object>> list = TestUtilities.toFormattedEntries(entries, true);
        BillUpdateViewModel expected = new BillUpdateViewModel(
                BillUpdatePresenter.SPLITTER_BILL_COLUMNS, list, true);
        this.controller.update(splitterBillId);
        for (Thread thread : this.useCase.getThreads()) {
            thread.join();
        }
        Assert.assertEquals(expected, this.viewModel);
        this.gateway.dropSplitBillTable(splitterBillId);
    }



}
