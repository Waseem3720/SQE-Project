package test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryDataReader {

    public static List<Object[]> readLibraryTestData(String filePath) throws IOException {
        List<Object[]> testData = new ArrayList<>();

        // Open the Excel file
        try (FileInputStream file = new FileInputStream(new File(filePath))) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate over rows (skip the header row)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                // Retrieve cell data
                String bookId = getCellValueAsString(row.getCell(0));
                String title = getCellValueAsString(row.getCell(1));
                String author = getCellValueAsString(row.getCell(2));
                boolean isAvailable = getCellValueAsBoolean(row.getCell(3));
                String memberId = getCellValueAsString(row.getCell(4));
                String memberName = getCellValueAsString(row.getCell(5));

                // Add data to list
                testData.add(new Object[]{bookId, title, author, isAvailable, memberId, memberName});
            }
        }
        return testData;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    private static boolean getCellValueAsBoolean(Cell cell) {
        if (cell == null) return false;
        if (cell.getCellType() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            return Boolean.parseBoolean(cell.getStringCellValue().trim());
        }
        return false;
    }
}
