package billgates.view.gui;

import billgates.interface_adapters.UserJoinUpdatable;
import billgates.use_cases.insert_entry.InsertEntryRequestModel;
import billgates.use_cases.user_join.UserJoinViewModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Objects;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte, Scott, Ellen, Eva, Yunshan
 */
public class ActionPanel extends JPanel implements UserJoinUpdatable {

    public static final int DEFAULT_WIDTH = (int) (MainFrame.DEFAULT_WIDTH / 3.5);
    public static final int DEFAULT_HEIGHT = MainFrame.DEFAULT_HEIGHT;
    public static final int DEFAULT_SIGN_IN_PANEL_WIDTH = DEFAULT_WIDTH / 10 * 9;
    public static final int DEFAULT_SIGN_IN_PANEL_HEIGHT = DEFAULT_HEIGHT / 7;
    public static final int HORIZONTAL_GAP = 5;
    public static final int VERTICAL_GAP = 10;

    private final ImageIcon backIcon = new ImageIcon(Objects.requireNonNull
            (this.getClass().getResource("/back.png")));
    private final SpringLayout signInLayout = new SpringLayout();
    private final JPanel signInPanel = new JPanel(this.signInLayout);
    private final JLabel usernameLabel = new ActionLabel("Username: ");
    private final JTextField usernameField = new JTextField(20);
    private final JLabel passwordLabel = new ActionLabel("Password: ");
    private final JPasswordField passwordField = new JPasswordField(20);

    private final JButton signInButton = new ActionButton("Sign In");
    private final JButton signOutButton = new ActionButton("Sign Out");
    private final JButton backButton = new ActionButton(" Back", this.backIcon);
    private final JButton addEntryButton = new ActionButton("Add Entry");
    private final JButton deleteEntryButton = new ActionButton("Delete Entry");
    private final JTextArea statisticsTextArea = new ActionTextArea("");

    private final MainFrame mainFrame;

    public ActionPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        this.setLayout(layout);
        this.initSignInPanel();
        this.initButtonTextArea();
        this.initBorder();
        this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    private void initSignInPanel() {
        this.add(this.signInPanel);
        // Set the size of signInPanel
        this.signInPanel.setMaximumSize(
                new Dimension(DEFAULT_SIGN_IN_PANEL_WIDTH, DEFAULT_SIGN_IN_PANEL_HEIGHT));

        // Restrict the input of usernameField (user cannot input whitespace for their username)
        this.usernameField.setDocument(new RegexDocument("\\S*"));

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
        this.backButton.addActionListener((e -> this.backFromSplit()));
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
        this.deleteEntryButton.addActionListener((e -> this.deleteEntry()));
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
        // Get the username and password from the user
        String userName = this.usernameField.getText();
        String userPassword = String.valueOf(this.passwordField.getPassword());

        // If the username and password are legal, we then call the controller of UserJoinUseCase
        if (this.checkUsername() && this.checkPassword()) {
            // Call the UserJoinController
            SwingUtilities.invokeLater(() ->
                    this.mainFrame.getUserJoinController().userJoin(userName, userPassword));
        }
    }

    private boolean checkUsername() {
        int usernameLength = this.usernameField.getText().length();
        if (usernameLength == 0) {
            JOptionPane.showMessageDialog(this.mainFrame, "Username cannot be empty!");
            return false;
        } else if (usernameLength > 10) {
            JOptionPane.showMessageDialog(this.mainFrame, "Username exceeds the maximum length!");
            return false;
        }
        return true;
    }

    private boolean checkPassword() {
        int passwordLength = String.valueOf(this.passwordField.getPassword()).length();
        if (passwordLength == 0) {
            JOptionPane.showMessageDialog(this.mainFrame, "Password cannot be empty!");
            return false;
        } else if (passwordLength > 16) {
            JOptionPane.showMessageDialog(this.mainFrame, "Password exceeds the maximum length!");
            return false;
        }
        return true;
    }

