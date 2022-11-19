package billgates.usecases.user_join;

import billgates.database.QueryUserData;
import billgates.interface_adapters.DatabaseGateway;

public class UserJoinUseCase implements UserJoinInputPort{
    private DatabaseGateway gateway;
    private UserJoinOutputPort outputPort;

    public UserJoinUseCase(DatabaseGateway gateway, UserJoinOutputPort outputPort){
        this.gateway = gateway;
        this.outputPort = outputPort;
    }

    @Override
    public void join(UserJoinRequestModel model) {
        QueryUserData userData = gateway.getUserData();
        if (!userData.getUsers().contains(model.getUsername())){
            outputPort.display(new UserJoinResponseModel(false, "Fuck you"));
        }
        else {
            join(model);
        }
    }
}
