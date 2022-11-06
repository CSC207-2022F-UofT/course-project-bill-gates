package billgates.entities;

public class UserFactory {
    //use getUser method to get object of type that implements Iuser
    private int temporaryID;
    private String temporaryName;
    public String temporaryPW = "temporaryPW";
    private int temporaryBillID =0;


    public UserFactory( int temporaryBillID, String temporaryPW){
        this.temporaryBillID=temporaryBillID;
        this.temporaryPW=temporaryPW;
    }
    public UserFactory(int temporaryID,String temporaryName,int temporaryBillID, String temporaryPW){
        this.temporaryID=temporaryID;
        this.temporaryName=temporaryName;
        this.temporaryBillID=temporaryBillID;
        this.temporaryPW=temporaryPW;

    }

    public UserFactory(){}
    // so we can have a quick consturt of Userfactory


    public IUser getUser(int id, String name, String UserType) {
        // creat user with only id and name
        if (UserType == "DummyUser") {
            IUser d = new DummyUser(id, name);
            return d;
        }
        if (UserType == "User") {
            IUser user = User.getInstance(id, name, temporaryPW, temporaryBillID);

            return user;
        }
        return null;
    }

    public IUser getUser(int id, String name,String password,int billID,String UserType) {
        if (UserType == "DummyUser") {
            return getUser(id,name, UserType);
        }

        if (UserType == "User") {
            IUser user = User.getInstance(id, name, password, billID);
            return user;
        }
        return null;
    }
    public IUser quickGetUser(String UserType) {
        return getUser(temporaryID, temporaryName, temporaryPW, temporaryBillID, UserType);
    }



    public void setTemporaryBillID(int temporaryBillID) {
        this.temporaryBillID = temporaryBillID;
    }

    public void setTemporaryPW(String temporaryPW) {
        this.temporaryPW = temporaryPW;
    }

    public void setTemporaryID(int temporaryID) {
        this.temporaryID = temporaryID;
    }

    public void setTemporaryName(String temporaryName) {
        this.temporaryName = temporaryName;
    }
}
