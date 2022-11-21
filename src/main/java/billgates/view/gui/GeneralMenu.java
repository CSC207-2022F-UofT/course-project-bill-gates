package billgates.view.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte
 */
public class GeneralMenu extends JMenu {

    public static final int DEFAULT_FONT_SIZE = 12;
    public static final Font DEFAULT_FONT = new FontSettings(DEFAULT_FONT_SIZE);

    public GeneralMenu(String text) {
        super(text);
        this.setFont(DEFAULT_FONT);
        this.setForeground(ActionLabel.DEFAULT_TEXT_COLOR.darker());
    }
}
