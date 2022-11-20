package billgates.usecases.user_join;

public class UserJoinController{

    private final UserJoinInputPort useCase;

    public UserJoinController(UserJoinInputPort useCase) {
        this.useCase = useCase;
    }

    public void userJoin(String username, String password){
        this.useCase.join(new UserJoinRequestModel(username, password));
    }
}
