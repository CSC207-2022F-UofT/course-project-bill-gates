package usecase.alter_entry;

public class AlterEntryController {
    private final AlterEntryInputPort useCase;

    public AlterEntryController(AlterEntryInputPort useCase) {
        this.useCase = useCase;
    }

    public void alterEntry(int entry_id, Object new_value, String alter_column) {
        this.useCase.alterEntry(entry_id, new_value, alter_column);
    }
}
