package billgates.usecases.user_join;

public class UserJoinViewModel {

    private boolean isJoined;
    private String reasonRejected;

    public UserJoinViewModel(boolean isJoined, String reasonRejected){
        this.isJoined = isJoined;
        this.reasonRejected = reasonRejected;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean joined) {
        isJoined = joined;
    }

    public String getReasonRejected() {
        return reasonRejected;
    }

    public void setReasonRejected(String reasonRejected) {
        this.reasonRejected = reasonRejected;
    }
}
