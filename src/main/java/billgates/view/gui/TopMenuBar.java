package billgates.view.gui;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
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
    private MainFrame mainFrame;

    public TopMenuBar(MainFrame owner) {
        // Set the layout of the top menubar
        this.setLayout(this.layout);
        this.mainFrame = owner;
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
        this.add(this.settingsMenu);
        this.add(Box.createRigidArea(new Dimension(ActionPanel.VERTICAL_GAP, 0)));
        this.add(this.helpMenu);

        // Add function to import menu
        importMenu.addMenuListener(new MenuAdaptor(){
            @Override
            public void menuSelected(MenuEvent e) {
                importBills();
            }
        });
        this.add(Box.createRigidArea(new Dimension(ActionPanel.VERTICAL_GAP, 0)));

        // Add function to setting menu
        settingsMenu.addMenuListener(new MenuAdaptor() {
            @Override
            public void menuSelected(MenuEvent e) {
                System.out.println("yes");
                int[] setting = setting();

                mainFrame.getBillPanel().changeColor(colors[setting[0]]);
                mainFrame.getActionPanel().changeColor(colors[setting[0]]);

                mainFrame.getActionPanel().changeFont(fonts[setting[1]]);
                mainFrame.getBillPanel().changeFont(fonts[setting[1]]);

            }
        });
    }

    // Function to set up the setting dialog
    private int[] setting() {
//        SettingDialog setdlg = mainFrame.getSettingDialog();
        SettingDialog setdlg = new SettingDialog(mainFrame);
        int[] setting = new int[2];
        if (setdlg.exec()) {
            int colorTargetIndex = setdlg.getMyColor();
            int fontTargetIndex = setdlg.getMyFont();
            SettingDialog.setFontField(fontTargetIndex);
            SettingDialog.setColorField(colorTargetIndex);
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
        //JDialog chooserDialog = chooser.createDialog(mainFrame);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Your Bills", "csv");
        chooser.setFileFilter(filter);
        int ret = chooser.showOpenDialog(mainFrame);
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

    // Create a MenuAdapter to simplify our code
    public abstract class MenuAdaptor implements MenuListener{
        protected MenuAdaptor() {}

        public void menuCanceled(MenuEvent e) {}

        public void menuSelected(MenuEvent e) {}

        public void menuDeselected(MenuEvent e) {}
    }


}