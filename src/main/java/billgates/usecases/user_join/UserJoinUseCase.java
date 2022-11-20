package billgates.usecases.user_join;

import billgates.database.QueryUserData;
import billgates.interface_adapters.DatabaseGateway;

public class UserJoinUseCase implements UserJoinInputPort {
    private final DatabaseGateway gateway;
    private final UserJoinOutputPort outputPort;

    public UserJoinUseCase(DatabaseGateway gateway, UserJoinOutputPort outputPort){
        this.gateway = gateway;
        this.outputPort = outputPort;
    }

    @Override
    public void join(UserJoinRequestModel model) {
        QueryUserData userData = gateway.getUserData();
        if (!userData.getUsers().contains(model.getUsername())){
            // TODO add the user to the database

            // TODO log the user in

        }
        else {
            // pop a dialog and notify the user that the name is invalid
            outputPort.display(new UserJoinResponseModel(false, "Invalid username."));
        }
    }
}
