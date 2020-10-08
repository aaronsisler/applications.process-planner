package process.planner.services;

import process.planner.models.HalfDay;
import process.planner.models.Process;
import process.planner.models.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class ProcessSchedulerService {
    private final int NUMBER_OF_CALENDAR_DAYS = 31;

    public int[][] createProcessMapping(Process process, ArrayList<LocalDate> dateList) {
        int numberOfSteps = process.getStepCount();
        int[][] processMap = new int[numberOfSteps][NUMBER_OF_CALENDAR_DAYS];

        long beginDateInDays = this.getStartDateEpochDate();

        for (LocalDate localDate : dateList) {
            long tempDate = localDate.toEpochDay();
            int dateStagger = (int)(tempDate - beginDateInDays);

            int dayCounter = 0;
            for (Step step : process.getSteps()) {
                StringBuilder sb = new StringBuilder();
                for (HalfDay halfDay : step.getHalfDays()) {
                    processMap[step.getStepNumber() - 1][dateStagger + dayCounter] = halfDay.getNumberOfEmployees();
                    dayCounter++;
                }
            }
        }

        return processMap;
    }

    private long getStartDateEpochDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse("01/01/2020", formatter);

        return date.toEpochDay();
    }
}
