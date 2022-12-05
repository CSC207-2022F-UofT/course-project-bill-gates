package billgates.interface_adapters;

import billgates.use_cases.bill_update.BillUpdateViewModel;

/**
 * Clean Architecture Layer: Interface Adapters
 * A interface represents that a view is updatable with respect to the
 * <code>BillUpdateViewModel</code>.
 * The purpose of this interface is dependency inversion.
 *
 * @author Scott
 * @see BillUpdateViewModel
 */
public interface BillPanelUpdatable {

    /**
     * Updates the table according to the <code>viewModel</code>.
     *
     * @param viewModel a data structure specifying the data required for this update.
     */
    void update(BillUpdateViewModel viewModel);

}
