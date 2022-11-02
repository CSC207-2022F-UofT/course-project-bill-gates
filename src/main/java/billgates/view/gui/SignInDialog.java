package billgates.view.gui;

import javax.swing.*;
import java.awt.*;

public class SignInDialog extends JDialog {
        JTextField nameInput = new JTextField(15);
    JTextField passwordInput = new JTextField(15);

        JButton registerButton = new JButton("Register");
        JButton signOnButton = new JButton("Sign On");

        private boolean accepted = false;

        public SignInDialog(Window owner){
            super(owner);

            JPanel root = new JPanel();
            this.setContentPane(root);
            root.setLayout(new BorderLayout());

            if (true){
                JPanel panel = new JPanel();
                root.add(panel, BorderLayout.CENTER);

                JLabel nameLabel = new JLabel("Name:");
                JLabel passwordLabel = new JLabel("Password:");
                panel.add(nameLabel);
                panel.add(nameInput);
                panel.add(passwordLabel);
                panel.add(passwordInput);
            }

            if (true){
                JPanel panel = new JPanel();
                root.add(panel, BorderLayout.SOUTH);

                panel.add(registerButton);
                panel.add(signOnButton);
            }

            // Case 1: Click registerButton
            registerButton.addActionListener(e -> {
                accepted = true;
                setVisible(false);
            });

            // Case 2: Click signOnButton
            signOnButton.addActionListener((e -> {
                accepted = false;
                setVisible(false);
            }));
        }

        public String getInput(){
            return nameInput.getText() + passwordInput.getText();
        }

        // Execute
        public boolean exec(){
            this.setTitle("Sign In");
            this.setModal(true);
            this.setSize(250, 150);

            SwingUtil.centerInOwner(this, this.getOwner());

            // Show the dialog
            this.setVisible(true);

            return accepted;
        }

        // Testing the dialog
        public static void main(String[] args) {
            JFrame frame = new JFrame();
            frame.setSize(600, 400);
            frame.setVisible(true);

            SignInDialog dialog = new SignInDialog(frame);
            dialog.exec();
        }
}
