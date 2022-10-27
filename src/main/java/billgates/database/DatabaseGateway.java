package billgates.database;

import java.time.ZonedDateTime;

public abstract class DatabaseGateway {
    public abstract void initializeConnection();
    public abstract QueryUserData getUserData();
    public abstract QueryBillData getBillData(int billId);
    public abstract QueryBillData getBillData(int billId, ZonedDateTime startDate, ZonedDateTime endDate);
    public abstract QueryEntryData getEntryData(int entryId, int billId);
}
