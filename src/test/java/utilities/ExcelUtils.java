package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {

    private static String path;
    private static Workbook workbook;
    private static Sheet sheet;
    /**
     * This method will open Excel file with specific file name and sheet name.
     * @param fileName
     * @param sheetName
     */
    public static void openExcelFile(String fileName, String sheetName){
        path = "src/test/resources/testdata/"+fileName+".xlsx";

        try {
            FileInputStream file = new FileInputStream(path);
            workbook = new XSSFWorkbook(file);
            sheet = workbook.getSheet(sheetName);
        } catch (IOException e) {
            System.out.println("Excel file path is invalid");
        }
    }

    /**
     * This method returns value with provided row and cell index.
     * @param row
     * @param cell
     * @return
     */
    public static String getValue(int row, int cell){
        return sheet.getRow(row).getCell(cell).toString();
    }

    /**
     * This method sets value with provided row and cell index
     * @param row
     * @param cell
     * @param value
     */
    public static void setValue(int row, int cell, String value) throws IOException {
        int numberOfRows = sheet.getPhysicalNumberOfRows();
        Row row1;
        if(row>=numberOfRows){
            row1 = sheet.createRow(row);
        }else {
            row1 = sheet.getRow(row);
        }

        Cell cell1;
        if (cell>=row1.getPhysicalNumberOfCells()){
            cell1=row1.createCell(cell);
        }else {
            cell1=row1.getCell(cell);
        }

        cell1.setCellValue(value);

        FileOutputStream output = null;
        try{
            output=new FileOutputStream(path);
            workbook.write(output);
        } catch (IOException e) {
            System.out.println("Excel file path is invalid");
        } finally {
            assert output != null;
            output.close();
        }
    }
}
