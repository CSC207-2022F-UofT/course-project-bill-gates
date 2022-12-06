package billgates.view.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Charlotte
 */
public class RegexDocument extends PlainDocument {
    private final Pattern pattern;

    public RegexDocument(String regex) {
        super();
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }
        String text = this.getText(0, offs).concat(str);
        Matcher matcher = this.pattern.matcher(text);

        if (matcher.matches()) {
            super.insertString(offs, str, a);
        }
    }
}
