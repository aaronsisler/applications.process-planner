package project.planner;

import project.planner.models.Process;
import project.planner.models.Step;
import project.planner.services.ProcessDateService;
import project.planner.services.ProcessDefinitionService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        System.out.println("Start App");

        // Service that reads input of processes
        Process process = new ProcessDefinitionService().createProcess();

        for(Step step: process.getSteps()) {
            System.out.println(step.getFullDays());
            System.out.println(step.getHalfDays());
        }

        // Service that reads input of process dates
        ArrayList<LocalDate> processDateList = new ProcessDateService().createProcessDateList();
        for (LocalDate date : processDateList) {
            String pattern = "MM/dd/yyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            System.out.println(date.toString());
        }

        // Service to read Process and place into an Array
        // Service to take Array and make an Excel sheet
    }
}
