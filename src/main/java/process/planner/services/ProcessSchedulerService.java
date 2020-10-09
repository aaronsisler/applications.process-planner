package process.planner.services;

import process.planner.models.HalfDay;
import process.planner.models.Process;
import process.planner.models.Step;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class ProcessSchedulerService {
    private final int NUMBER_OF_CALENDAR_DAYS = 45;

    public int[][] createProcessMapping(Process process, ArrayList<LocalDate> dateList) {
        int numberOfSteps = process.getStepCount();
        int[][] processMap = new int[numberOfSteps][NUMBER_OF_CALENDAR_DAYS];

        long beginDateInDays = this.getStartDateEpochDate();

        for (LocalDate localDate : dateList) {
            long tempDate = localDate.toEpochDay();
            int dateStagger = (int) (tempDate - beginDateInDays);

            int previousStepDayCount = 0;
            for (Step step : process.getSteps()) {
                if (step.getStepNumber() != 1) {
                    previousStepDayCount += step.getPreviousStepHalfDays() - 1;
                }

                for (HalfDay halfDay : step.getHalfDays()) {
                    int dayIndex = step.getHalfDays().indexOf(halfDay);
                    int nValue = dateStagger + previousStepDayCount + dayIndex;
                    processMap[step.getStepNumber() - 1][nValue] = halfDay.getNumberOfEmployees();
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
