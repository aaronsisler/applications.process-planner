package process.planner;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

public class CellFormat {
    public static short getCellColor(String color) {

        // Bad colors
        // OLIVE_GREEN
        // GREEN (Too dark)
        // DARK_BLUE
        // BLUE_GREY (looks like grey)

        switch (color) {
            case "GREEN":
                return IndexedColors.LIGHT_GREEN.getIndex();
            case "PURPLE":
                return IndexedColors.LAVENDER.getIndex();
            case "YELLOW":
                return IndexedColors.YELLOW.getIndex();
            case "LIGHT_GREY":
                return IndexedColors.GREY_25_PERCENT.getIndex();
            case "DARK_GREEN":
                return IndexedColors.SEA_GREEN.getIndex();
            case "BLUE":
                return IndexedColors.CORNFLOWER_BLUE.getIndex();
            case "CORAL":
                return IndexedColors.CORAL.getIndex();
            case "DARK_BLUE":
                return IndexedColors.PALE_BLUE.getIndex();
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
