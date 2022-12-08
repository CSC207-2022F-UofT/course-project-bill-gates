package billgates.use_cases.alter_entry;

import java.util.Map;

/**
 * Clean Architecture Layer: Interface Adapters
 * This class serves as the controller of the Alter Entry Use Case.
 * It is only responsible for accepting the inputs
 * (<code>entryId<code>, <code>newValue<code>, and <code>alterColumn<code>).
 * or input (<code>entryId<code>, <code>changeMap<code>) from the user and invoke the corresponding
 * use case to change the entry's wanted column to the desired value.
 *
 * @author Brandon Fu
 * @see AlterEntryInputPort
 */

public class AlterEntryController {
    private final AlterEntryInputPort useCase;

    public AlterEntryController(AlterEntryInputPort useCase) {
        this.useCase = useCase;
    }

    public void alterEntry(int entryId, Object newValue, String alterColumn) {
        this.useCase.alterEntry(entryId, newValue, alterColumn);
    }

    // This warning shouldn't be resolved because it is necessary to have this method.
    public void alterEntry(int entryId, Map<String, Object> changeMap) {
        this.useCase.alterEntry(entryId, changeMap);

    }
}
