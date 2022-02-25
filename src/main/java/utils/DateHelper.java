package utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    private DateHelper() {
    }

    public static String getDateFormattedAsString(ZonedDateTime zonedDateTime) {
        return zonedDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT)).replace('T', ' ');
    }

    public static String getDateFormattedAsString(ZonedDateTime zonedDateTime, String pattern) {
        return zonedDateTime.format(DateTimeFormatter.ofPattern(pattern)).replace('T', ' ');
    }

    public static String today() {
        return getDateFormattedAsString(ZonedDateTime.now());
    }

    public static String daysFromToday(int days) {
        return getDateFormattedAsString(ZonedDateTime.now().plusDays(days));
    }

}
