package billgates.view.gui;

import javax.swing.*;
import java.awt.*;

public class GeneralButton extends JButton {
    private int width = 120;
    private int height = 50;
    private Color color = new Color(220, 120, 150);
    public GeneralButton(String text, int width, int height, Color color, int size){
        super(text);
        this.setPreferredSize(new Dimension(width, height));
        this.setFont(new Font("Modern No. 20", Font.BOLD, size));
        setBackground(color);
    }
    public GeneralButton(String text){
        super(text);
        this.setPreferredSize(new Dimension(width, height));
        this.setFont(new Font(this.getFont().getName(), Font.BOLD, 16));
        setBackground(color);
    }

    public GeneralButton(Icon icon){
        super(icon);
    }
}
