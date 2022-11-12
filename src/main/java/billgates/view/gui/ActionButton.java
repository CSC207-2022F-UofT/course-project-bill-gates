package billgates.view.gui;

import javax.swing.*;
import java.awt.*;

public class ActionButton extends JButton {
    public static final int DEFAULT_WIDTH = 170;
    public static final int DEFAULT_HEIGHT = 50;
    public static final int DEFAULT_FONT_SIZE = 16;
    public static final Color DEFAULT_BACKGROUND_COLOR = new Color(240, 140, 170);
    public static final Color DEFAULT_TEXT_COLOR = Color.WHITE;
    public static final Font DEFAULT_FONT = new Font("Modern No. 20", Font.BOLD, DEFAULT_FONT_SIZE);

    public ActionButton(String text){
        super(text);
        this.setMaximumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setFont(DEFAULT_FONT);
        this.setForeground(DEFAULT_TEXT_COLOR);
        setBackground(DEFAULT_BACKGROUND_COLOR);
    }

//    public ActionButton(Icon icon){
//        super(icon);
//        this.setMaximumSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
//        setBackground(DEFAULT_BACKGROUND_COLOR);
//    }

}
