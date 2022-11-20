package billgates.usecases.user_join;

public class UserJoinController{

    private UserJoinInputPort useCase;

    public UserJoinController(UserJoinInputPort useCase) {
        this.useCase = useCase;
    }

    public void setUseCase(UserJoinInputPort useCase) {
        this.useCase = useCase;
    }

    public UserJoinInputPort getUseCase() {
        return useCase;
    }

    public void UserJoin(UserJoinRequestModel model){
        this.useCase.join(model);
    }
}
