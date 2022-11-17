package billgates.view.gui;

import java.awt.*;

public class FontSettings extends Font{

    public FontSettings(int fontSize) {
        super("Modern No. 20", Font.BOLD, fontSize);
    }

    // Add a new constructor to change font later, with parameter of font and font size
    public FontSettings(String font, int fontSize){
        super(font, Font.BOLD, fontSize);
    }
}
