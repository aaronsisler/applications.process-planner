package process.planner.models;

import java.util.ArrayList;

public class Step {
    private final ArrayList<HalfDay> halfDays;
    private int stepNumber;
    private int previousStepHalfDays = 1;

    public Step() {
        halfDays = new ArrayList<>();
    }

    public Step(ArrayList<HalfDay> halfDays) {
        this.halfDays = halfDays;
    }

    public int getPreviousStepHalfDays() {
        return previousStepHalfDays;
    }

    public void setPreviousStepHalfDays(int previousStepHalfDays) {
        this.previousStepHalfDays = previousStepHalfDays;
    }

    public ArrayList<HalfDay> getHalfDays() {
        return halfDays;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public int getHalfDayCount() {
        return this.halfDays.size();
    }

    public void addHalfDay(HalfDay halfDay) {
        this.halfDays.add(halfDay);
    }

    public void addHalfDay(int numberOfEmployees) {
        HalfDay halfDay = new HalfDay(numberOfEmployees);

        this.halfDays.add(halfDay);
    }
}
