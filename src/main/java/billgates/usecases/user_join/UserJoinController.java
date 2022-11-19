package billgates.usecases.user_join;

public class UserJoinController{

    private UserJoinInputPort userCase;

    public UserJoinController(UserJoinInputPort userCase) {
        this.userCase = userCase;
    }

    public UserJoinInputPort getUserCase() {
        return userCase;
    }

    public void setUserCase(UserJoinInputPort userCase) {
        this.userCase = userCase;
    }
}
