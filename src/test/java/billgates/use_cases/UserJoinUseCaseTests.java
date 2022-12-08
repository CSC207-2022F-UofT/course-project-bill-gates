package billgates.use_cases;

import billgates.database.MySQLDatabaseGateway;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;
import billgates.use_cases.user_join.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A test for <code>UserJoinUseCase</code>.
 * This test utilizes the User with user ID 243545 and a bill ID of 756432 as a standard user in the database,
 * and we test registering new user, logging in the standard user and denying the log in process.
 * The user in the database created either before or during the test will be deleted.
 *
 * @author Xinxiang
 * @see billgates.use_cases.user_join
 */

public class UserJoinUseCaseTests {

    private DatabaseGateway gateway = new MySQLDatabaseGateway();
    private UserJoinController controller;
    private UserJoinPresenter presenter;
    private UserJoinUseCase useCase;
    private UserJoinViewModel viewModel;
    private final int userID = 243545;
    private final int billID = 756432;
    private final User user = User.createInstance(userID, "userjoin", "usecasetest", billID);

    @Before
    public void setUp() {
        this.gateway.setUserId(this.user.getId());
        this.gateway.insertUser(this.user.getQueryUserData());
        this.gateway.createBillTable(this.user.getBillId());
        this.presenter = new UserJoinPresenter(
                viewModel -> UserJoinUseCaseTests.this.viewModel = viewModel);
        this.useCase = new UserJoinUseCase(this.gateway, this.presenter);
        this.controller = new UserJoinController(this.useCase);
    }

    @After
    public void tearDown() {
        this.gateway.cleanUser(this.user.getQueryUserData());
    }

    @Test
    public void testRegisterNewUser() throws InterruptedException {
        UserJoinViewModel expected = new UserJoinViewModel(true, "Registered successfully");
        this.controller.userJoin("matter", "123456");
        Assert.assertEquals(expected, this.viewModel);
        this.gateway.deleteUser("matter");
    }

    @Test
    public void testLoginExistingUser() throws InterruptedException {
        UserJoinViewModel expected = new UserJoinViewModel(true, "Logged in successfully");
        this.controller.userJoin("userjoin", "usecasetest");
        Assert.assertEquals(expected, this.viewModel);
    }

    @Test
    public void testWrongPasswordForLogin() throws InterruptedException {
        UserJoinViewModel expected = new UserJoinViewModel(false, "Incorrect password " +
                "or the username already exists.");
        this.controller.userJoin("userjoin", "293840");
        Assert.assertEquals(expected, this.viewModel);
    }
}
