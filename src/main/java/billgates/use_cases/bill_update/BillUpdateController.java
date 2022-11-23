package billgates.use_cases.bill_update;

/**
 * Clean Architecture Layer: Interface Adapters
 * This class serves as the controller of the Bill Update Use Case.
 * It is only responsible for accepting the input (<code>billId</code>)
 * from the user and invoke the corresponding use case.
 *
 * @author Scott
 * @see BillUpdateInputPort
 */
public class BillUpdateController {

    private final BillUpdateInputPort useCase;

    public BillUpdateController(BillUpdateInputPort useCase) {
        this.useCase = useCase;
    }

    public void update(int billId) {
        this.useCase.updateBill(billId);
    }
}
