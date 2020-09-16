package project.planner.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class ProcessStaggerService {

    public ArrayList<LocalDate> getProcessDateList() {
        ArrayList dateList = new ArrayList();
        for (int i = 1; i < 6; i++) {
            String string = String.format("0%d/01/2020", i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(string, formatter);
            dateList.add(date);
        }

        return dateList;
    }

    public int getProcessStaggerValue() {
        int processStaggerInDays = 14;
        return processStaggerInDays;
    }
}
