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
        Row row = sheet1.createRow(0);
        row.createCell(0).setCellValue("Taco");
        row.createCell(1).setCellValue("Bell");
        createExcelFile(wb);
    }

    private static void main(Workbook wb) {

    }

    private static void createExcelFile(Workbook wb) {
        try {
            // An output stream accepts output bytes and sends them to sink.
            OutputStream fileOut = new FileOutputStream("Geeks.xlsx");
            wb.write(fileOut);
            System.out.println("Sheets Has been Created successfully");
        } catch (Exception e) {
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
        }
    }
}
