package billgates.entities;

/**
 * Clean Architecture Layer: Application Business Rules
 *
 * @author Ray
 */
public class QueryUserData {

    private final int billID;
    private final String username;
    private final String password;
    private int userID = -1;

    public QueryUserData(int userID, int billID, String username, String password) {
        this.userID = userID;
        this.billID = billID;
        this.username = username;
        this.password = password;
    }

    public QueryUserData(String username, String password) {
        this.billID = this.userID;
        this.username = username;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public int getBillID() {
        return billID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
