package process.planner;

import process.planner.models.Process;
import process.planner.models.Suite;
import process.planner.services.*;
import process.planner.utils.FolderReader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Start App");

            ArrayList<String> filepathProcessDefinitionList = FolderReader.retrieveProcessDefinitionFiles();

            ArrayList<String> filepathProcessDateList = FolderReader.retrieveDateFiles();

            if(filepathProcessDefinitionList.size() != filepathProcessDateList.size()) {
                System.out.println("Definition file count and date file count does not equal.");
                System.out.println(String.format("Definition file count: ", filepathProcessDefinitionList.size()));
                System.out.println(String.format("Date file count: ", filepathProcessDateList.size()));
                throw new Exception("File count validation error");
            }

            // Service that reads input of processes
            ArrayList<Suite> suiteList = new ArrayList<>();
            for (String filepath : filepathProcessDefinitionList) {
                Process tempProcess = new DefinitionService().retrieveProcess(filepath);
                Suite tempSuite = new Suite();
                tempSuite.setProcess(tempProcess);
                suiteList.add(tempSuite);
            }

            for (int i = 0; i < filepathProcessDateList.size(); i++) {
                String filepath = filepathProcessDateList.get(i);
                ArrayList<LocalDate> processDateList = DatesService.getProcessDateList(filepath);
                Suite tempSuite = suiteList.get(i);
                tempSuite.setSuiteNumber(i+1);
                tempSuite.setProcessDates(processDateList);
            }

            // Run validation on input dates
            ArrayList<String> dateClashList = ValidationService.validateDates(suiteList);
            if (dateClashList.size() > 0) {
                for (String dateClash : dateClashList) {
                    System.out.println(dateClash);
                }
                throw new Exception("Date Validation failed. See above");
            }

            // Service to read Process and place into an Array
            int[][] suitesSchedule = new SchedulerService().createSuitesSchedule(suiteList);
//            App.printOutProcessMapping(suitesSchedule);

            int[] employeeCountsPerDay = processEmployeeCounts(suitesSchedule);

            String[] stepsColorScheme = new String[suitesSchedule.length];
            Arrays.fill(stepsColorScheme, "CORAL");

            // Service to take Array and make an Excel sheet
            ExcelExportService.exportExcelFile(suitesSchedule, employeeCountsPerDay, stepsColorScheme);
        } catch (Exception e) {
            System.out.println("Check above for helpful error messages");
            System.out.println(e.getMessage());
        }
    }

    private static int[] processEmployeeCounts(int[][] suitesSchedule) {
        int suitesScheduleLength = suitesSchedule[0].length;
        int[] employeeCountsPerDay = new int[suitesScheduleLength];
        for (int[] row : suitesSchedule) {
            for (int j = 0; j < suitesSchedule[0].length; j++) {
                if (row[j] > 0) {
                    employeeCountsPerDay[j] += row[j];
                }
            }
        }

        return employeeCountsPerDay;
    }

    private static void printOutProcessMapping(int[][] suitesSchedule) {
        for (int[] row : suitesSchedule) {
            StringBuilder sb = new StringBuilder();
            for (int columnValue : row) {
                sb.append(columnValue);
                sb.append(' ');
            }
            System.out.println(sb.toString());
        }
    }
}
