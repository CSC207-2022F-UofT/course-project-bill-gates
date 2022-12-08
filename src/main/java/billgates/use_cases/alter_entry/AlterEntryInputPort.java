package billgates.use_cases.alter_entry;

import java.util.Map;

/**
 * Clean Architecture Layer: Application Business Rules
 * An input port for the AlterEntry use case.
 *
 * @author Brandon Fu
 * @see AlterEntryUseCase
 */


public interface AlterEntryInputPort {

    /**
     * his method will change the entry with corresponding entryID in the database to the new
     * value according to the map.
     *
     * @param entryId   the ID of the entry which have a value we want to change.
     * @param changeMap the map which has the alter column as key and new value as key
     */

    void alterEntry(int entryId, Map<String, Object> changeMap);

    /**
     * This method will change the entry with corresponding entryID in the database to the
     * new value.
     *
     * @param entryId     the ID of the entry which have a value we want to change.
     * @param newValue    the value we want the date to change to, object type
     * @param alterColumn the String representation of the column we want to change
     */

    void alterEntry(int entryId, Object newValue, String alterColumn);

}
