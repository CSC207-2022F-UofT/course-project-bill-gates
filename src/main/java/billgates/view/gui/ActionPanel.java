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
    public static final int DEFAULT_TEXT_AREA_HEIGHT = DEFAULT_HEIGHT / 3;
    public static final int HORIZONTAL_GAP = 5;
    public static final int VERTICAL_GAP = 10;
    public static final int BORDER_THICKNESS = 3;
    public static final int EMPTY_BORDER_THICKNESS = 7;
    public static final Color DEFAULT_BORDER_TEXT_COLOR = new Color(220, 120, 150);

    private final BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);

    private final SpringLayout signInLayout = new SpringLayout();
    private final JPanel signInPanel = new JPanel(this.signInLayout);
    private final JLabel usernameLabel = new ActionLabel("Username: ");
    private final JTextField usernameField = new JTextField(10);
    private final JLabel passwordLabel = new ActionLabel("Password: ");
    private final JPasswordField passwordField = new JPasswordField(16);

    private final JButton signInButton = new ActionButton("Sign In");
    private final JButton signOutButton = new ActionButton("Sign Out");
    private final JButton backButton = new ActionButton("Back");
    private final JButton addEntryButton = new ActionButton("Add Entry");
    private final JButton deleteEntryButton = new ActionButton("Delete Entry");
    private final JTextArea statisticsTextArea = new JTextArea("Statistics");

    // private Icon backIcon;

    public ActionPanel() {
        this.setLayout(this.layout);
        this.initSignInPanel();
        this.initButtonTextArea();
        this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.initBorder();
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

    private void initButtonTextArea(){
        // Components with gaps
        // signInButton
        this.add(signInButton);
        signInButton.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,VERTICAL_GAP)));
        // Sign in event
        signInButton.addActionListener((e -> signIn()));

        // signOutButton
        this.add(signOutButton);
        signOutButton.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,VERTICAL_GAP)));
        // Sign out event
        signOutButton.addActionListener((e -> signOut()));
        // signOutButton should be disabled at the beginning
        signOutButton.setEnabled(false);

        // backButton
        // this.loadIcon();
        this.add(backButton);
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,VERTICAL_GAP)));

        // addEntryButton
        this.add(addEntryButton);
        addEntryButton.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,VERTICAL_GAP)));

        // deleteEntryButton
        this.add(deleteEntryButton);
        deleteEntryButton.setAlignmentX(CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,VERTICAL_GAP)));

        // statisticsTextArea
        this.add(statisticsTextArea);
        statisticsTextArea.setAlignmentX(CENTER_ALIGNMENT);
        statisticsTextArea.setMaximumSize(new Dimension(DEFAULT_SIGN_IN_PANEL_WIDTH, DEFAULT_TEXT_AREA_HEIGHT));
    }

    private void initBorder() {
        TitledBorder actionBorder = BorderFactory.createTitledBorder("Actions");
        actionBorder.setTitleColor(DEFAULT_BORDER_TEXT_COLOR);
        Border lineBorder = new LineBorder(MainFrame.DEFAULT_BACKGROUND_COLOR, BORDER_THICKNESS, true);
        Border emptyBorder = BorderFactory.createEmptyBorder(EMPTY_BORDER_THICKNESS,
                EMPTY_BORDER_THICKNESS, EMPTY_BORDER_THICKNESS, EMPTY_BORDER_THICKNESS);
        Border outsideBorder = BorderFactory.createCompoundBorder(emptyBorder, lineBorder);
        actionBorder.setBorder(BorderFactory.createCompoundBorder(outsideBorder, emptyBorder));
        this.setBorder(actionBorder);
    }

    private void signIn(){
        // Get the username and password
        System.out.println("Name: " + this.usernameField.getText());
        System.out.println("Password: " + Arrays.toString(this.passwordField.getPassword()));

        // If the user has successfully signed in, disable the signInButton, and enable the signOutButton
        if (checkSignIn()){
            signInButton.setEnabled(false);
            signOutButton.setEnabled(true);
        }
    }

    private boolean checkSignIn(){
        // Will be implemented further
        return true;
    }

    private void signOut(){
        signOutButton.setEnabled(false);
        signInButton.setEnabled(true);
    }

//    private void loadIcon(){
//        try {
////            BufferedImage backImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("back.png")));
////            this.backIcon = new ImageIcon(backImage);
//            this.backIcon = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("back.png")));
//            // this.backIcon = (Icon) ImageIO.read(new File("/resources/back.png"));
//        } catch (Exception e){
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

}
