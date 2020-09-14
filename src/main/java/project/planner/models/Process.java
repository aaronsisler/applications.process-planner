package project.planner.models;

import java.util.ArrayList;

public class Process {
    private ArrayList<Step> steps;

    public Process() {
        this.steps = new ArrayList<>();
    }

    public void addStep(Step step) {
        this.steps.add(step);
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }
}
