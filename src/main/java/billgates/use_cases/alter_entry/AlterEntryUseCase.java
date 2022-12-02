package billgates.use_cases.alter_entry;

import billgates.database.QueryEntryData;
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
     * This method aim to change numbers of column in one entry by once
     * @param entryId   the ID of the entry which have a value we want to change.
     * @param changeMap the map which has the alter column as key and new value as key
     */
    @Override
    public void alterEntry(int entryId, Map<String, Object> changeMap) {
        int billId = User.getInstance().getCurrentBillID();

        QueryEntryData oldEntry = this.gateway.getEntryData(billId, entryId);
        AlterEntryRequestModel model = new AlterEntryRequestModel(oldEntry);
        for (Map.Entry<String, Object> changeSet : changeMap.entrySet()) {
            String alterColumn = changeSet.getKey();
            Object newValue = changeSet.getValue();


            if (alterColumn.equals("date")) {
                model.setDate((ZonedDateTime) newValue);
            }
            if (alterColumn.equals("value")) {
                model.setValue((double) newValue);
            }
            if (alterColumn.equals("currency")) {
                model.setCurrency((String) newValue);
            }
            if (alterColumn.equals("description")) {
                model.setDescription((String) newValue);
            }
            if (alterColumn.equals("from")) {
                model.setFrom((String) newValue);
            }
            if (alterColumn.equals("to")) {
                model.setTo((String) newValue);
            }
            if (alterColumn.equals("location")) {
                model.setLocation((String) newValue);
            }
        }

        gateway.modifyEntry(billId, model.getQueryEntryData(entryId));

    }

    /**
     *  this is the basic alterEntry method that change on column in one entry
     * @param entryId     the ID of the entry which have a value we want to change.
     * @param newValue    the value we want the date to change to, object type
     * @param alterColumn the String representation of the column we want to change
     */

    public void alterEntry(int entryId, Object newValue, String alterColumn) {
        int billId = User.getInstance().getCurrentBillID();
        String newValueString = newValue.toString();
        this.gateway.modifyEntry(billId, entryId, alterColumn, newValueString);
    }

}
