package billgates.view.gui;

import javax.swing.*;
import java.awt.*;

public class ActionLabel extends JLabel {
    public static final int DEFAULT_FONT_SIZE = 13;
    public static final Font DEFAULT_FONT = new Font("Modern No. 20", Font.BOLD, DEFAULT_FONT_SIZE);

    public static final Color DEFAULT_BACKGROUND_COLOR = Color.GRAY;

    public ActionLabel(String text){
        super(text);
        this.setFont(DEFAULT_FONT);
        this.setForeground(DEFAULT_BACKGROUND_COLOR);
    }
}
