package billgates.entities;

public class UserFactory {
    //use getShape method to get object of type shape
    public IUser getUser(int id, String name, String UserType) {
        if (UserType == "DummyUser") {
            IUser d = new DummyUser(id, name);
            return d;
        }
        if (UserType == "User") {
            IUser user = User.getInstance(id, name, "TemporyPW", 0);

            return user;
        }
        return null;
    }

    public IUser getUser(int id, String name,String password,int billID,String UserType) {
        if (UserType == "DummyUser") {
            IUser d = new DummyUser(id, name);
            return d;
        }
        if (UserType == "User") {
            IUser user = User.getInstance(id, name, password, billID);

            return user;
        }
        return null;
    }
}
