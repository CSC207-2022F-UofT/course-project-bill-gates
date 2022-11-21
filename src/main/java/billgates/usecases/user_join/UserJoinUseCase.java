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
//        QueryUserData userData = gateway.getUserData();
//        if (!userData.getUsers().contains(model.getUsername())) {
//            // TODO add the user in the database.
//
//            // TODO log the user in
//            outputPort.display(new UserJoinResponseModel(true, "Registered successfully"));
//        }
//        else {
//            int userIndex = userData.getUsers().indexOf(model.getUsername());
//            if (userData.getPasswords().get(userIndex).equals(model.getPassword())) {
//                // TODO log the user in
//                outputPort.display(new UserJoinResponseModel(true, "Logged in successfully"));
//            } else {
//                outputPort.display(new UserJoinResponseModel(false, "Incorrect password " +
//                        "or the username already exists."));
//            }
//            // TODOs above depends on the implementation of DatabaseGateway
//        }
    }
}
