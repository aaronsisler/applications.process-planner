package process.planner.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessReaderFromFile {
    public ArrayList<int[]> getProcessDefinition() {
        ArrayList<int[]> stepDefinitionList = new ArrayList<>();
        ArrayList<String> rawStepDefinitionList = getRawStepDefinitions("process-one-definition.txt");

        for(String rawStepDef : rawStepDefinitionList) {
            String[] rawEmployeeCountsPerHalfDay = rawStepDef.split(",");
            int[] parsedEmployeeCountsPerHalfDay = new int[rawEmployeeCountsPerHalfDay.length];
            for(int i = 0; i < rawEmployeeCountsPerHalfDay.length; i++) {
                parsedEmployeeCountsPerHalfDay[i] = Integer.parseInt(rawEmployeeCountsPerHalfDay[i]);
            }
            stepDefinitionList.add(parsedEmployeeCountsPerHalfDay);
        }
        return stepDefinitionList;
    }

    private ArrayList<String> getRawStepDefinitions(String filePathname) {
        ArrayList<String> rawStepDefinitionList = new ArrayList<>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String filePath = classLoader.getResource(filePathname).getPath();
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String rawStep = scanner.nextLine();
                rawStepDefinitionList.add(rawStep);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePathname);
            e.printStackTrace();
        }
        return rawStepDefinitionList;
    }
}