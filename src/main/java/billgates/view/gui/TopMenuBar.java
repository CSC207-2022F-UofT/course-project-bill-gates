package billgates.view.gui;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class TopMenuBar extends JMenuBar {

    public static final int DEFAULT_HEIGHT = 23;
    private final BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
    private final JMenu fileMenu = new GeneralMenu("File");
    private final JMenu settingsMenu = new GeneralMenu("Settings");
    private final JMenu helpMenu = new GeneralMenu("Help");
    private final static Color[] COLORS = new Color[]{new Color(240, 140, 170), Color.RED, Color.ORANGE,
            Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.PINK, Color.WHITE};
    private final static String[] FONTS = new String[]{"Modern No. 20", "Times New Roman", "Helvetica", "Arial", "Impact",
            "Verdana", "Century", "Tahoma", "Copperplate"};
    private final MainFrame mainFrame;

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

        this.add(this.fileMenu);
        this.add(Box.createRigidArea(new Dimension(ActionPanel.VERTICAL_GAP, 0)));
        this.add(this.settingsMenu);
        this.add(Box.createRigidArea(new Dimension(ActionPanel.VERTICAL_GAP, 0)));
        this.add(this.helpMenu);

        // Add MenuItem import in File
        JMenuItem importMenuItem = new JMenuItem("Import File");
        fileMenu.add(importMenuItem);

        // Add function to import menu
        importMenuItem.addActionListener( e -> {
            importBills();
        });
        this.add(Box.createRigidArea(new Dimension(ActionPanel.VERTICAL_GAP, 0)));

        // Add MenuItem change color and font in setting
        JMenuItem appearanceMenuItem = new JMenuItem("Appearance");
        settingsMenu.add(appearanceMenuItem);

        // Add function to setting menu
        appearanceMenuItem.addActionListener( e -> {
            System.out.println("yes");
            int[] setting = setting();

            mainFrame.getBillPanel().changeColor(COLORS[setting[0]]);
            mainFrame.getActionPanel().changeColor(COLORS[setting[0]]);

            mainFrame.getActionPanel().changeFont(FONTS[setting[1]]);
            mainFrame.getBillPanel().changeFont(FONTS[setting[1]]);

        });
    }

    // Function to set up the setting dialog
    private int[] setting() {
//        SettingDialog setdlg = new SettingDialog(mainFrame);
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
        setting[0] = setdlg.getMyColor();
        setting[1] = setdlg.getMyFont();
        return setting;
    }

    public JMenuItem getFileMenu() {
        return fileMenu;
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
}