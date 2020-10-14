package process.planner.services;

import process.planner.models.Process;
import process.planner.models.Step;
import process.planner.utils.DefinitionReaderFromFile;

import java.util.ArrayList;
import java.util.Arrays;

public class DefinitionService {
    public Process retrieveProcess(String filepath) {
        ArrayList<String> rawStepDefinitions = new DefinitionReaderFromFile().getRawStepDefinitions(filepath);
        Process process = new Process();

        for (String rawStep : rawStepDefinitions) {
            String[] rawStepSections = rawStep.split("~");
            int stepStagger = Integer.parseInt(rawStepSections[0]);
            int[] halfDayCounts = Arrays.stream(rawStepSections[1].split(",")).mapToInt(Integer::parseInt).toArray();
            String stepColor = rawStepSections[2];
            Step tempStep = new Step();
            tempStep.setStaggerFromStart(stepStagger);
            tempStep.setStepColor(stepColor);
            for (int employeeCount : halfDayCounts) {
                tempStep.addHalfDay(employeeCount);
            }
            process.addStep(tempStep);
        }

        return process;
    }
}
