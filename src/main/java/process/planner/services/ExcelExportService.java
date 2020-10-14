package process.planner.services;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExcelExportService {
    public static void exportExcelFile(int[][] processMapping, int[] employeeCountsPerDay) {
        XSSFWorkbook wb = new XSSFWorkbook();

        // Creating Sheets using sheet object
        XSSFSheet sheet = wb.createSheet("Schedule");
        createHeaderRows(sheet);
        int rowCounterPostHeaders = 2;
        for (int[] row : processMapping) {
            if (row[0] == -2) {
                createSeparatorRow(sheet, row.length, rowCounterPostHeaders);
            } else {
                createValueRow(sheet, row, rowCounterPostHeaders);
            }

            rowCounterPostHeaders++;
        }

        createSeparatorRow(sheet, employeeCountsPerDay.length, rowCounterPostHeaders);
        createEmployeeCountFooter(sheet, employeeCountsPerDay, rowCounterPostHeaders + 1);

        for (int i = 0; i < processMapping[0].length; i++) {
            sheet.autoSizeColumn(i);
        }
        createExcelFile(wb);
    }

    private static void createValueRow(XSSFSheet sheet, int[] rowData, int rowPosition) {
        XSSFRow tempRow = sheet.createRow(rowPosition);

        for (int i = 0; i < rowData.length; i++) {
            if (rowData[i] == -1) {
                tempRow.createCell(i).setCellValue("");
            } else {
                tempRow.createCell(i).setCellValue(rowData[i]);
            }
        }
    }

    private static void createSeparatorRow(XSSFSheet sheet, int columnCount, int rowPosition) {
        XSSFRow blankRow = sheet.createRow(rowPosition);
        CellStyle blankRowCellStyle = blankRow.getSheet().getWorkbook().createCellStyle();
        blankRowCellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        blankRowCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        for (int i = 0; i < columnCount; i++) {
            XSSFCell tempDayCell = blankRow.createCell(i);
            tempDayCell.setCellValue("");
            tempDayCell.setCellStyle(blankRowCellStyle);
        }
    }

    private static void createEmployeeCountFooter(XSSFSheet sheet, int[] employeeCountsPerDay, int rowPosition) {
        int columnCounter = 0;
        XSSFRow tempRow = sheet.createRow(rowPosition);
        for (int employeeCount : employeeCountsPerDay) {
            String cellValue = employeeCount == 0 ? "" : Integer.toString(employeeCount);
            tempRow.createCell(columnCounter).setCellValue(cellValue);
            columnCounter++;
        }
    }

    private static void createHeaderRows(XSSFSheet sheet) {
        XSSFRow monthRow = sheet.createRow(0);
        XSSFRow dayRow = sheet.createRow(1);
        CellStyle cellStyle = monthRow.getSheet().getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        int[] daysInMonths = DatesService.getDaysInEachMonth(2021);
        int monthCounter = 1;
        int columnCounter = 0;
        int mergedCellsMonthBegin = 0;
        for (int daysInMonth : daysInMonths) {
            int dayCounter = 1;
            for (int i = 0; i < daysInMonth * 2; i++) {
                XSSFCell tempMonthCell = monthRow.createCell(columnCounter);
                tempMonthCell.setCellValue(DatesService.getMonthName(monthCounter));
                tempMonthCell.setCellStyle(cellStyle);
                XSSFCell tempDayCell = dayRow.createCell(columnCounter);
                tempDayCell.setCellValue(dayCounter);
                tempDayCell.setCellStyle(cellStyle);
                if (i % 2 == 1) {
                    sheet.addMergedRegion(new CellRangeAddress(1, 1, columnCounter - 1, columnCounter));
                    dayCounter++;
                }
                columnCounter++;
            }
            sheet.addMergedRegion(new CellRangeAddress(0, 0, mergedCellsMonthBegin, columnCounter - 1));
            mergedCellsMonthBegin = columnCounter;
            monthCounter++;
        }
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
