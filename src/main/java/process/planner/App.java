package process.planner;

import process.planner.models.Process;
import process.planner.services.ProcessDatesService;
import process.planner.services.ProcessDefinitionService;
import process.planner.services.ProcessSchedulerService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        System.out.println("Start App");

        // Service that reads input of processes
        Process process = new ProcessDefinitionService().createProcess();

//        for(Step step: process.getSteps()) {
//            System.out.println(step.getStepNumber());
//            System.out.println(step.getHalfDayCount());
//        }

        // Service that reads input of process dates
        ArrayList<LocalDate> processDateList = new ProcessDatesService().getProcessDateList();
        for (LocalDate date : processDateList) {
            String pattern = "MM/dd/yyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            System.out.println(date.toString());
        }

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
//        ExcelExportService.exportExcelFile(processMapping);
    }
}