    private void signOut() {
        this.mainFrame.setSignedIn(false);
        // init button status
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
        TopMenuBar topMenuBar = (TopMenuBar) this.mainFrame.getJMenuBar();
        topMenuBar.getFileMenu().setEnabled(false);

        // Disable the billTable
        BillTable billTable = this.mainFrame.getBillPanel().getBillTable();
        billTable.setEnabled(false);
        billTable.setVisible(false);

        // Clear the statisticsTextArea
        this.statisticsTextArea.setText("");

        this.backFromSplit();
    }

    private void backFromSplit() {
        // set the current bill to the main bill of the user
        SwingUtilities.invokeLater(() -> this.mainFrame.getBillUpdateController().update(-2));
    }

    private void addEntry() {
        AddEntryDialog addEntryDialog = new AddEntryDialog(this.mainFrame);
        InsertEntryRequestModel model = addEntryDialog.exec();

        if (model != null) {
            this.mainFrame.getInsertEntryController().insert(model);
            // after adding the entry, update the current bill
            SwingUtilities.invokeLater(() -> this.mainFrame.getBillUpdateController().update(-1));
        }
    }

    private void deleteEntry() {
        BillTable table = this.mainFrame.getBillPanel().getBillTable();
        int[] selectedRows = table.getSelectedRows();
        for (int i : selectedRows) {
            int entryId = (int) table.getModel().getValueAt(i, 0);
            this.mainFrame.getDeleteEntryController().delete(entryId);
        }
        SwingUtilities.invokeLater(() -> this.mainFrame.getBillUpdateController().update(-1));
    }

    public JButton getDeleteEntryButton() {
        return this.deleteEntryButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    @Override
    public void view(UserJoinViewModel viewModel) {
        // If the user join successfully,
        if (viewModel.isJoined()) {
            // disable the signInButton, and enable the signOutButton and addEntryButton
            this.signInButton.setEnabled(false);
            this.signOutButton.setEnabled(true);
            this.addEntryButton.setEnabled(true);

            // the usernameField and passwordField shouldn't be editable
            this.usernameField.setEditable(false);
            this.passwordField.setEditable(false);

            // enable importMenu
            TopMenuBar topMenuBar = (TopMenuBar) this.mainFrame.getJMenuBar();
            topMenuBar.getFileMenu().setEnabled(true);

            SwingUtilities.invokeLater(() -> this.mainFrame.getBillUpdateController().update(-2));

            // enable billTable
            BillTable billTable = this.mainFrame.getBillPanel().getBillTable();
            billTable.setVisible(true);
            billTable.setEnabled(true);

            this.mainFrame.setSignedIn(true);
        }

        // Show a message dialog with whatever the text from the viewModel
        JOptionPane.showMessageDialog(this.mainFrame, viewModel.getReasonRejected());
    }

    // Change color in ActionPanel and statistic text area
    public void changeColor(Color c) {
        this.setBorder(new CustomTitleBorder("Action", c));
        this.statisticsTextArea.setBorder(new CustomTitleBorder("Statistics", c));
        this.statisticsTextArea.setForeground(c);
        ActionButton.allButton.forEach(b -> b.setBackground(c));
    }

    // Change font in ActionPanel and statistic text area
    public void changeFont(String f) {
        Font newButtonFont = new FontSettings(f, ActionButton.DEFAULT_FONT_SIZE);
        for (ActionButton ab : ActionButton.allButton) {
            ab.setFont(newButtonFont);
        }
        Font newLabelFont = new FontSettings(f, ActionLabel.DEFAULT_FONT_SIZE);
        for (ActionLabel al : ActionLabel.allLabel) {
            al.setFont(newLabelFont);
        }
        Font newTextFont = new FontSettings(f, ActionTextArea.DEFAULT_FONT_SIZE);
        this.statisticsTextArea.setFont(newTextFont);
    }

    public JTextArea getStatisticsTextArea() {
        return this.statisticsTextArea;
    }
}