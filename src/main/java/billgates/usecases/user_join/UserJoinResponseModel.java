package billgates.usecases.user_join;

public class UserJoinResponseModel {

    private boolean isJoined;
    private String reasonRejected;

    public UserJoinResponseModel(boolean isJoined, String reasonRejected){
        this.isJoined = isJoined;
        this.reasonRejected = reasonRejected;
    }

    public boolean getIsJoined(){
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
