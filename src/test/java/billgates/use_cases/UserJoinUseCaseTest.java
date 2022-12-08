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
 * This test utilizes
 */

public class UserJoinUseCaseTest {

    private DatabaseGateway gateway = new MySQLDatabaseGateway();
    private UserJoinController controller;
    private UserJoinPresenter presenter;
    private UserJoinUseCase useCase;
    private UserJoinViewModel viewModel;
    private final User user = User.createInstance(243545, "userjoin", "usecasetest", 756432);

    @Before
    public void setUp() {
        this.gateway.setUserId(this.user.getId());
        this.gateway.insertUser(this.user.getQueryUserData());
        this.gateway.createBillTable(this.user.getBillId());
        this.presenter = new UserJoinPresenter(
                viewModel -> UserJoinUseCaseTest.this.viewModel = viewModel);
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
