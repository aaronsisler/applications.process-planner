package process.planner.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class FolderReader {
    private static final String DATE_FILE_PREFIX_NAME = "process-dates-";
    private static final String DEFINITION_PREFIX_NAME = "process-definition-";
    private static final String USER_DIRECTORY = System.getProperty("user.dir") + "/process-files";

    public static ArrayList<String> retrieveDateFiles() {
        ArrayList<String> dateFileList = new ArrayList<>();
        File folder = new File(USER_DIRECTORY);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().startsWith(DATE_FILE_PREFIX_NAME)) {
                dateFileList.add(file.getAbsoluteFile().toString());
            }
        }

        Collections.sort(dateFileList);

        return dateFileList;
    }

    public static ArrayList<String> retrieveProcessDefinitionFiles() {
        ArrayList<String> processDefinitionFileList = new ArrayList<>();
        File folder = new File(USER_DIRECTORY);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile() && file.getName().startsWith(DEFINITION_PREFIX_NAME)) {
                processDefinitionFileList.add(file.getAbsoluteFile().toString());
            }
        }

        Collections.sort(processDefinitionFileList);

        return processDefinitionFileList;
    }
}
