package billgates.use_cases.insert_entry;

/**
 * Clean Architecture Layer: Application Business Rules
 * An input port for the Insert Entry use case.
 *
 * @author Ruby, Ellen
 * @see InsertEntryUseCase
 */

public interface InsertEntryInputPort {

    /**
     * Inserts an entry in the database corresponding to the information in the model.
     * It will detect 2 different scenarios from the model and take following actions:
     * Case 1: If this is adding a new entry to the main bill,
     * a new QueryEntryData is created and passed to Gateway#insertEntry.
     * Case 2: If this is adding a splitter entry to the splitter bill,
     * a new QuerySplitEntryData is created and passed to Gateway#insertSplitEntry.
     *
     * @param model the information about the entry that needs to be inserted into the database.
     */

    void insertEntry(InsertEntryRequestModel model);
}
