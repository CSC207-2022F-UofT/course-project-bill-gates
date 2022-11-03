package billgates.view.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class SignInDialog extends JDialog {
        JTextField nameInput = new JTextField(17);
        JPasswordField passwordInput = new JPasswordField(17);

        JButton registerButton = new JButton("Register");
        JButton signOnButton = new JButton("Sign On");

        private boolean accepted = false;

        public SignInDialog(Window owner){
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
                SpringLayout layout = new SpringLayout();
                panel.setLayout(layout);
                root.add(panel, BorderLayout.CENTER);

                JLabel nameLabel = new JLabel("Name:");
                JLabel passwordLabel = new JLabel("Password:");
                panel.add(nameLabel);
                panel.add(nameInput);
                panel.add(passwordLabel);
                panel.add(passwordInput);

                SpringLayout.Constraints nLCons = layout.getConstraints(nameLabel);
                nLCons.setX(Spring.constant(52));
                nLCons.setY(Spring.constant(30));
                nLCons.setWidth(Spring.constant(38));
                nLCons.setHeight(Spring.constant(20));

                SpringLayout.Constraints pLCons = layout.getConstraints(passwordLabel);
                pLCons.setWidth(Spring.constant(60));
                pLCons.setHeight(Spring.constant(20));
                pLCons.setX(Spring.constant(30));
                pLCons.setY(Spring.sum(Spring.constant(50), nLCons.getHeight()));

                SpringLayout.Constraints nICons = layout.getConstraints(nameInput);
                nICons.setX(Spring.sum(Spring.sum(pLCons.getX(), pLCons.getWidth()), Spring.constant(5)));
                nICons.setY(nLCons.getY());

                SpringLayout.Constraints pICons = layout.getConstraints(passwordInput);
                pICons.setX(nICons.getX());
                pICons.setY(pLCons.getY());

                passwordInput.setEchoChar('*');
            }

            if (true){
                JPanel panel = new JPanel();
                root.add(panel, BorderLayout.SOUTH);

                panel.add(registerButton);
                panel.add(signOnButton);
            }

            // Case 1: Click registerButton
            registerButton.addActionListener(e -> {
                setVisible(false);
                RegisterDialog dlg = new RegisterDialog(this);
                if (dlg.exec()){
                    JOptionPane.showMessageDialog(null,
                            "Account has been successfully created !", "Congratulation",
                            JOptionPane.PLAIN_MESSAGE);
                    System.out.println(dlg.getInput());
                }
            });

            // Case 2: Click signOnButton
            signOnButton.addActionListener((e -> {
                accepted = true;
                setVisible(false);
            }));
        }

        public String getInput(){
            return String.format("Name: %s\n Password: %s", nameInput.getText(),
                    Arrays.toString(passwordInput.getPassword()));
        }

        // Execute
        public boolean exec(){
            this.setTitle("Sign In");
            this.setModal(true);
            this.setSize(300, 200);
            this.setResizable(false);

            SwingUtil.centerInOwner(this, this.getOwner());

            // Show the dialog
            this.setVisible(true);

            return accepted;
        }
}
