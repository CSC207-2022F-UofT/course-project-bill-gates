package billgates.use_cases;

import billgates.database.MySQLDatabaseGateway;
import billgates.entities.*;
import billgates.interface_adapters.DatabaseGateway;
import billgates.use_cases.insert_entry.InsertEntryController;
import billgates.use_cases.insert_entry.InsertEntryRequestModel;
import billgates.use_cases.insert_entry.InsertEntryUseCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * A test for <code>InsertEntryUseCase</code>
 * This test utilizes 7777777 as the user id and bill id.
 * The test user and test bills will be deleted after the test.
 *
 * @author Ruby
 * @see billgates.use_cases.insert_entry
 */
public class InsertEntryUseCaseTests {

    public static final int USER_ID = 7777777;

    private final DatabaseGateway gateway = new MySQLDatabaseGateway();
    private InsertEntryController controller;
    private final User user = User.createInstance(USER_ID, "test_user", "test_user", USER_ID);

    @Before
    public void setUp() {
        this.gateway.setUserId(this.user.getId());
        this.gateway.insertUser(this.user.getQueryUserData());
        this.gateway.createBillTable(this.user.getBillId());
        this.gateway.createSplitBillTable(666666);
        InsertEntryUseCase useCase = new InsertEntryUseCase(this.gateway);
        this.controller = new InsertEntryController(useCase);
    }

    @After
    public void tearDown() {
        this.gateway.cleanUser(this.user.getQueryUserData());
        this.gateway.dropSplitBillTable(666666);
    }

    @Test
    public void testNormalEntry() {
        LocalDateTime datetime = LocalDateTime.now();
        Entry expected = new EntryBuilder()
                .setDate(ZonedDateTime.of(datetime, ZoneId.systemDefault()))
                .setValue(34.2)
                .setCurrency("CAD")
                .setDescription(" ")
                .setFrom(" ")
                .setTo(" ")
                .setLocation(" ")
                .setSplitterBillId(-1)
                .setId(1)
                .buildEntry();
        InsertEntryRequestModel model = new InsertEntryRequestModel(
                ZonedDateTime.of(datetime, ZoneId.systemDefault()),
                34.2,
                "CAD",
                " ",
                " ",
                " ",
                " ");

        this.controller.insert(model);
        Assert.assertEquals(expected, this.gateway.getEntryData(this.user.getBillId(), 1));
    }

    @Test
    public void testSplitterEntry() {
        this.user.setCurrentBillID(666666);

        LocalDateTime datetime = LocalDateTime.now();
        SplitterEntry expected = new EntryBuilder()
                .setDate(ZonedDateTime.of(datetime, ZoneId.systemDefault()))
                .setValue(34.2)
                .setCurrency("CAD")
                .setDescription(" ")
                .setFrom(" ")
                .setTo(" ")
                .setLocation(" ")
                .setPayee(" ")
                .setIsPaidBack(false)
                .setId(1)
                .buildSplitterEntry();
        InsertEntryRequestModel model = new InsertEntryRequestModel(
                ZonedDateTime.of(datetime, ZoneId.systemDefault()),
                34.2,
                "CAD",
                " ",
                " ",
                " ",
                " ",
                " ",
                false);

        this.controller.insert(model);
        Assert.assertEquals(expected, this.gateway
                .getSplitEntryData(this.user.getCurrentBillID(), 1));
    }

}
