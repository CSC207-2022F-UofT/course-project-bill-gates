package billgates.view.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte, Eva, Ruby, Brandon Fu
 */
public class TopMenuBar extends JMenuBar {

    public static final int DEFAULT_HEIGHT = 23;
    private final static Color[] COLORS = new Color[]{new Color(240, 140, 170), Color.RED,
            Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.PINK, Color.WHITE
    };
    private final static String[] FONTS = new String[]{"Modern No. 20", "Times New Roman",
            "Helvetica", "Arial", "Impact", "Verdana", "Century", "Tahoma", "Copperplate"};
    private final JMenu fileMenu = new GeneralMenu("File");
    private final JMenu settingsMenu = new GeneralMenu("Settings");
    private final JMenu helpMenu = new GeneralMenu("Help");
    private final MainFrame mainFrame;

    public TopMenuBar(MainFrame owner) {
        // Set the layout of the top menubar
        BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(layout);
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
        this.add(Box.createRigidArea(new Dimension(ActionPanel.VERTICAL_GAP, 0)));

        // Add MenuItem import in File
        JMenuItem importMenuItem = new JMenuItem("Import File");
        KeyStroke keyStrokeToImport
                = KeyStroke.getKeyStroke(KeyEvent.VK_I + KeyEvent.VK_CONTROL, KeyEvent.SHIFT_DOWN_MASK);
        importMenuItem.setAccelerator(keyStrokeToImport);
        fileMenu.add(importMenuItem);

        // Add function to import menu
        importMenuItem.addActionListener(e -> importBills());
        this.add(Box.createRigidArea(new Dimension(ActionPanel.VERTICAL_GAP, 0)));

        // Add MenuItem change color and font in setting
        JMenuItem appearanceMenuItem = new JMenuItem("Appearance");
        KeyStroke keyStrokeToSet
                = KeyStroke.getKeyStroke(KeyEvent.VK_A + KeyEvent.VK_CONTROL, KeyEvent.SHIFT_DOWN_MASK);
        appearanceMenuItem.setAccelerator(keyStrokeToSet);
        settingsMenu.add(appearanceMenuItem);

        // Add function to setting menu
        appearanceMenuItem.addActionListener(e -> {
            int[] setting = setting();

            mainFrame.getBillPanel().changeColor(COLORS[setting[0]]);
            mainFrame.getActionPanel().changeColor(COLORS[setting[0]]);

            mainFrame.getActionPanel().changeFont(FONTS[setting[1]]);
            mainFrame.getBillPanel().changeFont(FONTS[setting[1]]);

        });

        // Add MenuItem functions in help
        JMenuItem functionMenuItem = new JMenuItem("How to use");
        KeyStroke keyStrokeToUse
                = KeyStroke.getKeyStroke(KeyEvent.VK_H + KeyEvent.VK_CONTROL, KeyEvent.SHIFT_DOWN_MASK);
        functionMenuItem.setAccelerator(keyStrokeToUse);
        helpMenu.add(functionMenuItem);

        // Add function to help menu
        functionMenuItem.addActionListener( e -> {
            showFunctions();
        });

        // Add MenuItem shortcut key in help
        JMenuItem shortcutMenuItem = new JMenuItem("Shortcut key");
        KeyStroke keyStrokeShortcut
                = KeyStroke.getKeyStroke(KeyEvent.VK_S + KeyEvent.VK_CONTROL, KeyEvent.SHIFT_DOWN_MASK);
        shortcutMenuItem.setAccelerator(keyStrokeShortcut);
        helpMenu.add(shortcutMenuItem);

        // Add function to help menu
        shortcutMenuItem.addActionListener( e -> {
            shortcut();
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

    // Function to load file after loading file
    public void loadFile(File file) {
        // CONTINUE!!!
        System.out.println("loaded");
    }

    // Function to show a description of the function of this app
    private void showFunctions() {
        String description = """
                <html><h3>Username, Password, and Sign In:</h3>
                You will need to enter a username and password to sign in. If you have not signed in with this username
                before, you will be automatically registered and signed in when you click the "Sign in" button. If you have
                signed in with this username before and entered the correct password, you will be successfully signed in
                when you click the "Sign in" button.
                <html><h3>Sign Out:</h3>
                When you click the "Sign Out" button, you will be signed out from the account you just signed in to.
                <html><h3>Back:</h3>
                Return from the splitter's bill to the main bill.
                <html><h3>Add entry:</h3>
                Add a new entry to the bill. You need to enter the information for this new entry from the pop-up window.
                <html><h3>Delete entry:</h3>
                Delete the selected entry from the bill.
                <html><h3>MenuBar -> File -> Import File:</h3>
                Select a csv file and add the information in it to the bill as new entries.
                <html><h3>MenuBar -> Settings -> Appearance:</h3>
                Change the background color or font.
                <html><h3>MenuBar -> Help -> How to use:</h3>
                Some description of how to use Bill Gates.
                <html><h3>MenuBar -> Help -> Shortcut key:</h3>
                Some descriptions of how to use the shortcut keys.
                """;
        JOptionPane.showMessageDialog(null, description,
                "How to use the Bill Gates", JOptionPane.PLAIN_MESSAGE);
    }

    // Function to show that how to use shortcut key
    private void shortcut() {
        String shortcut = """
                Shift + Control + I: MenuBar -> File -> Import File
                Shift + Control + A: MenuBar -> Settings -> Appearance
                Shift + Control + H: MenuBar -> Help -> How to use
                Shift + Control + S: MenuBar -> Help -> Shortcut key
                """;
        JOptionPane.showMessageDialog(null, shortcut,
                "Shortcut keys", JOptionPane.PLAIN_MESSAGE);
    }
}