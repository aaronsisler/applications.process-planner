package process.planner.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class DateReaderFromFile {
    public ArrayList<String> getDates(String filePathname) {
        ArrayList<String> dateList = new ArrayList<>();
        try {
            File file = new File(filePathname);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String rawDate = scanner.nextLine();
                dateList.add(rawDate);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePathname);
            e.printStackTrace();
        }
        return dateList;
    }
}