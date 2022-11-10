package billgates.GUI;

import javax.swing.*;
import java.awt.*;

public class GeneralLabel extends JLabel {
    private int size = 13;
    public GeneralLabel(String text){
        super(text);
        this.setFont(new Font("Modern No. 20", Font.BOLD, size));
    }
}
