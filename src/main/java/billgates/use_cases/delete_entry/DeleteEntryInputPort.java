package billgates.use_cases.delete_entry;

/**
 * Clean Architecture Layer: Application Business Rules
 * An input port for the Delete Entry use case.
 *
 * @author Ellen, Scott
 * @see DeleteEntryUseCase
 */
public interface DeleteEntryInputPort {
    /**
     * This method will delete the entry with corresponding entryID in the database.
     *
     * @param entryId the entryId that need to be deleted.
     */
    void deleteEntry(int entryId);
}
