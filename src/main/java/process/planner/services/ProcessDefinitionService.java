package process.planner.services;

import process.planner.models.Process;
import process.planner.models.Step;
import process.planner.utils.DefinitionReaderFromFile;

import java.util.ArrayList;

public class ProcessDefinitionService {
    public Process retrieveProcess(String filepath) {
        ArrayList<int[]> stepDefinition = new DefinitionReaderFromFile().getProcessDefinition(filepath);
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
}
