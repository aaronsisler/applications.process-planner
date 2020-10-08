package process.planner.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class ProcessDatesService {

    public ArrayList<LocalDate> getProcessDateList() {
        ArrayList dateList = new ArrayList();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
        int staggerValue = 5;

        for (int i = 1; i < 5; i++) {
            String paddedValue = String.format("0%d", i * staggerValue);
            String trimmedValue = paddedValue.substring(paddedValue.length() - 2);
            String rawStringDate = String.format("01/%s/2020", trimmedValue);
            LocalDate date = LocalDate.parse(rawStringDate, formatter);

            dateList.add(date);
        }

        return dateList;
    }
}
