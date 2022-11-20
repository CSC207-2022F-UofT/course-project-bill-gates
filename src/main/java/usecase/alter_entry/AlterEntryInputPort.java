package usecase.alter_entry;

public interface AlterEntryInputPort {
    void alterEntry(int entryID, Object newValue, String alterColumn);
}
