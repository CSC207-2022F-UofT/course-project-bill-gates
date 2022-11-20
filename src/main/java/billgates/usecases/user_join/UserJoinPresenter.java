package billgates.usecases.user_join;

import billgates.interface_adapters.UserJoinUpdatable;

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
