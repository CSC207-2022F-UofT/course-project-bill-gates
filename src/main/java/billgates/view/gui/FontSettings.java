package billgates.view.gui;

import java.awt.*;

public class FontSettings extends Font{

    public FontSettings(int fontSize) {
        super("Modern No. 20", Font.BOLD, fontSize);
    }

    public FontSettings(String font, int fontSize){
        super(font, Font.BOLD, fontSize);
    }
}
