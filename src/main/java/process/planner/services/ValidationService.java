package process.planner.services;

import process.planner.Config;
import process.planner.models.Suite;

import java.time.LocalDate;
import java.util.ArrayList;


public class ValidationService {
    public static ArrayList<String> validateDates(ArrayList<Suite> suitesList) {
        Config config = new Config();
        ArrayList<String> dateClashes = new ArrayList<>();

        for (int i = 0; i < suitesList.size(); i++) {
            Suite suite = suitesList.get(i);
            ArrayList<LocalDate> suiteDatesList = suite.getProcessDates();
            if (suiteDatesList.size() == 1) {
                continue;
            }
            for (int j = 1; j < suiteDatesList.size(); j++) {
                LocalDate firstDate = suiteDatesList.get(j - 1);
                LocalDate secondDate = suiteDatesList.get(j);
                int daysBetweenDates = (int) (secondDate.toEpochDay() - firstDate.toEpochDay());
                if (daysBetweenDates <= config.PROCESS_DAY_STAGGER) {
                    dateClashes.add(String.format("Suite: %d --- Date: %s is to close to Date: %s", suite.getSuiteNumber(), firstDate.toString(), secondDate.toString()));
                }
            }
        }

        return dateClashes;
    }
}
