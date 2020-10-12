package process.planner.services;

import process.planner.models.Process;
import process.planner.models.Suite;

import java.time.LocalDate;
import java.util.ArrayList;

;

public class ValidationService {
    public ArrayList<String> validateDates(ArrayList<Suite> suitesList) {
        ArrayList<String> dateClashes = new ArrayList<>();

        for (int i = 0; i < suitesList.size(); i++) {
            Suite suite = suitesList.get(i);
            ArrayList<LocalDate> suiteDatesList = suite.getProcessDates();
            Process process = suite.getProcess();
            process.getSteps();
        }

        return dateClashes;
    }
}
