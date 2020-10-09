package process.planner;

import process.planner.models.Process;
import process.planner.services.ExcelExportService;
import process.planner.services.DatesService;
import process.planner.services.ProcessDefinitionService;
import process.planner.services.ProcessSchedulerService;

import java.time.LocalDate;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        try {
            System.out.println("Start App");

            // Service that reads input of processes
            Process process = new ProcessDefinitionService().createProcess();

            // Service that reads input of process dates
            ArrayList<LocalDate> processDateList = DatesService.getProcessDateList();

            // Service to read Process and place into an Array
            int[][] processMapping = new ProcessSchedulerService().createProcessMapping(process, processDateList);
            for (int i = 0; i < processMapping.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < processMapping[i].length; j++) {
                    sb.append(processMapping[i][j]);
                    sb.append(' ');
                }
                System.out.println(sb.toString());
            }
            // Service to take Array and make an Excel sheet
            ExcelExportService.exportExcelFile(processMapping);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
