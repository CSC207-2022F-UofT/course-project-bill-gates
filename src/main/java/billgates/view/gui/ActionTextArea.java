package billgates.view.gui;

import javax.swing.*;
import java.awt.*;

public class ActionTextArea extends JTextArea {

    public static final int DEFAULT_FONT_SIZE = 14;
    public static final Font DEFAULT_FONT = new Font("Modern No. 20", Font.PLAIN, DEFAULT_FONT_SIZE);
    public static final int DEFAULT_HEIGHT = ActionPanel.DEFAULT_HEIGHT / 3;
    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(230,230,230);

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
    }
}
