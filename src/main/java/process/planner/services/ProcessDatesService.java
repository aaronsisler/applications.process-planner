package process.planner.services;

import process.planner.utils.DateReaderFromFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class ProcessDatesService {

    public ArrayList<LocalDate> getProcessDateList() {
        ArrayList<LocalDate> dateList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
        int staggerValue = 7;

        ArrayList<String> rawDateList = new DateReaderFromFile().getDates("process-one-dates.txt");

        for (String rawDate : rawDateList) {
            LocalDate date = LocalDate.parse(rawDate, formatter);

            dateList.add(date);
        }

//        for (int i = 1; i <= 3; i++) {
//            String paddedValue = String.format("0%d", i * staggerValue);
//            String trimmedValue = paddedValue.substring(paddedValue.length() - 2);
//            String rawStringDate = String.format("01/%s/2020", trimmedValue);
//            LocalDate date = LocalDate.parse(rawStringDate, formatter);
//
//            dateList.add(date);
//        }

        return dateList;
    }
}
