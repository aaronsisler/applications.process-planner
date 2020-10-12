package process.planner.services;

import process.planner.utils.DateReaderFromFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class DatesService {

    public static ArrayList<LocalDate> getProcessDateList(String filepath) {
        ArrayList<LocalDate> dateList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH);

        ArrayList<String> rawDateList = new DateReaderFromFile().getDates(filepath);

        for (String rawDate : rawDateList) {
            LocalDate date = LocalDate.parse(rawDate, formatter);

            dateList.add(date);
        }

        for (LocalDate date: dateList ) {
            System.out.println(date.toString());
        }

        Collections.sort(dateList);
        for (LocalDate date: dateList ) {
            System.out.println(date.toString());
        }

        return dateList;
    }

    public static long getStartDateEpochDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH);
        LocalDate date = LocalDate.parse("2021/01/01", formatter);

        return date.toEpochDay();
    }

    public static int getNumberOfDaysInYear(int year) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH);
        String beginningOfYear = String.format("%d/01/01", year);
        String endOfYear = String.format("%d/12/31", year);
        LocalDate begindate = LocalDate.parse(beginningOfYear, formatter);
        LocalDate enddate = LocalDate.parse(endOfYear, formatter);

        return (int) (enddate.toEpochDay() - begindate.toEpochDay() + 1); // Plus one at the end to account for the 01/01/XXXX day
    }

    public static int getDaysInMonth(String rawMonth, int year) {
        String month = rawMonth.length() == 1 ? String.format("0%s", rawMonth) : rawMonth;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH);
        String rawDate = String.format("%d/%s/01", month, year);
        LocalDate date = LocalDate.parse(rawDate, formatter);

        return date.lengthOfMonth();
    }

    public static int[] getDaysInEachMonth(int year) {
        int[] daysInMonths = new int[12];
        for (int i = 0; i < 12; i++) {
            daysInMonths[i] = DatesService.getDaysInMonth(Integer.toString(i + 1), year);
        }

        return daysInMonths;
    }

    public static String getMonthName(int rawMonth) {
        String month = rawMonth >= 10 ? Integer.toString(rawMonth) : String.format("0%d", rawMonth);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH);
        String rawDate = String.format("%d/%s/01", month, 1970);
        LocalDate date = LocalDate.parse(rawDate, formatter);

        return date.getMonth().toString();
    }
}
