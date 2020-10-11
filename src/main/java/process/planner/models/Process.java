package process.planner.models;

import java.util.ArrayList;

public class Process {
    private final ArrayList<Step> steps;

    public Process() {
        this.steps = new ArrayList<>();
    }

    public void addStep(Step step) {
        step.setStepNumber(this.getNextStepNumber());
        if (this.steps.size() > 0) {
            int halfDayCount = this.steps.get(this.steps.size() - 1).getHalfDayCount();
            step.setPreviousStepHalfDays(halfDayCount);
        }
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
