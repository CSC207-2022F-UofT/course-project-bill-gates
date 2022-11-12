package billgates.view.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GeneralMenu extends JMenu {

    public static final int DEFAULT_FONT_SIZE = 12;
    public static final Font DEFAULT_FONT = new Font("Modern No. 20", Font.BOLD, DEFAULT_FONT_SIZE);

    public GeneralMenu(String text) {
        super(text);
        this.setFont(DEFAULT_FONT);
        this.setForeground(ActionLabel.DEFAULT_Text_COLOR);
    }
}
