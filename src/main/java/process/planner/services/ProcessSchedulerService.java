package process.planner.services;

import process.planner.models.HalfDay;
import process.planner.models.Process;
import process.planner.models.Step;
import process.planner.utils.DaysOfTheMonth;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProcessSchedulerService {
    private int NUMBER_OF_CALENDAR_DAYS = DaysOfTheMonth.getDaysInMonth("01", 2020);
    private int NUMBER_OF_CALENDAR_HALF_DAYS = NUMBER_OF_CALENDAR_DAYS * 2;

    public int[][] createProcessMapping(Process process, ArrayList<LocalDate> dateList) {
        int numberOfSteps = process.getStepCount();
        NUMBER_OF_CALENDAR_DAYS = DaysOfTheMonth.getDaysInMonth("01", 2020);
        int[][] processMap = new int[numberOfSteps][NUMBER_OF_CALENDAR_HALF_DAYS];

        long beginDateInDays = DatesService.getStartDateEpochDate();

        for (LocalDate localDate : dateList) {
            long tempDate = localDate.toEpochDay();
            int dateStagger = (int) (tempDate - beginDateInDays) * 2;

            int previousStepDayCount = 0;
            for (Step step : process.getSteps()) {
                if (step.getStepNumber() != 1) {
                    previousStepDayCount += step.getPreviousStepHalfDays() - 1;
                }

                for (HalfDay halfDay : step.getHalfDays()) {
                    int dayIndex = step.getHalfDays().indexOf(halfDay);
                    int nValue = dateStagger + previousStepDayCount + dayIndex;
                    if(nValue >= NUMBER_OF_CALENDAR_HALF_DAYS) {
                        break;
                    }
                    processMap[step.getStepNumber() - 1][nValue] = halfDay.getNumberOfEmployees();
                }
            }
        }

        return processMap;
    }
}
