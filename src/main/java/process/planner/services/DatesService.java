package process.planner.services;

import process.planner.utils.DateReaderFromFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class DatesService {

    public static ArrayList<LocalDate> getProcessDateList() {
        ArrayList<LocalDate> dateList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);

        ArrayList<String> rawDateList = new DateReaderFromFile().getDates("process-one-dates.txt");

        for (String rawDate : rawDateList) {
            LocalDate date = LocalDate.parse(rawDate, formatter);

            dateList.add(date);
        }

        return dateList;
    }

    public static long getStartDateEpochDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse("01/01/2020", formatter);

        return date.toEpochDay();
    }
}
