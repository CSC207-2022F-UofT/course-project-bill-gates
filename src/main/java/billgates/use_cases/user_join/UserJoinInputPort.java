package billgates.use_cases.user_join;

/**
 * Clean Architecture Layer: Application Business Rules
 * An input port for the User Join use case.
 *
 * @author Xinxiang
 * @see UserJoinUseCase
 */
public interface UserJoinInputPort {

    /**
     * This method will detect 3 different scenarios from the
     * <code>UserJoinRequestModel</code> model and take following actions:
     * Case 1: If the username is not in the database,
     * a new user is created and recorded in the database. i.e, register a new user.
     * Case 2: If the username is in the database and the password matches,
     * the corresponding user is logged in.
     * Case 3: If the username is in the database and the password doesn't match,
     * either the username is not valid for a register or the password is incorrect,
     * and the corresponding user is not logged in.
     *
     * @param model the model that needs certain actions above.
     */
    void join(UserJoinRequestModel model);
}
