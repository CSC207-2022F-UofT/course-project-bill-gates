package billgates.use_cases.user_join;

/**
 * Clean Architecture Layer: Application Business Rules
 * A response model of the User Join use case.
 * It is used to transfer data from the use case to the presenter.
 *
 * @author Xinxiang
 * @see UserJoinUseCase
 * @see UserJoinOutputPort
 * @see UserJoinPresenter
 */
public class UserJoinResponseModel {

    private boolean isJoined;
    private String reasonRejected;

    public UserJoinResponseModel(boolean isJoined, String reasonRejected) {
        this.isJoined = isJoined;
        this.reasonRejected = reasonRejected;
    }

    public boolean getIsJoined() {
        return this.isJoined;
    }

    public void setJoined(boolean joined) {
        this.isJoined = joined;
    }

    public String getReasonRejected() {
        return this.reasonRejected;
    }

    public void setReasonRejected(String reasonRejected) {
        this.reasonRejected = reasonRejected;
    }
}
