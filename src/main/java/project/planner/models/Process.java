package project.planner.models;

import java.util.ArrayList;

public class Process {
    private final ArrayList<Step> steps;
    private int processStaggerDays;
    private int processLength;

    public Process() {
        this.steps = new ArrayList<>();
    }

    public Process(int processStaggerDays) {
        this.steps = new ArrayList<>();
        this.processStaggerDays = processStaggerDays;
    }

    public int getProcessStaggerDays() {
        return processStaggerDays;
    }

    public int getProcessLength() {
        return processLength;
    }

    public void addStep(Step step) {
        step.setStepNumber(this.getNextStepNumber());
        this.processLength += step.getFullDays();
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
