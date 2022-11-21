package billgates.view.gui;

import java.awt.*;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte
 */
public class SwingUtil {
    public static void centerInScreen(Window w) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int x = (screenSize.width - w.getWidth()) / 2;
        int y = (screenSize.height - w.getHeight()) / 2;
        w.setLocation(x, y);
    }

    public static void centerInOwner(Window win, Window owner) {
        // Get the position of the original window
        Rectangle ownerRect = owner.getBounds();

        // Show it in the center of the original window
        int width = win.getWidth();
        int height = win.getHeight();
        int x = ownerRect.x + (ownerRect.width - width) / 2;
        int y = ownerRect.y + (ownerRect.height - height) / 2;
        win.setBounds(x, y, width, height);
    }
}
