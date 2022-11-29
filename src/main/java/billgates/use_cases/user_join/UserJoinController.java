package billgates.use_cases.user_join;

/**
 * Clean Architecture Layer: Interface Adapters
 * This class serves as the controller of the User Join Use Case.
 * It is only responsible for accepting the input (<code>username</code>,
 * <code>password</code>) from the user and invoke the corresponding use case.
 *
 * @author Xinxiang
 * @see UserJoinInputPort
 */
public class UserJoinController {

    private final UserJoinInputPort useCase;

    public UserJoinController(UserJoinInputPort useCase) {
        this.useCase = useCase;
    }

    public void userJoin(String username, String password) {
        this.useCase.join(new UserJoinRequestModel(username, password));
    }
}
