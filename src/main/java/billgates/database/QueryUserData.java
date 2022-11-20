package billgates.database;

import java.util.List;

public class QueryUserData {

    private final List<String> userIDs;
    private final List<String> billIDs;
    private final List<String> users;
    private final List<String> passwords;

    public QueryUserData(List<String> userIDs, List<String> billIDs, List<String> users, List<String> passwords) {
        this.userIDs = userIDs;
        this.billIDs = billIDs;
        this.users = users;
        this.passwords = passwords;
    }

    public List<String> getUsers() {
        return users;
    }

    public List<String> getPasswords() {
        return passwords;
    }

    public List<String> getUserIDs() {
        return userIDs;
    }

    public List<String> getBillIDs() {
        return billIDs;
    }
}
