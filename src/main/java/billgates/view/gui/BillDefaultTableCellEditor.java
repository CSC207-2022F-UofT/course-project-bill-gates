package billgates.view.gui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class BillDefaultTableCellEditor extends DefaultCellEditor {

    public BillDefaultTableCellEditor(JComboBox<?> comboBox) {
        super(comboBox);
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        if (super.isCellEditable(anEvent)) {
            if (anEvent instanceof MouseEvent mouseEvent) {
                return mouseEvent.getClickCount() == 2;
            }
        }
        return false;
    }
}
