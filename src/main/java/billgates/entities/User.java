package billgates.entities;

import java.util.List;
import java.util.Objects;

// singleton
public class User {

    private static User instance;
    private int id;
    private String name;
    private String password;
    private int billID;
    private int currentBillID;
    private List<AbstractEntry> currentBill;

    private User(int id, String name, String password, int billID) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.billID = billID;
    }

    public static User getInstance(int id, String name, String password, int billID) {
        if (instance == null)
            instance = new User(id, name, password, billID);
        return instance;
    }

    /**
     * Precondition: instance != null
     * This function returns the current User instance or throw a runtime exception if the current user does not exist.
     *
     * @return The current User instance.
     */
    public static User getInstance() {
        if (instance == null) throw new
                NullPointerException("Failed to obtain the current user. User have not logged in yet.");
        return instance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getCurrentBillID() {
        return currentBillID;
    }

    public void setCurrentBillID(int currentBillID) {
        this.currentBillID = currentBillID;
    }
}
