package billgates.use_cases;

import billgates.TestUtilities;
import billgates.database.MySQLDatabaseGateway;
import billgates.entities.AbstractEntry;
import billgates.entities.Entry;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;
import billgates.use_cases.delete_entry.DeleteEntryController;
import billgates.use_cases.delete_entry.DeleteEntryUseCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

/**
 * A test for <code>DeleteEntryUseCase</code>
 * This test utilizes 666666 as the user id and bill id. And change the entry of it.
 * The test user and test bills will be deleted after the test.
 *
 * @author Ellen Zhang
 * @see billgates.use_cases.delete_entry
 */

public class DeleteEntryUseCaseTest {
    public static final int USER_ID = 666666;
    private final User user = User.createInstance(USER_ID, "test_user", "test_user", USER_ID);
    private DatabaseGateway gateway = new MySQLDatabaseGateway();

    private DeleteEntryController controller;
    private DeleteEntryUseCase useCase;

    @Before
    public void setUp() {
        this.gateway.setUserId(this.user.getId());
        this.gateway.insertUser(this.user.getQueryUserData());
        this.gateway.createBillTable(this.user.getBillId());
        this.useCase = new DeleteEntryUseCase(this.gateway);
        this.controller = new DeleteEntryController(this.useCase);
    }

    @After
    public void tearDown() {
        this.gateway.cleanUser(this.user.getQueryUserData());
    }

    @Test
    public void testIsNormalEntryDeleted() throws InterruptedException {
        int size = 50;
        List<AbstractEntry> entries = TestUtilities.generateRandomEntries(0, size, 0, 50, false);
        entries.forEach(entry -> this.gateway.insertEntry(this.user.getBillId(), (Entry) entry));
        List<List<Object>> list = TestUtilities.toFormattedEntries(entries, false);
        this.controller.delete(1);
        int expected = size - 1;
        Assert.assertEquals(expected, 49);
    }

    @Test
    public void testEmptyBillDeleted() throws InterruptedException {
        int size = 0;
        List<AbstractEntry> entries = TestUtilities.generateRandomEntries(0, size, 0, 50, false);
        entries.forEach(entry -> this.gateway.insertEntry(this.user.getBillId(), (Entry) entry));
        List<List<Object>> list = TestUtilities.toFormattedEntries(entries, false);
        this.controller.delete(1);
        int expected = size;
        Assert.assertEquals(expected, 0);
    }

}
