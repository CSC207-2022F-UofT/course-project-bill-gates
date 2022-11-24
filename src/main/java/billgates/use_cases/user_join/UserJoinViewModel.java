package billgates.use_cases.user_join;

public class UserJoinViewModel {

    private boolean isJoined;
    private String reasonRejected;

    public UserJoinViewModel(boolean isJoined, String reasonRejected) {
        this.isJoined = isJoined;
        this.reasonRejected = reasonRejected;
    }

    public boolean isJoined() {
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
