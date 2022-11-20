package billgates.usecases.user_join;

import billgates.database.QueryUserData;
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
        List<QueryUserData> userData = gateway.getUserData();
        // Check whether the user is in database.
        for (QueryUserData user: userData) {
            if (user.getUsername().equals(model.getUsername())) { // We found one that matches the username.
                if (user.getPassword().equals(model.getPassword())) { //This is the one!
                    //TODO: log in the user
                }
                else {
                outputPort.display(new UserJoinResponseModel(false, "Wrong password."));
                }
                return;
            }
        }
        //Then the rest is for the not-detected in database.
        outputPort.display(new UserJoinResponseModel(false, "Username not in database."));
    }
}
