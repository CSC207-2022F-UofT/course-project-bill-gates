package billgates.view.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Frame extends JFrame {
    private int width = 1000;
    private int height = (int) (width * 0.618);
    JList<JTextField> listBox = new JList<>();
    DefaultListModel<JTextField> model = new DefaultListModel<>();
    public Frame(String title){
        // Set the title
        super(title);

        // Set panels
        JPanel outPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel billPanel = new JPanel();
        JPanel topRightPanel = new JPanel();
        JPanel topLeftPanel = new JPanel();
        JPanel entryPanel = new JPanel();
        JScrollPane scrollPanel = new JScrollPane(listBox);

        this.setContentPane(outPanel);

        // Set layout
        outPanel.setLayout(new BorderLayout());
        leftPanel.setLayout(new FlowLayout(1, 30, 30));
        rightPanel.setLayout(new BorderLayout());
        topPanel.setLayout(new BorderLayout(10, 10));
        billPanel.setLayout(new BorderLayout());
        topRightPanel.setLayout(new FlowLayout(2, 5, 5));
        SpringLayout entryLayout = new SpringLayout();
        entryPanel.setLayout(entryLayout);
        outPanel.add(leftPanel, BorderLayout.WEST);
        outPanel.add(rightPanel, BorderLayout.EAST);
        rightPanel.add(topPanel, BorderLayout.NORTH);
        rightPanel.add(billPanel, BorderLayout.SOUTH);
        topPanel.add(topLeftPanel, BorderLayout.WEST);
        topPanel.add(topRightPanel, BorderLayout.EAST);
        billPanel.add(entryPanel, BorderLayout.NORTH);
        billPanel.add(scrollPanel, BorderLayout.SOUTH);

        // Set border
        Border emptyBorderLeft = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        Border emptyBorderRight = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border lineBorder = new LineBorder(Color.BLACK, 3);
        Border matteBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK);

        // Set preferred size and border for each panel
        outPanel.setPreferredSize(new Dimension(width, height));
        leftPanel.setPreferredSize(new Dimension(width / 5, height));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorderLeft));
        rightPanel.setPreferredSize(new Dimension((int) (width - leftPanel.getPreferredSize().getWidth()) - 18, height));
        rightPanel.setBorder(lineBorder);
        topPanel.setPreferredSize(new Dimension(rightPanel.getPreferredSize().width, height / 7));
        topPanel.setBorder(matteBorder);
        topLeftPanel.setPreferredSize(new Dimension((int) (topPanel.getPreferredSize().width * 0.85), topPanel.getPreferredSize().height));
        topLeftPanel.setBorder(emptyBorderRight);
        topRightPanel.setPreferredSize(new Dimension((int) (topPanel.getPreferredSize().width * 0.144), topPanel.getPreferredSize().height));
        topRightPanel.setBorder(emptyBorderRight);
        billPanel.setPreferredSize(new Dimension(rightPanel.getPreferredSize().width, rightPanel.getPreferredSize().height - topPanel.getPreferredSize().height));
        // billPanel.setBorder(emptyBorderRight);
        entryPanel.setPreferredSize(new Dimension(billPanel.getPreferredSize().width, billPanel.getPreferredSize().height / 6));
        entryPanel.setBorder(BorderFactory.createCompoundBorder(matteBorder, emptyBorderLeft));
        scrollPanel.setPreferredSize(new Dimension(billPanel.getPreferredSize().width, billPanel.getPreferredSize().height - entryPanel.getPreferredSize().height));

        // Set background color
        leftPanel.setBackground(new Color(220, 120, 150, 100));
        topLeftPanel.setBackground(new Color(220, 120, 150, 100));
        topRightPanel.setBackground(new Color(220, 120, 150, 100));

        // Labels and buttons in leftPanel
        JLabel userLabel = new JLabel("TODO");
        JButton importButton = new GeneralButton("Import");
        JButton analysisButton = new GeneralButton("Analysis");
        JButton setButton = new GeneralButton("Settings");
        JButton helpButton = new GeneralButton("Help");

        leftPanel.add(userLabel);
        leftPanel.add(importButton);
        leftPanel.add(analysisButton);
        leftPanel.add(setButton);
        leftPanel.add(helpButton);

        userLabel.setPreferredSize(new Dimension(100, 100));

        // Buttons in topLeftPanel
        JButton addBill = new GeneralButton("ADD A NEW BILL !", 630, 60, new Color(220, 120, 150), 20);
        JButton plusButton = new GeneralButton("+", 70, 70, new Color(220, 120, 150), 40);

        addBill.add(plusButton);
        topLeftPanel.add(addBill, BorderLayout.CENTER);

        // Buttons in topRightPanel
        JButton signInButton = new GeneralButton("Sign In", 85, 25, new Color(220, 120, 150), 12);
        JButton signOutButton = new GeneralButton("Sign Out",85, 25, new Color(220, 120, 150), 12);

        topRightPanel.add(signInButton);
        topRightPanel.add(signOutButton);

        // At the beginning, the signOutButton should be disabled
        signOutButton.setEnabled(false);

        // Sign in
        signInButton.addActionListener((e -> {
            if(signIn()) {
                // If the user have already signed in, the signInButton should be disabled, while signOutButton is enabled
                signInButton.setEnabled(false);
                signOutButton.setEnabled(true);
            }
        }));

        // A back button and labels in entryPanel
        JButton back = new GeneralButton("");
        JLabel curr = new GeneralLabel("Currency");
        JLabel val = new GeneralLabel("Value");
        JLabel date = new GeneralLabel("Date");
        JLabel from = new GeneralLabel("From");
        JLabel to = new GeneralLabel("To");
        JLabel location = new GeneralLabel("Location");
        JLabel description = new GeneralLabel("Description");

        entryPanel.add(back);
        entryPanel.add(curr);
        entryPanel.add(val);
        entryPanel.add(date);
        entryPanel.add(from);
        entryPanel.add(to);
        entryPanel.add(location);
        entryPanel.add(description);

        // Constrains for those labels
        SpringLayout.Constraints backCons = entryLayout.getConstraints(back);
        backCons.setX(Spring.constant(entryPanel.getX() - 15));
        backCons.setY(Spring.constant(entryPanel.getY() + 30));
        backCons.setWidth(Spring.constant(30));
        backCons.setHeight(Spring.constant(30));

        SpringLayout.Constraints currCons = entryLayout.getConstraints(curr);
        currCons.setX(Spring.sum(backCons.getConstraint(SpringLayout.EAST), Spring.constant(10)));
        currCons.setY(Spring.constant(entryPanel.getY() + 35));

        SpringLayout.Constraints valCons = entryLayout.getConstraints(val);
        valCons.setX(Spring.sum(currCons.getConstraint(SpringLayout.EAST), Spring.constant(40)));
        valCons.setY(Spring.constant(entryPanel.getY() + 35));

        SpringLayout.Constraints dateCons = entryLayout.getConstraints(date);
        dateCons.setX(Spring.sum(valCons.getConstraint(SpringLayout.EAST), Spring.constant(50)));
        dateCons.setY(Spring.constant(entryPanel.getY() + 35));

        SpringLayout.Constraints fromCons = entryLayout.getConstraints(from);
        fromCons.setX(Spring.sum(dateCons.getConstraint(SpringLayout.EAST), Spring.constant(50)));
        fromCons.setY(Spring.constant(entryPanel.getY() + 35));

        SpringLayout.Constraints toCons = entryLayout.getConstraints(to);
        toCons.setX(Spring.sum(fromCons.getConstraint(SpringLayout.EAST), Spring.constant(40)));
        toCons.setY(Spring.constant(entryPanel.getY() + 35));

        SpringLayout.Constraints locCons = entryLayout.getConstraints(location);
        locCons.setX(Spring.sum(toCons.getConstraint(SpringLayout.EAST), Spring.constant(50)));
        locCons.setY(Spring.constant(entryPanel.getY() + 35));

        SpringLayout.Constraints desCons = entryLayout.getConstraints(description);
        desCons.setX(Spring.sum(locCons.getConstraint(SpringLayout.EAST), Spring.constant(40)));
        desCons.setY(Spring.constant(entryPanel.getY() + 35));

        // Bills in scrollPanel
        initBillView();

    }

    private void initBillView(){

    }

    private boolean signIn(){
        SignInDialog dlg = new SignInDialog(this);
        if (dlg.exec()){
            System.out.println(dlg.getInput());
            return true;
        }
        return false;
    }
}
