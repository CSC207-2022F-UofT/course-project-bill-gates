package billgates.use_cases.user_join;

/**
 * Clean Architecture Layer: Application Business Rules
 * A request model of the User Join use case.
 * It is used to transfer input username and password as a whole
 * from the controller to the use case.
 *
 * @author Xinxiang
 * @see UserJoinInputPort
 * @see UserJoinUseCase
 * @see UserJoinController
 */
public class UserJoinRequestModel {
    private String username;
    private String password;

    public UserJoinRequestModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

