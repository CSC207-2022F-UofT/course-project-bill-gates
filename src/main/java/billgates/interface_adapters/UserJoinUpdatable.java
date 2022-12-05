package billgates.interface_adapters;

import billgates.use_cases.user_join.UserJoinViewModel;

/**
 * Clean Architecture Layer: Interface Adapters
 * An interface represents that a view is updatable with respect to the
 * <code>UserJoinViewModel</code>.
 * The purpose of this interface is dependency inversion.
 *
 * @author Xinxiang
 * @see UserJoinViewModel
 */
public interface UserJoinUpdatable {

    /**
     * Updates the table according to the <code>viewModel</code>.
     *
     * @param viewModel a data structure specifying the data required for this update.
     */
    void view(UserJoinViewModel viewModel);

}
