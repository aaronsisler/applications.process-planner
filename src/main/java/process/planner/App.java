package process.planner;

import process.planner.models.Process;
import process.planner.models.Suite;
import process.planner.services.DatesService;
import process.planner.services.ExcelExportService;
import process.planner.services.ProcessDefinitionService;
import process.planner.services.SchedulerService;

import java.time.LocalDate;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Start App");

            ArrayList<String> filepathProcessDefintionList = new ArrayList<>();
            filepathProcessDefintionList.add("process-definition-one.txt");
            filepathProcessDefintionList.add("process-definition-two.txt");
            filepathProcessDefintionList.add("process-definition-three.txt");

            ArrayList<String> filepathProcessDateList = new ArrayList<>();
            filepathProcessDateList.add("process-dates-one.txt");
            filepathProcessDateList.add("process-dates-two.txt");
            filepathProcessDateList.add("process-dates-three.txt");

            // Service that reads input of processes
            ArrayList<Suite> suiteList = new ArrayList<>();
            for (int i = 0; i < filepathProcessDefintionList.size(); i++) {
                String filepath = filepathProcessDefintionList.get(i);
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
            for (int i = 0; i < suitesSchedule.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < suitesSchedule[i].length; j++) {
                    sb.append(suitesSchedule[i][j]);
                    sb.append(' ');
                }
                System.out.println(sb.toString());
            }

            int suitesScheduleLength = suitesSchedule[0].length;
            int[] employeeCountsPerDay = new int[suitesScheduleLength];
            for (int i = 0; i < suitesSchedule.length; i++) {
                for (int j = 0; j < suitesSchedule[0].length; j++) {
                    employeeCountsPerDay[j] += suitesSchedule[i][j];
                }
            }

            // Service to take Array and make an Excel sheet
            ExcelExportService.exportExcelFile(suitesSchedule, employeeCountsPerDay);
        } catch (Exception e) {
            System.out.println("We broke");
            System.out.println(e.getMessage());
        }

    }
}
