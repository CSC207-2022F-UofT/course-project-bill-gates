package billgates.GUI;

import javax.swing.*;
import java.awt.*;

public class SettingDialog extends JDialog {
    JButton confirmButton = new JButton("Confirm");
    JButton cancelButton = new JButton("Cancel");
    JComboBox<String> colorField = new JComboBox<>();
    JComboBox<String> fontField = new JComboBox<>();
    Color[] colors = new Color[]{new Color(220, 120, 150, 100), Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.PINK, Color.WHITE};
    String[] fonts = new String[]{"Modern No. 20", "Times New Roman", "Helvetica", "Arial", "Impact",
            "Verdana", "Century", "Tahoma", "Copperplate"};
    private int colorIndex = 0;
    private int fontIndex = 0;
    private boolean confirmed = false;

    public SettingDialog(Window owner) {
        super(owner);

        // Set the font
        this.setFont(new Font("Modern No. 20", Font.BOLD, 14));

        // Set the root panel and its layout
        JPanel root = new JPanel();
        this.setContentPane(root);
        root.add(cancelButton);
        cancelButton.setBounds(200, 175, 40, 20);

        JLabel backgroundButton = new JLabel("Background Color");
        root.add(backgroundButton);
        root.add(colorField);
        root.add(confirmButton);
        colorField.addItem("Default");
        colorField.addItem("Red");
        colorField.addItem("Orange");
        colorField.addItem("Yellow");
        colorField.addItem("Green");
        colorField.addItem("Cyan");
        colorField.addItem("Blue");
        colorField.addItem("Pink");
        colorField.addItem("White");

        JLabel fontStyle = new JLabel("Font");
        root.add(fontStyle);
        root.add(fontField);
        fontField.addItem("Default");
        fontField.addItem("Times New Roman");
        fontField.addItem("Helvetica");
        fontField.addItem("Arial");
        fontField.addItem("Impact");
        fontField.addItem("Verdana");
        fontField.addItem("Century");
        fontField.addItem("Tahoma");
        fontField.addItem("Copperplate");


        // Case 1: Click cancelButton
        cancelButton.addActionListener(e -> {
            setVisible(false);
        });

        // Case 2: Choose different color and font
        confirmButton.addActionListener((e ->
        {
            colorIndex = colorField.getSelectedIndex();

            fontIndex = fontField.getSelectedIndex();
            confirmed = true;

            JOptionPane.showMessageDialog(null, "Page has been set!", "All Set", JOptionPane.PLAIN_MESSAGE);
            setVisible(false);
        }));
    }
    public int getMyColor(){
        return colorIndex;
    }

    public int getMyFont(){
        return fontIndex;
    }

    public boolean exec() {
        this.setTitle("Setting");
        this.setModal(true);
        this.setSize(400, 200);
        this.setResizable(false);

        SwingUtil.centerInOwner(this, this.getOwner());

        // Show the dialog
        this.setVisible(true);
        return confirmed;
    }
}
