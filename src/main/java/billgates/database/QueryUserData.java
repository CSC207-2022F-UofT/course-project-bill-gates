package billgates.database;

import java.util.List;

public class QueryUserData {

    private final int userID;
    private final int billID;
    private final String username;
    private final String password;

    public QueryUserData(int userID, int billID, String username, String password) {
        this.userID = userID;
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
