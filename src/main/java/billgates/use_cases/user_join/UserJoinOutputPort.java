package billgates.use_cases.user_join;

/**
 * Clean Architecture Layer: Application Business Rules
 * An output port for the User Join use case.
 * The purpose of this interface is dependency inversion.
 *
 * @author Xinxiang
 * @see UserJoinUseCase
 * @see UserJoinPresenter
 */
public interface UserJoinOutputPort {

    /**
     * Converts the data in model to a GUI-friendly format and
     * updates the GUI correspondingly.
     *
     * @param model the model to convert.
     */
    void display(UserJoinResponseModel model);
}
