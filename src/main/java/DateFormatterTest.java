package java.gupta;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatterTest {
    private static final String DATE_TIME_FORMAT_PATTERN = "E, dd MMM yyyy HH:mm:ss zzz";
    private static final String DATE_TIME_FORMAT_LOCALE_TAG = "en_US_POSIX";

    public static void main(String... args) {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_PATTERN)
                .withLocale(Locale.forLanguageTag(DATE_TIME_FORMAT_LOCALE_TAG))
                .withZone(ZoneId.systemDefault());
        final String dateString = dateTimeFormatter.format(Instant.now());
        System.out.println(dateString);
    }
}
