package usecase.alter_entry;

public class AlterEntryController {
    private final AlterEntryInputPort useCase;

    public AlterEntryController(AlterEntryInputPort useCase) {
        this.useCase = useCase;
    }

    public void alterEntry(int entryID, Object newValue, String alterColumn) {
        this.useCase.alterEntry(entryID, newValue, alterColumn);
    }
}
