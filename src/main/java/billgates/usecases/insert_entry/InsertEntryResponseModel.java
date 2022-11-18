package billgates.usecases.insert_entry;

import billgates.database.QueryEntryData;

import java.util.Collections;
import java.util.List;

public class InsertEntryResponseModel {

    private List<List<Object>> entries;

    public InsertEntryResponseModel(List<List<Object>> entries) {
        this.entries = entries;
    }

    public List<List<Object>> getEntries() {
        return entries;
    }

//    public List<List<Object>> addEntry(QueryEntryData entry){
//        List<Object> newEntry = new java.util.ArrayList<>(Collections.emptyList());
//        newEntry.add(entry.getId());
//        newEntry.add(entry.getDate());
//        newEntry.add(entry.getValue());
//        newEntry.add(entry.getCurrency());
//        newEntry.add(entry.getDescription());
//        newEntry.add(entry.getFrom());
//        newEntry.add(entry.getTo());
//        newEntry.add(entry.getLocation());
//        return entries.add(newEntry);
//    }

    public void setEntries(List<List<Object>> entries) {
        this.entries = entries;
    }
}
