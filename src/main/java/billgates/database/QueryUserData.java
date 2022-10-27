package billgates.database;

import java.util.ArrayList;

public class QueryUserData {
    private ArrayList<String> users = new ArrayList<>();
    private ArrayList<String> passwords = new ArrayList<>();

    public QueryUserData(ArrayList<String> users, ArrayList<String> passwords) {
        this.users = users;
        this.passwords = passwords;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public ArrayList<String> getPasswords() {
        return passwords;
    }
}
