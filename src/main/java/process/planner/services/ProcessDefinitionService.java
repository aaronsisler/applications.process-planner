package process.planner.services;

import process.planner.models.Process;
import process.planner.models.Step;
import process.planner.utils.ProcessReaderFromFile;

import java.util.ArrayList;

public class ProcessDefinitionService {
    public Process createProcess() {
        ArrayList<int[]> stepDefinition = new ProcessReaderFromFile().getProcessDefinition();
        Process process = new Process();

        for (int[] dayDef : stepDefinition) {
            Step tempStep = new Step();
            for (int employeeCount : dayDef) {
                tempStep.addHalfDay(employeeCount);
            }
            process.addStep(tempStep);
        }

        return process;
    }

    public Process createProcessOld() {
        Process process = new Process();

        for (int i = 1; i < 5; i++) {
            Step tempStep = getStepData(i);

            process.addStep(tempStep);
        }

        return process;
    }

    private Step getStepData(int numberOfHalfDays) {
        Step step = new Step();

        for (int i = 1; i <= numberOfHalfDays; i++) {
            step.addHalfDay(2);
        }

        return step;
    }
}
