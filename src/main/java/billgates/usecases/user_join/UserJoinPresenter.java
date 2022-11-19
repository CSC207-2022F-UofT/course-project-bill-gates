package billgates.usecases.user_join;

import billgates.interface_adapters.Viewable;

public class UserJoinPresenter implements UserJoinOutputPort{

    private Viewable view;

    public UserJoinPresenter(Viewable view){
        this.view = view;
    }

    public Viewable getView() {
        return view;
    }

    public void setView(Viewable view) {
        this.view = view;
    }

    @Override
    public void display(UserJoinResponseModel model) {
        this.view.view(new UserJoinViewModel(model.getIsJoined(), model.getReasonRejected()));
    }
}
