package billgates.usecases.insert_entry;

import java.util.List;

public class InsertEntryResponseModel {

    private List<List<Object>> entries;

    public InsertEntryResponseModel(List<List<Object>> entries) {
        this.entries = entries;
    }

    public List<List<Object>> getEntries() {
        return entries;
    }

    public void setEntries(List<List<Object>> entries) {
        this.entries = entries;
    }
}
