package billgates.alter_entry;

import java.util.List;

public class AlterEntryViewModel {
    private List<List<Object>> entries;
    public AlterEntryViewModel(List<List<Object>> entries) {
        this.entries=entries;
    }

    public List<List<Object>> getEntries() {
        return entries;
    }

    public void setEntries(List<List<Object>> entries) {
        this.entries = entries;
    }
}
