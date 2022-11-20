package usecase.alter_entry;

public interface AlterEntryInputPort {
    void alterEntry(int entry_id, Object new_value, String alter_column);
}
