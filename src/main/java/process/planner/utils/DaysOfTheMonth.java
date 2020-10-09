package process.planner.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DaysOfTheMonth {
    public static int getDaysInMonth(String rawMonth, int year) {
        String month = rawMonth.length() == 1 ? String.format("0%d", rawMonth) : rawMonth;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
        String rawDate = String.format("%s/01/%d", month, year);
        LocalDate date = LocalDate.parse(rawDate, formatter);

        return date.lengthOfMonth();
    }
}
