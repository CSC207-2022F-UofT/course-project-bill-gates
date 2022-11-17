package billgates.view.gui;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class TopMenuBar extends JMenuBar{

    public static final int DEFAULT_HEIGHT = 23;
    private final BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
    private final JMenu importMenu = new GeneralMenu("Import");
    private final JMenu settingsMenu = new GeneralMenu("Settings");
    private final JMenu helpMenu = new GeneralMenu("Help");
    private final Color[] colors = new Color[]{new Color(240, 140, 170), Color.RED, Color.ORANGE,
            Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.PINK, Color.WHITE};
    private final String[] fonts = new String[]{"Modern No. 20", "Times New Roman", "Helvetica", "Arial", "Impact",
            "Verdana", "Century", "Tahoma", "Copperplate"};

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
        this.add(this.settingsMenu);

        // importMenu should be disabled  before signing in
//        this.importMenu.setEnabled(false);

        // Add function to import menu
        importMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                importBills();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });
        this.add(Box.createRigidArea(new Dimension(ActionPanel.VERTICAL_GAP, 0)));

        // Add function to setting menu
        settingsMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                System.out.println("yes");
                int[] setting = setting();

                MainFrame.getBillPanel().changeColor(colors[setting[0]]);
                MainFrame.getActionPanel().changeColor(colors[setting[0]]);

                MainFrame.getActionPanel().changeFont(fonts[setting[1]]);
                MainFrame.getBillPanel().changeFont(fonts[setting[1]]);

            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });


        this.add(Box.createRigidArea(new Dimension(ActionPanel.VERTICAL_GAP, 0)));

        this.add(this.helpMenu);
    }

    // Function to set up the setting dialog
    private int[] setting() {
        SettingDialog setdlg = new SettingDialog(this);
        int[] setting = new int[2];
        if (setdlg.exec()) {
            int colorTargetIndex = setdlg.getMyColor();
            int fontTargetIndex = setdlg.getMyFont();
            setting[0] = colorTargetIndex;
            setting[1] = fontTargetIndex;
            return setting;
        }
        setting[0] = 0;
        setting[1] = 0;
        return setting;
    }

    public JMenu getImportMenu() {
        return importMenu;
    }

    // Function to choose file in your computer
    private void importBills() {
        System.out.println("imported");
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Your Bills", "csv", "xlsx");
        chooser.setFileFilter(filter);
        int ret = chooser.showOpenDialog(this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            loadFile(file);
        }
    }

    //Function to load file after loading file
    public void loadFile(File file) {
        // CONTINUE!!!
        System.out.println("loaded");
    }
}
