package billgates;

import billgates.entities.AbstractEntry;
import billgates.entities.EntryBuilder;
import billgates.view.BillGatesUtilities;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Utilities for testing.
 *
 * @author Scott
 */
public class TestUtilities {

    /**
     * Generates a list of n randomly generated entries based on the given parameters,
     * where n is a random integer between <code>sizeMin</code> and <code>sizeMax</code>.
     *
     * @param sizeMin         the minimum size of the generated list
     * @param sizeMax         the maximum size of the generate list
     * @param textLengthMin   the minimum size of the lengths of the generated strings (used for
     *                        description, from, to, and ... attributes)
     * @param textLengthMax   the maximum size of the lengths of the generated strings (used for
     *                        description, from, to, and ... attributes)
     * @param isSplitterEntry a boolean represents the type of the generated entries
     * @return a list of n randomly generated entries based on the given parameters,
     * where n is a random integer between <code>sizeMin</code> and <code>sizeMax</code>.
     */
    public static List<AbstractEntry> generateRandomEntries(int sizeMin, int sizeMax,
                                                            int textLengthMin, int textLengthMax,
                                                            boolean isSplitterEntry) {
        Random random = new Random();
        int n = random.nextInt(sizeMin, sizeMax + 1);
        List<AbstractEntry> result = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            long epoch = Instant.EPOCH.getEpochSecond();
            long now = Instant.now().getEpochSecond();
            long randomSecond = random.nextLong(epoch, now);
            ZonedDateTime randomDatetime = ZonedDateTime.ofInstant(
                    Instant.ofEpochMilli(randomSecond * 1000), ZoneId.systemDefault());
            double value = Double.parseDouble(new DecimalFormat("0.00")
                    .format(random.nextDouble() * 10000));
            String currency = BillGatesUtilities.CURRENCY_CODES.get(
                    random.nextInt(0, BillGatesUtilities.CURRENCY_CODES.size()));
            // generate random strings
            String description = generateRandomText(textLengthMin, textLengthMax);
            String from = generateRandomText(textLengthMin, textLengthMax);
            String to = generateRandomText(textLengthMin, textLengthMax);
            String location = generateRandomText(textLengthMin, textLengthMax);
            if (isSplitterEntry) {
                String payee = generateRandomText(textLengthMin, textLengthMax);
                boolean isPaid = Boolean.parseBoolean(String.valueOf(random.nextInt(0, 2)));
                result.add(new EntryBuilder()
                        .setId(i)
                        .setDate(randomDatetime)
                        .setValue(value)
                        .setCurrency(currency)
                        .setDescription(description)
                        .setFrom(from)
                        .setTo(to)
                        .setLocation(location)
                        .setPayee(payee)
                        .setIsPaidBack(isPaid)
                        .buildSplitterEntry());
            } else {
                result.add(new EntryBuilder()
                        .setId(i)
                        .setDate(randomDatetime)
                        .setValue(value)
                        .setCurrency(currency)
                        .setDescription(description)
                        .setFrom(from)
                        .setTo(to)
                        .setLocation(location)
                        .buildEntry());
            }
        }
        result.sort(Collections.reverseOrder(Comparator.comparing(entry ->
                entry.getDate().getAttribute())));
        return result;
    }

    /**
     * Converts the given list of entries to a list of object lists.
     * The conversion algorithm is the same as that in <code>BillUpdatePresenter</code>.
     *
     * @param entries         a list of entries to be converted.
     * @param isSplitterEntry a boolean represents the type of the given list of entries
     * @return a list of object lists representing the given list of entries.
     * @see billgates.use_cases.bill_update.BillUpdatePresenter
     */
    public static List<List<Object>> toFormattedEntries(List<AbstractEntry> entries,
                                                        boolean isSplitterEntry) {
        List<List<Object>> result = new ArrayList<>(entries.size());
        entries.forEach(entry -> {
            List<Object> objects = entry.toObjects();
            String s = ((ZonedDateTime) objects.get(1)).format(DateTimeFormatter.ofPattern(
                    BillGatesUtilities.DATETIME_PATTERN));
            objects.set(1, s);
            if (!isSplitterEntry) {
                objects.set(objects.size() - 1, (int) objects.get(objects.size() - 1) ==
                        -1 ? "No" : "Yes");
            }
            result.add(objects);
        });
        return result;
    }

    public static String generateRandomText(int textLengthMin, int textLengthMax) {
        Random random = new Random();
        // generate random strings
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = random.nextInt(textLengthMin, textLengthMax);
        return random.ints(leftLimit, rightLimit + 1)
                .filter(j -> (j <= 57 || j >= 65) && (j <= 90 || j >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
    }

}
