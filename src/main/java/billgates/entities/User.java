package billgates.entities;

import java.util.Objects;

/**
 * Clean Architecture Layer: Enterprise Business Rules
 * This class represents a user entity in our program.
 * Only one user is allowed in the running time of our program.
 * We implement the User using singleton design pattern.
 * The UserJoinUseCase will initialize the user by calling getInstance(parameters).
 * Other use cases can use getInstance() (the one without parameters) to obtain the current user.
 *
 * @author Scott
 */
public class User {

    /**
     * The current instance of the User.
     */
    private static User instance;

    /**
     * The id of this user.
     * The id is unique for each user.
     */
    private int id;

    /**
     * The name of this user.
     * The name is unique for each user.
     * The max name length is 10.
     * We may put some constraints on what characters should a username contain.
     */
    private String name;

    /**
     * The password of this user.
     * The max password length is 16.
     * We may implement some encrypt methods in the future to increase program security.
     * We may put some constraints on what characters should a password contain.
     */
    private String password;

    /**
     * The main bill id of this user.
     */
    private int billId;

    /**
     * The current bill id of this user.
     * This may be different that billId because a splitter bill might be opened.
     */
    private int currentBillID;

    private User(int id, String name, String password, int billId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.billId = billId;
        this.currentBillID = billId;
    }

    /**
     * Get or create an instance of a user.
     *
     * @param id the id of this user
     * @param name the name of this user
     * @param password the password of this user
     * @param billID the main bill id of this user
     * @return a new user instance
     */
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

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getCurrentBillID() {
        return currentBillID;
    }

    public void setCurrentBillID(int currentBillID) {
        this.currentBillID = currentBillID;
    }
}
