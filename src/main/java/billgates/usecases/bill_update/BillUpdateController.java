package billgates.usecases.bill_update;

public class BillUpdateController {

    private final BillUpdateInputPort useCase;

    public BillUpdateController(BillUpdateInputPort useCase) {
        this.useCase = useCase;
    }

    public void update(int billId) {
        this.useCase.updateBill(billId);
    }
}
