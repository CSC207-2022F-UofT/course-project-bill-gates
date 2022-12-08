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

public class DeleteEntryUseCaseTests {
    public static final int USER_ID = 666666;
    private final User user = User.createInstance(USER_ID, "test_user", "test_user", USER_ID);
    private final DatabaseGateway gateway = new MySQLDatabaseGateway();
    private DeleteEntryController controller;

    @Before
    public void setUp() {
        this.gateway.setUserId(this.user.getId());
        this.gateway.insertUser(this.user.getQueryUserData());
        this.gateway.createBillTable(this.user.getBillId());
        DeleteEntryUseCase useCase = new DeleteEntryUseCase(this.gateway);
        this.controller = new DeleteEntryController(useCase);
    }

    @After
    public void tearDown() {
        this.gateway.cleanUser(this.user.getQueryUserData());
    }

    @Test
    public void testOneEntryDeleted() {
        int size = 50;
        List<AbstractEntry> entries = TestUtilities.generateRandomEntries(size, size, 0, 50, false);
        entries.forEach(entry -> this.gateway.insertEntry(this.user.getBillId(), (Entry) entry));
        this.controller.delete(1);
        int expected = this.gateway.getBillData(USER_ID).size();
        Assert.assertEquals(expected, size - 1);
    }

    @Test
    public void testMultipleEntriesDeleted() {
        int minSize = 2;
        int maxSize = 50;
        List<AbstractEntry> entries = TestUtilities.generateRandomEntries(minSize, maxSize, 0, 50,
                false);
        entries.forEach(entry -> this.gateway.insertEntry(this.user.getBillId(), (Entry) entry));
        List<List<Object>> list = TestUtilities.toFormattedEntries(entries, false);

        for (List<Object> objects : list) {
            // objects.get(0) returns the id of this given entry
            this.gateway.deleteEntry(USER_ID, (int) objects.get(0));
        }

        int expected = 0;
        int actual = this.gateway.getBillData(USER_ID).size();

        Assert.assertEquals(expected, actual);
    }

}
