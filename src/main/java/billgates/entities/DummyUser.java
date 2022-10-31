package billgates.entities;

import java.util.Objects;

public class DummyUser implements IUser {

    private int id;
    private String name;

    public DummyUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        DummyUser dummyUser = (DummyUser) o;
        return id == dummyUser.id && this.name.equals(dummyUser.name);
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
}
