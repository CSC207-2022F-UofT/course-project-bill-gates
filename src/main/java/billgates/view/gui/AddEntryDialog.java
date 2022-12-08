package billgates.view.gui;

import billgates.use_cases.insert_entry.InsertEntryRequestModel;
import billgates.view.BillGatesUtilities;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import javax.swing.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Ellen
 */
public class AddEntryDialog extends JPanel {
    private final MainFrame mainFrame;
    private DateTimePicker dateTimePicker;
    private ActionTextField valueField;
    private ActionComboBox<String> currencyComboBox;
    private ActionTextField descriptionField;
    private ActionTextField fromField;
    private ActionTextField toField;
    private ActionTextField locationField;
    private ActionTextField payeeField;
    private JCheckBox paidBackCheckBox;

    public AddEntryDialog(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        initDialog();
    }

    public InsertEntryRequestModel exec() {
        int result = JOptionPane.showConfirmDialog(this.mainFrame, this,
                "Adding an entry...", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            LocalDateTime datetime = this.dateTimePicker.getDateTimePermissive();
            InsertEntryRequestModel model = new InsertEntryRequestModel(
                    ZonedDateTime.of(datetime, ZoneId.systemDefault()),
                    Double.parseDouble(this.valueField.getText()),
                    Objects.requireNonNull(this.currencyComboBox.getSelectedItem()).toString(),
                    this.descriptionField.getText(),
                    this.fromField.getText(),
                    this.toField.getText(),
                    this.locationField.getText());
            if (this.mainFrame.isSplitterBill()) {
                model.setPayee(this.payeeField.getText());
                model.setPaidBack(this.paidBackCheckBox.isSelected());
            }
            return model;
        } else {
            return null;
        }
    }

    private void initDialog() {
        this.add(new ActionLabel("Date and Time:"));
        // Create a datetime picker.
        DatePickerSettings datePickerSettings = new DatePickerSettings();
        TimePickerSettings timePickerSettings = new TimePickerSettings();
        timePickerSettings.setFormatForMenuTimes(BillGatesUtilities.TIME_PATTERN);
        timePickerSettings.setFormatForDisplayTime(BillGatesUtilities.TIME_PATTERN);
        this.dateTimePicker = new DateTimePicker(datePickerSettings, timePickerSettings);
        this.dateTimePicker.setAlignmentX(LEFT_ALIGNMENT);
        this.dateTimePicker.setDateTimeStrict(LocalDateTime.now());
        this.add(this.dateTimePicker);

        this.add(new ActionLabel("Value:"));
        this.valueField = new ActionTextField(20);
        this.valueField.setDocument(new RegexDocument("\\d*\\.?\\d{0,2}"));
        this.valueField.setText(NumberFormat.getInstance().format(0D));
        this.valueField.setAlignmentX(LEFT_ALIGNMENT);
        this.add(this.valueField);

        this.add(new ActionLabel("Currency:"));
        this.currencyComboBox = new ActionComboBox<>(BillGatesUtilities.CURRENCY_CODES);
        this.currencyComboBox.setAlignmentX(LEFT_ALIGNMENT);
        this.currencyComboBox.setFont(ActionTextField.DEFAULT_FONT);
        this.add(this.currencyComboBox);

        this.add(new ActionLabel("Description:"));
        this.descriptionField = new ActionTextField(20);
        this.descriptionField.setAlignmentX(LEFT_ALIGNMENT);
        this.add(this.descriptionField);

        this.add(new ActionLabel("From:"));
        this.fromField = new ActionTextField(20);
        this.fromField.setAlignmentX(LEFT_ALIGNMENT);
        this.add(this.fromField);

        this.add(new ActionLabel("To:"));
        this.toField = new ActionTextField(20);
        this.toField.setAlignmentX(LEFT_ALIGNMENT);
        this.add(this.toField);

        this.add(new ActionLabel("Location:"));
        this.locationField = new ActionTextField(20);
        this.locationField.setAlignmentX(LEFT_ALIGNMENT);
        this.add(this.locationField);

        if (this.mainFrame.isSplitterBill()) {
            this.add(new ActionLabel("Payee:"));
            this.payeeField = new ActionTextField(20);
            this.payeeField.setAlignmentX(LEFT_ALIGNMENT);
            this.add(this.payeeField);

            this.paidBackCheckBox = new JCheckBox("Paid Back", false);
            this.paidBackCheckBox.setAlignmentX(LEFT_ALIGNMENT);
            this.add(this.paidBackCheckBox);
        }
    }
}
