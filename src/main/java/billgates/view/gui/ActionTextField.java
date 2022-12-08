package billgates.view.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Ellen
 */
public class ActionTextField extends JTextField {

    public static final int DEFAULT_FONT_SIZE = 14;
    public static final Font DEFAULT_FONT = new FontSettings(DEFAULT_FONT_SIZE);

    public ActionTextField(int columns) {
        super(columns);
        this.setFont(DEFAULT_FONT);
    }

}
