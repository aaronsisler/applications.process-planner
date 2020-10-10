package process.planner.services;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExcelExportService {
    public static void exportExcelFile(int[][] processMapping) {
        XSSFWorkbook wb = new XSSFWorkbook();

        // Creating Sheets using sheet object
        XSSFSheet sheet = wb.createSheet("Schedule");
        createMonthsRow(sheet);
        int rowCounter = 2;
        for (int[] row : processMapping) {
            XSSFRow tempRow = sheet.createRow(rowCounter);

            int columnCounter = 0;
            for (int columnValue : row) {
                tempRow.createCell(columnCounter).setCellValue(columnValue);
                columnCounter++;
            }
            rowCounter++;
        }
        createExcelFile(wb);
    }

    private static void createMonthsRow(XSSFSheet sheet) {
        XSSFRow monthRow = sheet.createRow(0);
        XSSFRow dayRow = sheet.createRow(1);
        int[] daysInMonths = DatesService.getDaysInEachMonth(2021);
        int monthCounter = 1;
        int columnCounter = 0;
        for (int daysInMonth : daysInMonths) {
            int dayCounter = 1;
            for (int i = 0; i < daysInMonth * 2; i++) {
                monthRow.createCell(columnCounter).setCellValue(monthCounter);
                dayRow.createCell(columnCounter).setCellValue(dayCounter);
                if(i % 2 == 1) {
                    dayCounter++;
                }
                columnCounter++;
            }
            monthCounter++;
        }
    }

    private static void createDaysRow() {
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
