package billgates.view.gui;

import billgates.use_cases.insert_entry.InsertEntryRequestModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte, Eva, Ruby, Brandon Fu, Scott
 */
public class TopMenuBar extends JMenuBar {

    public static final int DEFAULT_HEIGHT = 23;
    private final static Color[] COLORS = new Color[]{new Color(240, 140, 170), Color.RED,
            Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.PINK, Color.WHITE
    };
    private final static String[] FONTS = new String[]{"Microsoft YaHei UI", "Tahoma", "Times New Roman",
            "Helvetica", "Arial", "Impact", "Verdana", "Century", "Copperplate"};
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
                = KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.SHIFT_DOWN_MASK +
                KeyEvent.CTRL_DOWN_MASK);
        importMenuItem.setAccelerator(keyStrokeToImport);
        fileMenu.add(importMenuItem);

        // Add function to import menu
        importMenuItem.addActionListener(e -> importBills());
        this.add(Box.createRigidArea(new Dimension(ActionPanel.VERTICAL_GAP, 0)));

        // Add MenuItem change color and font in setting
        JMenuItem appearanceMenuItem = new JMenuItem("Appearance");
        KeyStroke keyStrokeToSet = KeyStroke.getKeyStroke(KeyEvent.VK_A,
                KeyEvent.SHIFT_DOWN_MASK + KeyEvent.CTRL_DOWN_MASK);
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
        KeyStroke keyStrokeToUse = KeyStroke.getKeyStroke(KeyEvent.VK_H,
                KeyEvent.SHIFT_DOWN_MASK + KeyEvent.CTRL_DOWN_MASK);
        functionMenuItem.setAccelerator(keyStrokeToUse);
        helpMenu.add(functionMenuItem);

        // Add function to help menu
        functionMenuItem.addActionListener( e -> showFunctions());

        // Add MenuItem shortcut key in help
        JMenuItem shortcutMenuItem = new JMenuItem("Shortcut key");
        KeyStroke keyStrokeShortcut = KeyStroke.getKeyStroke(KeyEvent.VK_S,
                KeyEvent.SHIFT_DOWN_MASK + KeyEvent.CTRL_DOWN_MASK);
        shortcutMenuItem.setAccelerator(keyStrokeShortcut);
        helpMenu.add(shortcutMenuItem);

        // Add function to help menu
        shortcutMenuItem.addActionListener( e -> shortcut());
    }

    // Function to set up the setting dialog
    private int[] setting() {
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
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Your Bills", "csv");
        chooser.setFileFilter(filter);
        int ret = chooser.showOpenDialog(mainFrame);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            // Connect with the Insert Entry Use case to pass the file
            List<List<String>> csvFile = convertFile(file);
            for (List<String> entry: csvFile) {
                ZonedDateTime date = LocalDate.parse(entry.get(0), DateTimeFormatter.ISO_DATE)
                        .atStartOfDay(ZoneOffset.UTC);
                double value = Double.parseDouble(entry.get(1));
                InsertEntryRequestModel model1 = new InsertEntryRequestModel(date, value,
                        entry.get(2), entry.get(3), entry.get(4), entry.get(5),entry.get(6));
                this.mainFrame.getInsertEntryController().insert(model1);
            }

            // After adding these bills, update the current bill
            SwingUtilities.invokeLater(() -> this.mainFrame.getBillUpdateController().update(-1));
        }
    }

    // Function to convert your file after loading file
    public List<List<String>> convertFile(File file) {
        List<List<String>> csvFile = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                csvFile.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return csvFile;
    }

    // Function to show a description of the function of this app
    // This warning shouldn't be resolved because these are just bunch of texts.
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