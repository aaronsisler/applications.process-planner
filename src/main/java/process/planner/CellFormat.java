package process.planner;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

public class CellFormat {
    public static short getCellColor(String color) {

        switch (color) {
            case "GREEN":
                return IndexedColors.LIGHT_GREEN.getIndex();
            case "PURPLE":
                return IndexedColors.LAVENDER.getIndex();
            case "YELLOW":
                return IndexedColors.YELLOW.getIndex();
            case "LIGHT_GREY":
                return IndexedColors.GREY_25_PERCENT.getIndex();
            case "HUNTER_GREEN":
                return IndexedColors.GREEN.getIndex();
            case "BLUE":
                return IndexedColors.BLUE.getIndex();
            case "CORAL":
                return IndexedColors.CORAL.getIndex();
            case "DARK_BLUE":
                return IndexedColors.DARK_BLUE.getIndex();
            case "GREY":
                return IndexedColors.GREY_50_PERCENT.getIndex();
            default:
                return IndexedColors.WHITE.getIndex();
        }
    }

    public static FillPatternType getFillPattern() {
        return FillPatternType.SOLID_FOREGROUND;
    }
}
