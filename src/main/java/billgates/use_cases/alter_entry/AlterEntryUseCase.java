package billgates.use_cases.alter_entry;

import billgates.entities.Entry;
import billgates.entities.User;
import billgates.interface_adapters.DatabaseGateway;

import java.time.ZonedDateTime;
import java.util.Map;

/**
 * Clean Architecture Layer: Application Business Rules
 * A concrete implementation of the <code>AlterEntryInputPort</code>.
 *
 * @author Brandon Fu
 * @see AlterEntryInputPort
 * @see AlterEntryRequestModel
 */

public class AlterEntryUseCase implements AlterEntryInputPort {
    private final DatabaseGateway gateway;

    public AlterEntryUseCase(DatabaseGateway gateway) {
        this.gateway = gateway;
    }

    /**
     * This method aims to change multiple columns of an entry once.
     *
     * @param entryId   the ID of the entry which have a value we want to change.
     * @param changeMap the map which has the alter column as key and new value as key
     */
    @Override
    public void alterEntry(int entryId, Map<String, Object> changeMap) {
        int billId = User.getInstance().getCurrentBillID();

        Entry oldEntry = this.gateway.getEntryData(billId, entryId);
        AlterEntryRequestModel model = new AlterEntryRequestModel(oldEntry);
        for (Map.Entry<String, Object> changeSet : changeMap.entrySet()) {
            String alterColumn = changeSet.getKey();
            Object newValue = changeSet.getValue();


            if (alterColumn.equals("Date")) {
                model.setDate((ZonedDateTime) newValue);
            }
            if (alterColumn.equals("Value")) {
                model.setValue((double) newValue);
            }
            if (alterColumn.equals("Currency")) {
                model.setCurrency((String) newValue);
            }
            if (alterColumn.equals("Description")) {
                model.setDescription((String) newValue);
            }
            if (alterColumn.equals("From")) {
                model.setFrom((String) newValue);
            }
            if (alterColumn.equals("To")) {
                model.setTo((String) newValue);
            }
            if (alterColumn.equals("Location")) {
                model.setLocation((String) newValue);
            }
        }

        gateway.modifyEntry(billId, model.getEntryData(entryId));

    }

    /**
     * Alters one column in one entry.
     *
     * @param entryId     the ID of the entry which have a value we want to change.
     * @param newValue    the value we want the date to change to, object type
     * @param alterColumn the String representation of the column we want to change
     */
    public void alterEntry(int entryId, Object newValue, String alterColumn) {
        User user = User.getInstance();
        int billId = user.getCurrentBillID();
        String newValueString = newValue.toString();
        if (billId == user.getBillId()) {
            this.gateway.modifyEntry(billId, entryId, alterColumn, newValueString);
        } else {
            if ("Paid Back".equals(alterColumn)) {
                newValueString = ((boolean) newValue) ? "1" : "0";
            }
            this.gateway.modifySplitEntry(billId, entryId, alterColumn, newValueString);
        }
    }

}
