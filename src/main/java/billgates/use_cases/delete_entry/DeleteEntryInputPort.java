package billgates.use_cases.delete_entry;

/**
 * Clean Architecture Layer: Application Business Rules
 *
 * @author Ellen, Scott
 */
public interface DeleteEntryInputPort {
    void deleteEntry(int entryId);
}
