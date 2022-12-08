package billgates.view.gui;

import java.awt.*;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte, Eva
 */
public class FontSettings extends Font{

    public FontSettings(int fontSize) {
        super("Microsoft YaHei UI", Font.BOLD, fontSize);
    }

    // Add a new constructor to change font later, with parameter of font and font size
    public FontSettings(String font, int fontSize){
        super(font, Font.BOLD, fontSize);
    }
}