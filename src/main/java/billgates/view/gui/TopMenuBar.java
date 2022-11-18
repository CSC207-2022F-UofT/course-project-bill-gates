package billgates.view.gui;

import javax.swing.*;
import java.awt.*;

public class TopMenuBar extends JMenuBar {

    public static final int DEFAULT_HEIGHT = 23;
    private final BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
    private final JMenu importMenu = new GeneralMenu("Import");
    private final JMenu settingsMenu = new GeneralMenu("Settings");
    private final JMenu helpMenu = new GeneralMenu("Help");

    public TopMenuBar() {
        // Set the layout of the top menubar
        this.setLayout(this.layout);

        this.initMenu();
        this.setBorderPainted(false);
        this.setBackground(ActionTextArea.DEFAULT_BACKGROUND_COLOR);
        this.setPreferredSize(new Dimension(MainFrame.DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    private void initMenu() {
        // Add menus with gaps
        this.add(Box.createRigidArea(new Dimension(ActionPanel.VERTICAL_GAP, 0)));

        this.add(this.importMenu);
        this.add(Box.createRigidArea(new Dimension(ActionPanel.VERTICAL_GAP, 0)));
        // importMenu should be disabled  before signing in
        this.importMenu.setEnabled(false);

        this.add(this.settingsMenu);
        this.add(Box.createRigidArea(new Dimension(ActionPanel.VERTICAL_GAP, 0)));

        this.add(this.helpMenu);
    }

    public JMenu getImportMenu() {
        return importMenu;
    }
}
