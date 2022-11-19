package billgates.usecases.user_join;

import billgates.database.QueryUserData;
import billgates.interface_adapters.DatabaseGateway;

import java.util.function.ToDoubleBiFunction;

public class UserJoinUseCase implements UserJoinInputPort{
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
            outputPort.display(new UserJoinResponseModel(false, "Not in database"));

        }
        else {
            join(model);
            outputPort.display(new UserJoinResponseModel(true, null));
        }
    }
}
