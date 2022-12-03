package billgates.use_cases.user_join;

import billgates.interface_adapters.UserJoinUpdatable;

/**
 * Clean Architecture Layer: Interface Adapters
 * A presenter in Clean Architecture for the User Join use case.
 * This presenter is implemented based on the MVC architecture.
 *
 * @author Xinxiang
 * @see UserJoinUpdatable
 */
public class UserJoinPresenter implements UserJoinOutputPort {

    private UserJoinUpdatable view;

    public UserJoinPresenter(UserJoinUpdatable view) {
        this.view = view;
    }

    public UserJoinUpdatable getView() {
        return view;
    }

    public void setView(UserJoinUpdatable view) {
        this.view = view;
    }

    @Override
    public void display(UserJoinResponseModel model) {
        this.view.view(new UserJoinViewModel(model.getIsJoined(), model.getReasonRejected()));
    }
}
