package billgates.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte, Eva
 */
public class ActionLabel extends JLabel {
    public static final List<ActionLabel> allLabel = new ArrayList<>();
    public static final int DEFAULT_FONT_SIZE = 13;
    public static final Font DEFAULT_FONT = new FontSettings(DEFAULT_FONT_SIZE);

    public static final Color DEFAULT_TEXT_COLOR = Color.GRAY;

    public ActionLabel(String text) {
        super(text);
        this.setFont(DEFAULT_FONT);
        this.setForeground(DEFAULT_TEXT_COLOR);
        allLabel.add(this);
    }
}
