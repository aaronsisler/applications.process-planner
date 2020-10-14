package process.planner.services;

import process.planner.Config;
import process.planner.models.HalfDay;
import process.planner.models.Step;
import process.planner.models.Suite;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class SchedulerService {
    private final int NUMBER_OF_CALENDAR_DAYS = DatesService.getNumberOfDaysInYear(2021);
    private final int NUMBER_OF_CALENDAR_HALF_DAYS = NUMBER_OF_CALENDAR_DAYS * 2;
    private final Config config = new Config();

    public int[][] createSuitesSchedule(ArrayList<Suite> suiteList) {
        int numberOfSteps = 0;
        for (Suite suite : suiteList) {
            numberOfSteps += suite.getProcess().getStepCount();
            if (suiteList.indexOf(suite) < suiteList.size() - 1) {
                numberOfSteps += 1;
            }
        }


        int[][] processMap = new int[numberOfSteps][NUMBER_OF_CALENDAR_HALF_DAYS];
        for (int[] row : processMap) {
            Arrays.fill(row, config.EMPTY_CELL);
        }

        int previousSuiteStepCount = 0;
        for (Suite suite : suiteList) {
            processMap = this.createSuiteMapping(processMap, suite, previousSuiteStepCount);
            previousSuiteStepCount += suite.getProcess().getStepCount();
            if (numberOfSteps <= previousSuiteStepCount) {
                break;
            }
            processMap = this.addBlankRow(processMap, previousSuiteStepCount);
            previousSuiteStepCount++;
        }

        return processMap;
    }

    private int[][] addBlankRow(int[][] processMap, int previousSuiteStepCount) {
        for (int j = 0; j < processMap[0].length; j++) {
            processMap[previousSuiteStepCount][j] = config.FILLED_CELL;
        }

        return processMap;
    }

    private int[][] createSuiteMapping(int[][] processMap, Suite suite, int previousSuiteStepCount) {
        long beginDateInDays = DatesService.getStartDateEpochDate();

        for (LocalDate localDate : suite.getProcessDates()) {
            long tempDate = localDate.toEpochDay();
            int dateStaggerFromBeginning = (int) (tempDate - beginDateInDays) * 2;

            for (Step step : suite.getProcess().getSteps()) {
                int mValue = previousSuiteStepCount + step.getStepNumber() - 1;
                for (HalfDay halfDay : step.getHalfDays()) {
                    int dayIndex = step.getHalfDays().indexOf(halfDay);
                    int nValue = dateStaggerFromBeginning + step.getStaggerFromStart() + dayIndex;
                    if (nValue >= NUMBER_OF_CALENDAR_HALF_DAYS) {
                        break;
                    }
                    processMap[mValue][nValue] = halfDay.getNumberOfEmployees();
                }
            }
        }

        return processMap;
    }
}
