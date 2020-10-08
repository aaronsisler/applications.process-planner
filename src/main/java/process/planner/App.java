package process.planner;

import process.planner.models.Process;
import process.planner.services.ExcelExportService;
import process.planner.services.ProcessDefinitionService;
import process.planner.services.ProcessSchedulerService;
import process.planner.services.ProcessStaggerService;

public class App {
    public static void main(String[] args) {
        System.out.println("Start App");
        int processStaggerDays = 12;

        // Service that reads input of processes
        Process process = new ProcessDefinitionService().createProcess(processStaggerDays);

//        for(Step step: process.getSteps()) {
//            System.out.println(step.getFullDays());
//            System.out.println(step.getHalfDays());
//        }

        // Service that reads input of process dates
//        ArrayList<LocalDate> processDateList = new ProcessStaggerService().getProcessDateList();
        int processStaggerValue = new ProcessStaggerService().getProcessStaggerValue();
//        for (LocalDate date : processDateList) {
//            String pattern = "MM/dd/yyyy";
//            DateFormat df = new SimpleDateFormat(pattern);
//            System.out.println(date.toString());
//        }

        // Service to read Process and place into an Array
        int[][] processMapping = new ProcessSchedulerService().createProcessMapping(process, processStaggerValue);
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
    }
}
