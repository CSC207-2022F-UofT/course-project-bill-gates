package billgates.view.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Eva, Charlotte, Scott
 */
public class SettingDialog extends JDialog {
    private static final JComboBox<String> colorField = new JComboBox<>();
    private static final JComboBox<String> fontField = new JComboBox<>();
    private static int colorIndex;
    private static int fontIndex;
    private boolean confirmed = false;
    private static boolean isInitialized = false;

    // Set up setting dialog to change background color and font style
    public SettingDialog(MainFrame owner) {
        super(owner);

        // Set the font
        this.setFont(new FontSettings(14));

        // Set the Main root panel and its layout
        JPanel root = new JPanel();
        this.setTitle("Setting");
        this.setModal(true);
        this.setSize(400, 200);
        this.setResizable(false);
        SwingUtil.centerInOwner(this, owner);
        this.setContentPane(root);

        //Set the sub-action root panel
        JPanel subAction = new JPanel();
        JButton confirmButton = new JButton("Confirm");
        subAction.add(confirmButton);
        JButton cancelButton = new JButton("Cancel");
        subAction.add(cancelButton);

        // Set the color-field and font-field
        JLabel backgroundButton = new JLabel("Background Color");
        JLabel fontStyle = new JLabel("Font");

        if (!isInitialized) {
            colorField.addItem("Default");
            colorField.addItem("Red");
            colorField.addItem("Orange");
            colorField.addItem("Yellow");
            colorField.addItem("Green");
            colorField.addItem("Cyan");
            colorField.addItem("Blue");
            colorField.addItem("Pink");
            colorField.addItem("White");

            fontField.addItem("Default");
            fontField.addItem("Tahoma");
            fontField.addItem("Times New Roman");
            fontField.addItem("Helvetica");
            fontField.addItem("Arial");
            fontField.addItem("Impact");
            fontField.addItem("Verdana");
            fontField.addItem("Century");
            fontField.addItem("Copperplate");

            isInitialized = true;
        }

        //Set the color choose box panel
        JPanel colorChooseBox = new JPanel();
        colorChooseBox.add(backgroundButton);
        colorChooseBox.add(colorField);

        //Set the font choose box panel
        JPanel fontChooseBox = new JPanel();
        fontChooseBox.add(fontStyle);
        fontChooseBox.add(fontField);

        //Add the panels to root panel to set up our layout
        this.add(colorChooseBox, BorderLayout.CENTER);
        this.add(fontChooseBox, BorderLayout.CENTER);
        this.add(subAction, BorderLayout.SOUTH);

        // Case 1: Click cancelButton
        cancelButton.addActionListener(e -> this.setVisible(false));

        // Case 2: Choose different color and font
        confirmButton.addActionListener((e ->
        {
            colorIndex = colorField.getSelectedIndex();
            colorField.setSelectedIndex(colorIndex);

            fontIndex = fontField.getSelectedIndex();
            fontField.setSelectedIndex(fontIndex);

            this.confirmed = true;

            JOptionPane.showMessageDialog(this, "Page has been set!", "All Set",
                    JOptionPane.PLAIN_MESSAGE);
            this.setVisible(false);
        }));
    }

    public static void setColorField(int index) {
        colorField.setSelectedIndex(index);
    }

    public static void setFontField(int index) {
        fontField.setSelectedIndex(index);
    }

    public int getMyColor() {
        return colorIndex;
    }

    public int getMyFont() {
        return fontIndex;
    }

    public boolean exec() {

        // Show the dialog
        this.setVisible(true);
        return confirmed;
    }
}