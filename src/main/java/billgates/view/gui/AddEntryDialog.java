package billgates.view.gui;

import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
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
        JTextField currencyField = new JTextField(20);
        JTextField descriptionField = new JTextField(20);
        JTextField fromField = new JTextField(20);
        JTextField toField = new JTextField(20);
        JTextField locationField = new JTextField(20);

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
        myPanel.add(new JLabel("date and time:"));
        myPanel.add(dateTimePicker1);
        myPanel.add(new JLabel("value:"));
        myPanel.add(valueField);
        myPanel.add(new JLabel("currency:"));
        myPanel.add(currencyField);
        myPanel.add(new JLabel("description:"));
        myPanel.add(descriptionField);
        myPanel.add(new JLabel("from:"));
        myPanel.add(fromField);
        myPanel.add(new JLabel("to:"));
        myPanel.add(toField);
        myPanel.add(new JLabel("location:"));
        myPanel.add(locationField);

        result = JOptionPane.showConfirmDialog(null, myPanel,
                "Information about the new entry", JOptionPane.OK_CANCEL_OPTION);

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
