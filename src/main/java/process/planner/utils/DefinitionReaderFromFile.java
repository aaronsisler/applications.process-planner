package process.planner.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DefinitionReaderFromFile {
    public ArrayList<String> getRawStepDefinitions(String filepath) {
        ArrayList<String> rawStepDefinitionList = new ArrayList<>();
        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String rawData = scanner.nextLine().trim();
                if (!rawData.isEmpty()) {
                    String rawStep = rawData.split("#")[0].trim();
                    rawStepDefinitionList.add(rawStep);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filepath);
            e.printStackTrace();
        }

        return rawStepDefinitionList;
    }
}