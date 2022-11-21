package billgates.database;

import java.util.List;

/**
 * Clean Architecture Layer: Application Business Rules
 *
 * @author Ray
 */
public class QueryUserData {
    private final List<String> users;
    private final List<String> passwords;

    public QueryUserData(List<String> users, List<String> passwords) {
        this.users = users;
        this.passwords = passwords;
    }

    public List<String> getUsers() {
        return users;
    }

    public List<String> getPasswords() {
        return passwords;
    }
}
