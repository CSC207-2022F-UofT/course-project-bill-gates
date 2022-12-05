package billgates.view.gui;

import billgates.Main;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    private final DateTimePicker dateTimePicker;

    public DateTableCellEditor() {
        DatePickerSettings datePickerSettings = new DatePickerSettings();
        TimePickerSettings timePickerSettings = new TimePickerSettings();
        timePickerSettings.setFormatForMenuTimes("HH:mm:ss");
        timePickerSettings.setFormatForDisplayTime("HH:mm:ss");
        this.dateTimePicker = new DateTimePicker(datePickerSettings, timePickerSettings);
    }

    @Override
    public Object getCellEditorValue() {
        return this.dateTimePicker.getDateTimePermissive()
                .format(DateTimeFormatter.ofPattern(Main.DATETIME_PATTERN));
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
                                                 int row, int column) {
        LocalDateTime datetime = LocalDateTime.parse((String) value,
                DateTimeFormatter.ofPattern(Main.DATETIME_PATTERN));
        this.dateTimePicker.setDateTimeStrict(datetime);
        return this.dateTimePicker;
    }
}
