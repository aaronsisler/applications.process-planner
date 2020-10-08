package process.planner.services;

import process.planner.models.Process;
import process.planner.models.Step;

public class ProcessSchedulerService {
    private final int NUMBER_OF_CALENDAR_DAYS = 31;

    public int[][] createProcessMapping(Process process, int processStaggerDays) {
        int numberOfSteps = process.getStepCount();
        int[][] processMap = new int[numberOfSteps][NUMBER_OF_CALENDAR_DAYS];

        int previousStepLastDay = 0;
        while (previousStepLastDay + process.getProcessLength() < NUMBER_OF_CALENDAR_DAYS) {
            for (Step step : process.getSteps()) {
//                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < step.getFullDays(); i++) {
//                    sb.append(previousStepLastDay + i);
//                    sb.append(' ');
                    processMap[step.getStepNumber() - 1][i + previousStepLastDay] = 1;
                }
//                System.out.println("Step: " + step.getStepNumber());
//                System.out.println(sb.toString());
                previousStepLastDay += step.getFullDays() - 1;
            }
        }

        return processMap;
    }
}
