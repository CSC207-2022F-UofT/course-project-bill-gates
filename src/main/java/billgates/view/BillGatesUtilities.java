package billgates.view;

import java.util.*;

/**
 * Clean Architecture Layer: Frameworks & Drivers
 *
 * @author Ellen
 */
public class BillGatesUtilities {

    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_PATTERN = "HH:mm:ss";

    static {
        CURRENCY_CODES = getCurrencyCodes();
    }

    /**
     * A list of all available currency codes of the current JVM.
     * It starts with CAD, CNY, and USD; and follows by all currency codes sorted by ascending
     * alphabetical order.
     */
    public static final List<String> CURRENCY_CODES;

    /**
     * Gets a list of available currency codes according to ISO 3166.
     * Notes: CAD, CNY, USD are the top three currencies.
     *
     * @return a list of currency codes sorted in ascending alphabetical order with CAD, CNY,
     * and USD at the beginning.
     */
    public static List<String> getCurrencyCodes() {
        List<String> result = new ArrayList<>(Arrays.asList("CAD", "CNY", "USD"));
        Set<String> temp = new TreeSet<>();
        for (Locale locale : Locale.getAvailableLocales()) {
            try {
                temp.add(Currency.getInstance(locale).getCurrencyCode());
            } catch (IllegalArgumentException ignore) {
            }
        }
        result.addAll(temp);
        return result;
    }

}
