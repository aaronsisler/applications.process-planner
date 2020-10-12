package process.planner;

import process.planner.models.Process;
import process.planner.models.Suite;
import process.planner.services.DatesService;
import process.planner.services.ExcelExportService;
import process.planner.services.ProcessDefinitionService;
import process.planner.services.SchedulerService;
import process.planner.utils.FolderReader;

import java.time.LocalDate;
import java.util.ArrayList;


public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Start App");

            ArrayList<String> filepathProcessDefinitionList = FolderReader.retrieveProcessDefinitionFiles();

            ArrayList<String> filepathProcessDateList = FolderReader.retrieveDateFiles();

            // Service that reads input of processes
            ArrayList<Suite> suiteList = new ArrayList<>();
            for (String filepath : filepathProcessDefinitionList) {
                Process tempProcess = new ProcessDefinitionService().retrieveProcess(filepath);
                Suite tempSuite = new Suite();
                tempSuite.setProcess(tempProcess);
                suiteList.add(tempSuite);
            }

            for (int i = 0; i < filepathProcessDateList.size(); i++) {
                String filepath = filepathProcessDateList.get(i);
                ArrayList<LocalDate> processDateList = DatesService.getProcessDateList(filepath);
                Suite tempSuite = suiteList.get(i);
                tempSuite.setProcessDates(processDateList);
            }

            // Service to read Process and place into an Array
            int[][] suitesSchedule = new SchedulerService().createSuitesSchedule(suiteList);
//            App.printOutProcessMapping(suitesSchedule);

            int suitesScheduleLength = suitesSchedule[0].length;
            int[] employeeCountsPerDay = new int[suitesScheduleLength];
            for (int[] row : suitesSchedule) {
                for (int j = 0; j < suitesSchedule[0].length; j++) {
                    if (row[j] > 0) {
                        employeeCountsPerDay[j] += row[j];
                    }
                }
            }

            // Service to take Array and make an Excel sheet
            ExcelExportService.exportExcelFile(suitesSchedule, employeeCountsPerDay);
        } catch (Exception e) {
            System.out.println("We broke");
            System.out.println(e.getMessage());
        }
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
