package process.planner.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DateReaderFromFile {
    public Map<String, String> getDates(String filePathname) throws IOException {
        Map<String, String> dateList = new HashMap<String, String>() {
        };
        try {
            File file = new File(filePathname);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String rawData = scanner.nextLine().trim();
                if (rawData.isEmpty()) {
                    continue;
                }

                if (!rawData.contains(",")) {
                    dateList.put(rawData.trim(), "");
                    continue;
                }
                String[] rawSplitData = rawData.split(",");

                if (rawSplitData[0].trim().isEmpty() || rawSplitData[1].trim().isEmpty()) {
                    throw new IOException(String.format("Invalid data in file: %s", filePathname));
                }
                dateList.put(rawSplitData[0].trim(), rawSplitData[1].trim());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePathname);
            e.printStackTrace();
        }
        return dateList;
    }
}