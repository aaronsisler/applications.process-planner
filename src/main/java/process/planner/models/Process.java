package process.planner.models;

import java.util.ArrayList;

public class Process {
    private final ArrayList<Step> steps;
    private int processLength;

    public Process() {
        this.steps = new ArrayList<>();
    }

    public int getProcessLength() {
        return processLength;
    }

    public void addStep(Step step) {
        step.setStepNumber(this.getNextStepNumber());
        this.processLength += step.getHalfDayCount();
        this.steps.add(step);
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public int getStepCount() {
        return this.steps.size();
    }

    public int getNextStepNumber() {
        return this.steps.size() + 1;
    }
}
