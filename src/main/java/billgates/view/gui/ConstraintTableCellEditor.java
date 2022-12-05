package billgates.view.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.function.Predicate;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 * A table cell editor with an extra constraint.
 * When the user inputs an invalid string, this editor will mark the cell with a red border and
 * prevent setting the invalid value.
 *
 * @author Scott
 * @see BillTable#initTableColumns()
 */
public class ConstraintTableCellEditor extends DefaultCellEditor {

    public static final Border INVALID_BORDER = BorderFactory.createLineBorder(Color.RED, 1);

    private final JTextField textField;

    /**
     * A predicate that returns true if the given string satisfies the condition.
     */
    private final Predicate<String> constraint;

    public ConstraintTableCellEditor(JTextField textField, Predicate<String> constraint) {
        super(textField);
        this.textField = textField;
        this.constraint = constraint;
    }

    @Override
    public boolean stopCellEditing() {
        if (!this.constraint.test(this.textField.getText())) {
            this.textField.setBorder(INVALID_BORDER);
            return false;
        }
        return super.stopCellEditing();
    }
}
