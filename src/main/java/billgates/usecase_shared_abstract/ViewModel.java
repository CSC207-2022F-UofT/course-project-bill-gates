package billgates.usecase_shared_abstract;

import java.util.List;

public abstract class ViewModel {
    private List<List<Object>> entries;
    public ViewModel(List<List<Object>> entries) {
        this.entries = entries;
    }

    public List<List<Object>> getEntries() {
        return entries;
    }

    public void setEntries(List<List<Object>> entries) {
        this.entries = entries;
    }
}
