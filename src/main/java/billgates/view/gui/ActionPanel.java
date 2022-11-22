package billgates.view.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Objects;

public class ActionPanel extends JPanel {

    public static final int DEFAULT_WIDTH = (int) (MainFrame.DEFAULT_WIDTH / 3.5);
    public static final int DEFAULT_HEIGHT = MainFrame.DEFAULT_HEIGHT;
    public static final int DEFAULT_SIGN_IN_PANEL_WIDTH = DEFAULT_WIDTH / 10 * 9;
    public static final int DEFAULT_SIGN_IN_PANEL_HEIGHT = DEFAULT_HEIGHT / 7;
    public static final int HORIZONTAL_GAP = 5;
    public static final int VERTICAL_GAP = 10;

    private final BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);

    private final ImageIcon backIcon = new ImageIcon(Objects.requireNonNull
            (this.getClass().getResource("/back.png")));
    private final SpringLayout signInLayout = new SpringLayout();
    private final JPanel signInPanel = new JPanel(this.signInLayout);
    private final JLabel usernameLabel = new ActionLabel("Username: ");
    private final JTextField usernameField = new JTextField(10);
    private final JLabel passwordLabel = new ActionLabel("Password: ");
    private final JPasswordField passwordField = new JPasswordField(16);

    private final JButton signInButton = new ActionButton("Sign In");
    private final JButton signOutButton = new ActionButton("Sign Out");
    private final JButton backButton = new ActionButton(" Back", this.backIcon);
    private final JButton addEntryButton = new ActionButton("Add Entry");
    private final JButton deleteEntryButton = new ActionButton("Delete Entry");
    private final JTextArea statisticsTextArea = new ActionTextArea("Statistics");

    public ActionPanel() {
        this.setLayout(this.layout);
        this.initSignInPanel();
        this.initButtonTextArea();
        this.initBorder();
        this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    private void initSignInPanel() {
        this.add(this.signInPanel);
        // Set the size of signInPanel
        this.signInPanel.setMaximumSize(new Dimension(DEFAULT_SIGN_IN_PANEL_WIDTH, DEFAULT_SIGN_IN_PANEL_HEIGHT));

        // add and layout components
        // username label
        this.signInPanel.add(this.usernameLabel);
        this.signInLayout.putConstraint(SpringLayout.WEST, this.usernameLabel, HORIZONTAL_GAP,
                SpringLayout.WEST, this.signInPanel);
        this.signInLayout.putConstraint(SpringLayout.NORTH, this.usernameLabel, VERTICAL_GAP,
                SpringLayout.NORTH, this.signInPanel);

        // username field
        this.signInPanel.add(this.usernameField);
        this.signInLayout.putConstraint(SpringLayout.WEST, this.usernameField, HORIZONTAL_GAP,
                SpringLayout.EAST, this.usernameLabel);
        this.signInLayout.putConstraint(SpringLayout.NORTH, this.usernameField, VERTICAL_GAP,
                SpringLayout.NORTH, this.signInPanel);

        // password label
        this.signInPanel.add(this.passwordLabel);
        this.signInLayout.putConstraint(SpringLayout.WEST, this.passwordLabel, HORIZONTAL_GAP,
                SpringLayout.WEST, this.signInPanel);
        this.signInLayout.putConstraint(SpringLayout.NORTH, this.passwordLabel, VERTICAL_GAP,
                SpringLayout.SOUTH, this.usernameLabel);

        // password field
        this.signInPanel.add(this.passwordField);
        this.signInLayout.putConstraint(SpringLayout.WEST, this.passwordField, 0,
                SpringLayout.WEST, this.usernameField);
        this.signInLayout.putConstraint(SpringLayout.NORTH, this.passwordField, VERTICAL_GAP,
                SpringLayout.SOUTH, this.usernameField);
    }

    private void initButtonTextArea() {
        // Components with gaps
        // signInButton
        this.add(this.signInButton);
        this.signInButton.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, VERTICAL_GAP)));
        // Sign in event
        this.signInButton.addActionListener((e -> this.signIn()));

        // signOutButton
        this.add(this.signOutButton);
        this.signOutButton.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, VERTICAL_GAP)));
        // Sign out event
        this.signOutButton.addActionListener((e -> this.signOut()));
        // signOutButton should be disabled at the beginning
        this.signOutButton.setEnabled(false);

        // backButton
        this.add(this.backButton);
        this.backButton.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, VERTICAL_GAP)));
        // Back from splitting bills event
        this.addEntryButton.addActionListener((e -> this.backFromSplit()));
        // backButton should be disabled at the beginning
        this.backButton.setEnabled(false);

        // addEntryButton
        this.add(this.addEntryButton);
        this.addEntryButton.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, VERTICAL_GAP)));
        // Add entry event
        this.addEntryButton.addActionListener((e -> this.addEntry()));
        // addEntryButton should be disabled at the beginning
        this.addEntryButton.setEnabled(false);

        // deleteEntryButton
        this.add(this.deleteEntryButton);
        this.deleteEntryButton.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, VERTICAL_GAP)));
        // Delete entry event
        this.addEntryButton.addActionListener((e -> this.deleteEntry()));
        // deleteEntryButton should be disabled at the beginning
        this.deleteEntryButton.setEnabled(false);

        // statisticsTextArea
        this.add(this.statisticsTextArea);
        this.statisticsTextArea.setAlignmentX(CENTER_ALIGNMENT);
    }

    private void initBorder() {
        TitledBorder actionBorder = new CustomTitleBorder("Actions");
        this.setBorder(actionBorder);
    }

    private void signIn() {
        // Get the username and password from user
        String userName = this.usernameField.getText();
        String userPassword = Arrays.toString(this.passwordField.getPassword());
        System.out.println("Name: " + userName);
        System.out.println("Password: " + userPassword);

        // TODO: Call the controller of UserJoinUseCase

        // If the user has successfully signed in,
        if (this.checkUsername() & this.checkPassword()) {
            // disable the signInButton, and enable the signOutButton and addEntryButton
            this.signInButton.setEnabled(false);
            this.signOutButton.setEnabled(true);
            this.addEntryButton.setEnabled(true);

            // the usernameField and passwordField shouldn't be editable
            this.usernameField.setEditable(false);
            this.passwordField.setEditable(false);

            // enable importMenu
            TopMenuBar tmb = (TopMenuBar) this.getRootPane().getJMenuBar();
            tmb.getImportMenu().setEnabled(true);

            // enable billTable
            MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
            BillTable bt = (BillTable) mf.getBillPanel().getBillTable();
            bt.setVisible(true);
            bt.setEnabled(true);
        }
    }

    private boolean checkUsername() {
        // Will be implemented further
        return true;
    }

    private boolean checkPassword() {
        // Will be implemented further
        return true;
    }

    private void signOut() {
        // Enable the signInButton, and disable the signOutButton and , addEntryButton, and deleteEntryButton
        this.signInButton.setEnabled(true);
        this.signOutButton.setEnabled(false);
        this.addEntryButton.setEnabled(false);
        this.deleteEntryButton.setEnabled(false);

        // The usernameField and passwordField should be editable after signing out
        this.usernameField.setEditable(true);
        this.passwordField.setEditable(true);

        // Clear the usernameField and passwordField
        this.usernameField.setText("");
        this.passwordField.setText("");

        // Disable the importMenu
        TopMenuBar tmb = (TopMenuBar) this.getRootPane().getJMenuBar();
        tmb.getImportMenu().setEnabled(false);

        // Disable the billTable
        MainFrame mf = (MainFrame) SwingUtilities.getWindowAncestor(this);
        BillTable bt = (BillTable) mf.getBillPanel().getBillTable();
        bt.setEnabled(false);
        bt.setVisible(false);

        // TODO: Call the controller of BillUpdateUseCase
    }

    private void backFromSplit() {
        // TODO: Call the controller of BillUpdateUseCase
    }

    private void addEntry() {
        // TODO: Call the controller of InsertEntryUseCase
    }

    private void deleteEntry() {
        // TODO: Call the controller of DeleteEntryUseCase
    }

    public JButton getDeleteEntryButton() {
        return deleteEntryButton;
    }

    // Change color in ActionPanel and statistic text area
    public void changeColor(Color c){
        this.setBorder(new CustomTitleBorder("Action", c));
        this.statisticsTextArea.setBorder(new CustomTitleBorder("Statistics", c));
        this.statisticsTextArea.setForeground(c);
    }

    // Change font in ActionPanel and statistic text area
    public void changeFont(String f){
        Font newButtonFont = new FontSettings(f, ActionButton.DEFAULT_FONT_SIZE);
        for(ActionButton ab: ActionButton.allButton){
            ab.setFont(newButtonFont);
        }
        Font newLabelFont = new FontSettings(f, ActionLabel.DEFAULT_FONT_SIZE);
        for(ActionLabel al: ActionLabel.allLabel){
            al.setFont(newLabelFont);
        }
        Font newTextFont = new FontSettings(f, ActionTextArea.DEFAULT_FONT_SIZE);
        this.statisticsTextArea.setFont(newTextFont);
    }
}