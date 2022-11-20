package billgates.usecases.user_join;

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
