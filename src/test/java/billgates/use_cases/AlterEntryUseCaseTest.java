package billgates.use_cases;

import billgates.TestUtilities;
import billgates.database.MySQLDatabaseGateway;
import billgates.entities.*;
import billgates.interface_adapters.DatabaseGateway;
import billgates.use_cases.alter_entry.AlterEntryController;
import billgates.use_cases.alter_entry.AlterEntryUseCase;
import billgates.use_cases.alter_entry.AlterEntryRequestModel;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * A test for <code>AlterEntryUseCase</code>
 * This test utilizes 666666 as the user id and bill id. And change the entry of it.
 * The test user and test bills will be deleted after the test.
 *
 * @author Brandon Fu
 * @see billgates.use_cases.alter_entry
 */
public class AlterEntryUseCaseTest {
    public static final int USER_ID = 666666;

    private DatabaseGateway gateway = new MySQLDatabaseGateway();
    private AlterEntryRequestModel requestModel;
    private AlterEntryUseCase useCase;
    private AlterEntryController controller;
    private final User user = User.createInstance(USER_ID, "test_user", "test_user", USER_ID);

    @Before
    public void setup() {
        this.gateway.setUserId(this.user.getId());
        this.gateway.insertUser(this.user.getQueryUserData());
        this.gateway.createBillTable(this.user.getBillId());
        this.useCase = new AlterEntryUseCase(this.gateway);
        this.controller = new AlterEntryController(this.useCase);
    }

    @After
    public void teardown() {
        this.gateway.cleanUser(this.user.getQueryUserData());
    }


    @Test
    public void testAlterEntry_location() throws InterruptedException {
        List<AbstractEntry> entries = TestUtilities.generateRandomEntries(1, 1, 0, 50, false);
        entries.forEach(entry -> this.gateway.insertEntry(this.user.getBillId(), (Entry) entry));
        AbstractEntry entry = entries.get(0);
        EntryBuilder builder = new EntryBuilder();
        builder.setCurrency(entry.getCurrency().getAttribute());
        builder.setDescription(entry.getDescription().getAttribute());
        builder.setDate(entry.getDate().getAttribute());
        builder.setFrom(entry.getFrom().getAttribute());
        builder.setTo(entry.getTo().getAttribute());
        builder.setValue(entry.getValue().getAttribute());
        builder.setId(entry.getId().getAttribute());
        builder.setLocation("UofT");
        Entry expect = builder.buildEntry();


        this.controller.alterEntry(this.user.getBillId(),"UofT","Location");

        Assert.assertEquals(expect, this.gateway.getEntryData(this.user.getBillId(), entry.getId().getAttribute()));
    }




    @Test
    public void testAlterEntry_value() throws InterruptedException {
        List<AbstractEntry> entries = TestUtilities.generateRandomEntries(1, 1, 0, 50, false);
        entries.forEach(entry -> this.gateway.insertEntry(this.user.getBillId(), (Entry) entry));
        AbstractEntry entry = entries.get(0);
        EntryBuilder builder = new EntryBuilder();
        builder.setCurrency(entry.getCurrency().getAttribute());
        builder.setDescription(entry.getDescription().getAttribute());
        builder.setDate(entry.getDate().getAttribute());
        builder.setFrom(entry.getFrom().getAttribute());
        builder.setTo(entry.getTo().getAttribute());
        builder.setValue(666666);
        builder.setId(entry.getId().getAttribute());
        builder.setLocation(entry.getLocation().getAttribute());
        Entry expect = builder.buildEntry();


        this.controller.alterEntry(this.user.getBillId(),"666666","Value");

        Assert.assertEquals(expect, this.gateway.getEntryData(this.user.getBillId(), entry.getId().getAttribute()));

    }

}
