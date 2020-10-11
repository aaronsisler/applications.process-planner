package process.planner.services;

import process.planner.models.HalfDay;
import process.planner.models.Step;
import process.planner.models.Suite;

import java.time.LocalDate;
import java.util.ArrayList;

public class SchedulerService {
    private final int NUMBER_OF_CALENDAR_DAYS = DatesService.getNumberOfDaysInYear(2021);
    private final int NUMBER_OF_CALENDAR_HALF_DAYS = NUMBER_OF_CALENDAR_DAYS * 2;

    public int[][] createSuitesSchedule(ArrayList<Suite> suiteList) {
        int numberOfSteps = 0;
        for (Suite suite : suiteList) {
            numberOfSteps += suite.getProcess().getStepCount();
            if (suiteList.indexOf(suite) < suiteList.size() - 1) {
                numberOfSteps += 1;
            }
        }

        int[][] processMap = new int[numberOfSteps][NUMBER_OF_CALENDAR_HALF_DAYS];
        int previousSuiteStepCount = 0;
        for (Suite suite : suiteList) {
            processMap = this.createProcessMapping(processMap, suite, previousSuiteStepCount);
            if (numberOfSteps > previousSuiteStepCount + 1) {
                previousSuiteStepCount += suite.getProcess().getStepCount();
                processMap = this.addBlankRow(processMap, previousSuiteStepCount);
                previousSuiteStepCount += 1;
            }
        }

        return processMap;
    }

    private int[][] addBlankRow(int[][] processMap, int previousSuiteStepCount) {
        for (int j = 0; j < processMap[0].length; j++) {
            processMap[previousSuiteStepCount][j] = -1;
        }

        return processMap;
    }

    private int[][] createProcessMapping(int[][] processMap, Suite suite, int previousSuiteStepCount) {
        long beginDateInDays = DatesService.getStartDateEpochDate();

        for (LocalDate localDate : suite.getProcessDates()) {
            long tempDate = localDate.toEpochDay();
            int dateStagger = (int) (tempDate - beginDateInDays) * 2;

            int previousStepDayCount = 0;
            for (Step step : suite.getProcess().getSteps()) {
                if (step.getStepNumber() != 1) {
                    previousStepDayCount += step.getPreviousStepHalfDays() - 1;
                }

                for (HalfDay halfDay : step.getHalfDays()) {
                    int dayIndex = step.getHalfDays().indexOf(halfDay);
                    int nValue = dateStagger + previousStepDayCount + dayIndex;
                    if (nValue >= NUMBER_OF_CALENDAR_HALF_DAYS) {
                        break;
                    }
                    int mValue = previousSuiteStepCount + step.getStepNumber() - 1;
                    processMap[mValue][nValue] = halfDay.getNumberOfEmployees();
                }
            }
        }

        return processMap;
    }
}
