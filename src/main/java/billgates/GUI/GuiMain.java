package billgates.GUI;

import javax.swing.*;

public class GuiMain {
    public static void main(String[] args) {
        // Set a window
        JFrame frame = new Frame("BILL GATES");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window
        frame.setSize(1000, (int) (1000 * 0.618));

        // User cannot resize the window
        frame.setResizable(false);

        // Display the window in the center of the screen
        SwingUtil.centerInScreen(frame);
        frame.setVisible(true);
    }
}
