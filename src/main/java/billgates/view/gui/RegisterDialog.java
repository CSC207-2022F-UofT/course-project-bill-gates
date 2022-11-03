package billgates.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class RegisterDialog extends JDialog {
    JTextField nameInput = new JTextField(17);
    JTextField nickInput = new JTextField(17);
    JPasswordField passwordInput = new JPasswordField(17);
    JPasswordField confirmPasswordInput = new JPasswordField(15);
    JTextField emailInput = new JTextField(17);

    JButton createButton = new JButton("Create Account");
    JButton cancelButton = new JButton("Cancel");

    private boolean accepted = false;

    public RegisterDialog(Window owner){
        super(owner);

        // Set the font
        this.setFont(new Font("Modern No. 20", Font.BOLD, 14));

        // Set the root panel and its layout
        JPanel root = new JPanel();
        this.setContentPane(root);
        root.setLayout(new BorderLayout());

        // Components
        if (true){
            JPanel panel = new JPanel();
            root.add(panel, BorderLayout.CENTER);

            JLabel nameLabel = new JLabel("<HTML><font color=black>Name</font><font color=red>*</font>" +
                    "<font color=black>:</font></html>");
            JLabel nickLabel = new JLabel("Nickname:");
            JLabel passwordLabel = new JLabel("<HTML><font color=black>Password</font><font color=red>*</font>" +
                    "<font color=black>:</font></html>");
            JLabel confirmPasswordLabel = new JLabel("<HTML><font color=black>Confirm password</font>" +
                    "<font color=red>*</font><font color=black>:</font></html>");
            JLabel emailLabel = new JLabel("Email:");
            panel.add(nameLabel);
            panel.add(nameInput);
            panel.add(nickLabel);
            panel.add(nickInput);
            panel.add(passwordLabel);
            panel.add(passwordInput);
            panel.add(confirmPasswordLabel);
            panel.add(confirmPasswordInput);
            panel.add(emailLabel);
            panel.add(emailInput);

            passwordInput.setEchoChar('*');
            confirmPasswordInput.setEchoChar('*');
        }

        if (true){
            JPanel panel = new JPanel();
            root.add(panel, BorderLayout.SOUTH);

            panel.add(cancelButton);
            panel.add(createButton);
        }

        // Case 1: Click cancelButton
        cancelButton.addActionListener(e -> {
            setVisible(false);
        });

        // Case 2: Click createButton
        createButton.addActionListener((e -> {
            if (nameInput.getText().equals("") | passwordInput.getPassword().length == 0 |
                    confirmPasswordInput.getPassword().length == 0){
                JOptionPane.showMessageDialog(null,
                        "Blank field detected ! Please fill in the field with * !", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else if (!matchPassword()) {
                JOptionPane.showMessageDialog(null, "Passwords don't match!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                accepted = true;
                setVisible(false);
            }
        }));
    }

    public String getInput(){
        return String.format("Name: %s\n Nickname: %s\n Password: %s\n Email: ", nameInput.getText(),
                nickInput.getText(), Arrays.toString(passwordInput.getPassword()), emailInput.getText());
    }

    // Execute
    public boolean exec(){
        this.setTitle("Register");
        this.setModal(true);
        this.setSize(300, 220);
        this.setResizable(false);

        SwingUtil.centerInOwner(this, this.getOwner());

        // Show the dialog
        this.setVisible(true);

        return accepted;
    }

    private boolean matchPassword(){
        return Arrays.equals(passwordInput.getPassword(), confirmPasswordInput.getPassword());
    }
}
