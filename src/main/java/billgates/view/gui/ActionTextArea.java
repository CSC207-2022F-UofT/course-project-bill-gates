package billgates.view.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte
 */
public class ActionTextArea extends JTextArea {

    public static final int DEFAULT_FONT_SIZE = 14;
    public static final Font DEFAULT_FONT = new FontSettings(DEFAULT_FONT_SIZE);
    public static final int DEFAULT_HEIGHT = ActionPanel.DEFAULT_HEIGHT / 5 * 2;
    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(230, 230, 230);

    public ActionTextArea(String text) {
        // Set the text
        super(text);

        // Set the font and colors
        this.setFont(DEFAULT_FONT);
        this.setForeground(CustomTitleBorder.DEFAULT_BORDER_TEXT_COLOR);
        this.setBackground(DEFAULT_BACKGROUND_COLOR);
        this.setBorder(new CustomTitleBorder("Statistics"));

        // Set the size
        this.setMaximumSize(new Dimension(ActionPanel.DEFAULT_SIGN_IN_PANEL_WIDTH, DEFAULT_HEIGHT));

        // Set this text area not editable
        this.setEditable(false);
    }
}