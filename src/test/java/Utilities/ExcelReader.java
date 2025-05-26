package Utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<String> data;

    public static void excelData(String excelFileName, String sheetName) throws IOException {
        String excelPath = "./src/test/resources/TestData/"+excelFileName+".xlsx";
        FileInputStream fi = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fi);
        XSSFSheet sheet = wb.getSheet(sheetName);
        int rows = sheet.getLastRowNum();
        int column = sheet.getRow(0).getLastCellNum();
        data = new ArrayList<>();
        for (int i=1; i<=rows; i++){
            XSSFRow row = sheet.getRow(i);
            for (int j=0; j<=column-1; j++){
                XSSFCell cell = row.getCell(j);
                DataFormatter format = new DataFormatter();
                String value = format.formatCellValue(cell);
                data.add(value);
            }
        }
    }
}
