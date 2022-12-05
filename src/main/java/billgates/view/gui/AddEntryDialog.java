package billgates.view.gui;

import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AddEntryDialog extends JDialog {

    private int result;
    private ZonedDateTime date;
    private static double value;
    private String currency;
    private String description;
    private String from;
    private String to;
    private String location;
    private final MainFrame mainFrame;

    public AddEntryDialog(MainFrame owner) {
        super(owner);
        this.mainFrame = owner;
        // Create a datetime picker.
        DateTimePicker dateTimePicker1 = new DateTimePicker();
        add(dateTimePicker1);

        JTextField valueField = new JTextField(20);
        valueField.setDocument(new RegexDocument("\\S*"));
        valueField.setDocument(new RegexDocument("\\d*\\.?\\d{0,2}"));

        JTextField currencyField = new JTextField(20);
        currencyField.setDocument(new RegexDocument("\\S*"));
        currencyField.setDocument(new RegexDocument("[A-Z]*"));

        JTextField descriptionField = new JTextField(20);

        JTextField fromField = new JTextField(20);

        JTextField toField = new JTextField(20);

        JTextField locationField = new JTextField(20);

        JPanel insertEntryDialog = new JPanel();
        insertEntryDialog.setLayout(new BoxLayout(insertEntryDialog, BoxLayout.PAGE_AXIS));
        insertEntryDialog.add(new JLabel("Date and Time:"));
        insertEntryDialog.add(dateTimePicker1);
        insertEntryDialog.add(new JLabel("Value:"));
        insertEntryDialog.add(valueField);
        insertEntryDialog.add(new JLabel("Currency:"));
        insertEntryDialog.add(currencyField);
        insertEntryDialog.add(new JLabel("Description:"));
        insertEntryDialog.add(descriptionField);
        insertEntryDialog.add(new JLabel("From:"));
        insertEntryDialog.add(fromField);
        insertEntryDialog.add(new JLabel("To:"));
        insertEntryDialog.add(toField);
        insertEntryDialog.add(new JLabel("Location:"));
        insertEntryDialog.add(locationField);

        result = JOptionPane.showConfirmDialog(null, insertEntryDialog,
                "Information about the new entry", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            LocalDateTime localDateTime = dateTimePicker1.getDateTimePermissive();
            date = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
            String value1 = valueField.getText();
            value = Double.parseDouble(value1);
            currency = currencyField.getText();
            description = descriptionField.getText();
            from = fromField.getText();
            to = toField.getText();
            location = locationField.getText();
        }
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getLocation_text() {
        return location;
    }

}
