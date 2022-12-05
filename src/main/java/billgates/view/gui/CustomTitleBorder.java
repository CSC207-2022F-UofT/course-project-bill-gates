package billgates.view.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte, Eva
 */
public class CustomTitleBorder extends TitledBorder {

    public static final int DEFAULT_BORDER_THICKNESS = 3;
    public static final int DEFAULT_EMPTY_BORDER_THICKNESS = 7;
    public static final Color DEFAULT_BORDER_TEXT_COLOR = new Color(220, 120, 150);
    public static final Border LINE_BORDER = new LineBorder(MainFrame.DEFAULT_BACKGROUND_COLOR,
            DEFAULT_BORDER_THICKNESS, true);
    public static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder(
            DEFAULT_EMPTY_BORDER_THICKNESS, DEFAULT_EMPTY_BORDER_THICKNESS,
            DEFAULT_EMPTY_BORDER_THICKNESS, DEFAULT_EMPTY_BORDER_THICKNESS);

    public static final Border OUTSIDE_BORDER = BorderFactory.createCompoundBorder(EMPTY_BORDER,
            LINE_BORDER);

    public CustomTitleBorder(String text) {
        super(OUTSIDE_BORDER, text);
        this.setTitleColor(DEFAULT_BORDER_TEXT_COLOR);
        this.setBorder(BorderFactory.createCompoundBorder(OUTSIDE_BORDER, EMPTY_BORDER));
    }

    // Add a new constructor to change border color later
    public CustomTitleBorder(String text, Color c) {
        super(OUTSIDE_BORDER, text);
        this.setTitleColor(c);
        this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(EMPTY_BORDER, new LineBorder(c,
                        DEFAULT_BORDER_THICKNESS, true)), EMPTY_BORDER));
    }

}