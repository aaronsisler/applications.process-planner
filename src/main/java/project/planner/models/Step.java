package project.planner.models;

public class Step {
    private final int fullDays;
    private final int halfDays;
    private int stepNumber;

    public Step(int fullDays) {
        this.fullDays = fullDays;
        this.halfDays = 0;
    }

    public Step(int fullDays, int halfDays) {
        this.fullDays = fullDays;
        this.halfDays = halfDays;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public int getFullDays() {
        return fullDays;
    }

    public int getHalfDays() {
        return halfDays;
    }

}
