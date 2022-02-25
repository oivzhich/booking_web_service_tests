package utils;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT);

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

    public static String daysFrom(String startDate, int plusDays) {
        LocalDate localDate = LocalDate.parse(startDate, formatter).plusDays(plusDays);
        return localDate.format(formatter);
    }
}
