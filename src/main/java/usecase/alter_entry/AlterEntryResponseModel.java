package usecase.alter_entry;

import java.util.List;

public class AlterEntryResponseModel {
    private List<List<Object>> entries;

    public AlterEntryResponseModel(List<List<Object>> entries) {
        this.entries = entries;
    }

    public List<List<Object>> getEntries() {
        return entries;
    }

    public void setEntries(List<List<Object>> entries) {
        this.entries = entries;
    }
}
