package billgates.use_cases.user_join;

import billgates.entities.QueryUserData;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

/**
 * Clean Architecture Layer: Application Business Rules
 * A concrete implementation of the <code>UserJoinInputPort</code>.
 *
 * @author Xinxiang, Scott, Charlotte
 * @see UserJoinInputPort
 * @see UserJoinPresenter
 * @see UserJoinRequestModel
 */

public class UserJoinUseCase implements UserJoinInputPort {

    /**
     * The presenter for converting the response model to a view model.
     */
    private final DatabaseGateway gateway;

    /**
     * The database gateway for input/output with the database.
     */
    private final UserJoinOutputPort outputPort;

    public UserJoinUseCase(DatabaseGateway gateway, UserJoinOutputPort outputPort) {
        this.gateway = gateway;
        this.outputPort = outputPort;
    }

    @Override
    public void join(UserJoinRequestModel model) {
        boolean exist = this.gateway.getUserData(model.getUsername()) != null;
        // if the user does not exist
        if (!exist) {
            // register
            // Create a QueryUserData in the database
            QueryUserData tempUser = new QueryUserData(model.getUsername(), model.getPassword());
            this.gateway.insertUser(tempUser);
            QueryUserData actualUser = this.gateway.getUserData(model.getUsername());
            // create a new bill
            this.gateway.createBillTable(actualUser.getBillID());
            // set the database user id
            this.gateway.setUserId(actualUser.getUserID());
            // Create a local User
            User.createInstance(actualUser.getUserID(), actualUser.getUsername(),
                    actualUser.getPassword(), actualUser.getBillID());
            // Notify the user that they have successfully registered
            this.outputPort.display(new UserJoinResponseModel(true, "Registered successfully"));
        }
        // if the user exists
        else {
            QueryUserData actualUser = this.gateway.getUserData(model.getUsername());
            // if the password matches
            if (actualUser.getPassword().equals(model.getPassword())) {
                // Create a local User
                User.createInstance(actualUser.getUserID(), actualUser.getUsername(),
                        actualUser.getPassword(), actualUser.getBillID());
                // set the database user id
                this.gateway.setUserId(actualUser.getUserID());
                // Notify the user that they have successfully login
                this.outputPort.display(new UserJoinResponseModel(true, "Logged in successfully"));
            }
            // incorrect password
            else {
                // Notify the user that they couldn't log in
                this.outputPort.display(new UserJoinResponseModel(false, "Incorrect password " +
                        "or the username already exists."));
            }
        }
    }
}
