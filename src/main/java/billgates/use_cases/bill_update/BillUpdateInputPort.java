package billgates.use_cases.bill_update;

/**
 * Clean Architecture Layer: Application Business Rules
 * An input port for the Bill Update use case.
 *
 * @author Scott
 * @see BillUpdateUseCase
 */
public interface BillUpdateInputPort {

    /**
     * This method will update the bill view with the entries in the database.
     * If billId == -1, then the bill that the current bill id of the user points to will be
     * displayed.
     * If billId == -2, then the bill that the main bill id of the user points to will be displayed.
     * Otherwise, the bill pointed by billId will be displayed.
     *
     * @param billId the billId that the view need to update to
     */
    void updateBill(int billId);

}
