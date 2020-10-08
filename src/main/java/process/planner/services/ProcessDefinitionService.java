package process.planner.services;

import process.planner.models.Process;
import process.planner.models.Step;

public class ProcessDefinitionService {
    public Process createProcess() {
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
