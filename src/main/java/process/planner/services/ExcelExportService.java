package process.planner.services;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import process.planner.CellFormat;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class ExcelExportService {
    public static void exportExcelFile(int[][] processMapping, int[] employeeCountsPerDay, String[] stepsColorScheme) {
        XSSFWorkbook wb = new XSSFWorkbook();

        // Creating Sheets using sheet object
        XSSFSheet sheet = wb.createSheet("Schedule");
        createHeaderRows(sheet);
        int rowCounterPostHeaders = 2;
        for (int i = 0; i < processMapping.length; i++) {
            int[] row = processMapping[i];

            if (row[0] == -2) {
                createSeparatorRow(sheet, row.length, rowCounterPostHeaders);
            } else {
                String stepColor = stepsColorScheme[i];
                createValueRow(sheet, row, stepColor, rowCounterPostHeaders);
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

    private static void createValueRow(XSSFSheet sheet, int[] rowData, String stepColor, int rowPosition) {
        XSSFRow tempRow = sheet.createRow(rowPosition);
        CellStyle cellStyle = createCellStyle(tempRow, stepColor);

        for (int i = 0; i < rowData.length; i++) {
            if (rowData[i] == -1) {
                tempRow.createCell(i).setCellValue("");
            } else {
                XSSFCell tempCell = tempRow.createCell(i);
                tempCell.setCellValue(rowData[i]);
                tempCell.setCellStyle(cellStyle);
            }
        }
    }

    private static CellStyle createCellStyle(XSSFRow row, String cellColor) {
        CellStyle cellStyle = row.getSheet().getWorkbook().createCellStyle();
        cellStyle.setFillForegroundColor(CellFormat.getCellColor(cellColor));
        cellStyle.setFillPattern(CellFormat.getFillPattern());

        return cellStyle;
    }

    private static void createSeparatorRow(XSSFSheet sheet, int columnCount, int rowPosition) {
        XSSFRow blankRow = sheet.createRow(rowPosition);

        for (int i = 0; i < columnCount; i++) {
            XSSFCell tempDayCell = blankRow.createCell(i);
            tempDayCell.setCellValue("");
            tempDayCell.setCellStyle(createCellStyle(blankRow,"GREY"));
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
