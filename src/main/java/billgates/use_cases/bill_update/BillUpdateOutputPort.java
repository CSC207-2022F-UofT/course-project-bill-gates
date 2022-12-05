package billgates.use_cases.bill_update;

/**
 * Clean Architecture Layer: Application Business Rules
 * An output port for the Bill Update use case.
 * The purpose of this interface is dependency inversion.
 *
 * @author Scott
 * @see BillUpdateUseCase
 * @see BillUpdatePresenter
 */
public interface BillUpdateOutputPort {

    /**
     * Converts the data in <code>model</code> to a GUI-friendly format and updates the GUI
     * correspondingly.
     *
     * @param model the model to convert.
     */
    void updateBill(BillUpdateResponseModel model);

}

