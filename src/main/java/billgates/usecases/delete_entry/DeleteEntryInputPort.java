package billgates.usecases.delete_entry;


public interface DeleteEntryInputPort {
    void deleteEntry(int billId, int entryId);
}
