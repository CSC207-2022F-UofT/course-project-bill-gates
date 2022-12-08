package billgates.use_cases.user_join;

import java.util.Objects;

/**
 * Clean Architecture Layer: Interface Adapters
 * A view model holding information for the GUI to display.
 *
 * @author Xinxiang
 * @see billgates.interface_adapters.UserJoinUpdatable
 * @see UserJoinPresenter
 */
public class UserJoinViewModel {

    /**
     * Whether the user is joined.
     */
    private boolean isJoined;
    /**
     * The reason that the view model is presented.
     */
    private String reasonRejected;

    public UserJoinViewModel(boolean isJoined, String reasonRejected) {
        this.isJoined = isJoined;
        this.reasonRejected = reasonRejected;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        UserJoinViewModel that = (UserJoinViewModel) obj;
        return this.isJoined == that.isJoined &&
                Objects.equals(this.reasonRejected, that.reasonRejected);
    }

    public boolean isJoined() {
        return this.isJoined;
    }

    // This warning shouldn't be resolved because this is just a setter.
    public void setJoined(boolean joined) {
        this.isJoined = joined;
    }

    public String getReasonRejected() {
        return this.reasonRejected;
    }

    // This warning shouldn't be resolved because this is just a setter.
    public void setReasonRejected(String reasonRejected) {
        this.reasonRejected = reasonRejected;
    }
}
