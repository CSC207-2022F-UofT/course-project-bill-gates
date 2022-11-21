package billgates.usecases.user_join;

import billgates.database.QueryUserData;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

import java.util.List;

public class UserJoinUseCase implements UserJoinInputPort {
    private final DatabaseGateway gateway;
    private final UserJoinOutputPort outputPort;

    public UserJoinUseCase(DatabaseGateway gateway, UserJoinOutputPort outputPort) {
        this.gateway = gateway;
        this.outputPort = outputPort;
    }

    @Override
    public void join(UserJoinRequestModel model) {
        List<QueryUserData> usersData = this.gateway.getUserData();
        boolean exist = usersData.stream().anyMatch(d -> d.getUsername().equals(model.getUsername()));
        if (!exist) {
            // Create a QueryUserData in the database
            QueryUserData queryUser = new QueryUserData(model.getUsername(), model.getPassword());
            this.gateway.insertUser(queryUser);
            QueryUserData queryUserData = this.gateway.getUserData(model.getUsername());
            // Create a local User
            User.getInstance(queryUserData.getUserID(), queryUserData.getUsername(),
                    queryUserData.getPassword(), queryUserData.getBillID());
            // Notify the user that they have successfully registered
            outputPort.display(new UserJoinResponseModel(true, "Registered successfully"));
        }
        else {
            QueryUserData queryUserData = this.gateway.getUserData(model.getUsername());
            if (queryUserData.getPassword().equals(model.getPassword())) {
                // Create a local User
                User.getInstance(queryUserData.getUserID(), queryUserData.getUsername(),
                        queryUserData.getPassword(), queryUserData.getBillID());
                // Notify the user that they have successfully login
                outputPort.display(new UserJoinResponseModel(true, "Logged in successfully"));
            }
            else {
                // Notify the user that they couldn't log in
                outputPort.display(new UserJoinResponseModel(false, "Incorrect password " +
                        "or the username already exists."));
            }
        }
    }
}
