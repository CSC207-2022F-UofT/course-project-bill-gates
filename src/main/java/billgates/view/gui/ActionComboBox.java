package billgates.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Ellen
 */
public class ActionComboBox<E> extends JComboBox<E> {

    public static final int DEFAULT_FONT_SIZE = 14;
    public static final Font DEFAULT_FONT = new FontSettings(DEFAULT_FONT_SIZE);

    public ActionComboBox() {
        super();
        this.setFont(DEFAULT_FONT);
    }

    public ActionComboBox(List<E> elements) {
        this();
        elements.forEach(this::addItem);
    }

}
