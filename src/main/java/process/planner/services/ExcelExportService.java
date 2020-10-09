package process.planner.services;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExcelExportService {
    public static void exportExcelFile(int[][] processMapping) {
        Workbook wb = new HSSFWorkbook();

        // Creating Sheets using sheet object
        Sheet sheet1 = wb.createSheet("Schedule");
        int rowCounter = 0;
        for (int[] row : processMapping) {
            Row tempRow = sheet1.createRow(rowCounter);

            int columnCounter = 0;
            for (int columnValue : row) {
                tempRow.createCell(columnCounter).setCellValue(columnValue);
                columnCounter++;
            }
            rowCounter++;
        }
        createExcelFile(wb);
    }

    private static void createExcelFile(Workbook wb) {
        try {
            // An output stream accepts output bytes and sends them to sink.
            OutputStream fileOut = new FileOutputStream("Schedule.xlsx");
            wb.write(fileOut);
            System.out.println("Sheets Has been Created successfully");
        } catch (Exception e) {
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        }
    }
}
