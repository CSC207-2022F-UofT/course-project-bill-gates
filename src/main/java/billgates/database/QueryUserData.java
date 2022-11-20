package billgates.database;

import java.util.List;

public class QueryUserData {

    private int userID = -1;
    private final int billID;
    private final String username;
    private final String password;

    public QueryUserData(int userID, int billID, String username, String password) {
        this.userID = userID;
        this.billID = billID;
        this.username = username;
        this.password = password;
    }

    public QueryUserData(int billID, String username, String password) {
        this.billID = billID;
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
