package GenerateImage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    
    public static int getRowcount(String xfile, String xlsheet) throws IOException {
        int rowcount;
        try (FileInputStream fi = new FileInputStream(xfile); 
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet ws = wb.getSheet(xlsheet);
            rowcount = ws.getLastRowNum();
        }
        return rowcount;
    }

    public static int getcellcount(String xlfile, String xlsheet, int rownum) throws IOException {
        int cellcount;
        try (FileInputStream fi = new FileInputStream(xlfile); 
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet ws = wb.getSheet(xlsheet);
            XSSFRow row = ws.getRow(rownum);
            cellcount = row.getLastCellNum();
        }
        return cellcount;
    }

    public static String getcelldata(String xlfile, String xlsheet, int rownum, int column) throws IOException {
        String data = "";
        try (FileInputStream fi = new FileInputStream(xlfile); 
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet ws = wb.getSheet(xlsheet);
            XSSFRow row = ws.getRow(rownum);
            XSSFCell cell = row.getCell(column);
            data = (cell != null) ? cell.toString() : "";
        }
        return data;
    }

    public static void setcelldata(String xlfile, String xlsheet, int rownum, int column, String data) throws IOException {
        try (FileInputStream fi = new FileInputStream(xlfile); 
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet ws = wb.getSheet(xlsheet);
            XSSFRow row = ws.getRow(rownum);
            XSSFCell cell = row.createCell(column);
            cell.setCellValue(data);
            try (FileOutputStream fo = new FileOutputStream(xlfile)) {
                wb.write(fo);
            }
        }
    }

    public static void fillgreencolor(String xlfile, String xlsheet, int rownum, int column) throws IOException {
        try (FileInputStream fi = new FileInputStream(xlfile); 
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet ws = wb.getSheet(xlsheet);
            XSSFRow row = ws.getRow(rownum);
            XSSFCell cell = row.getCell(column);
            CellStyle style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);
            try (FileOutputStream fo = new FileOutputStream(xlfile)) {
                wb.write(fo);
            }
        }
    }

    public static void fillredcolor(String xlfile, String xlsheet, int rownum, int column) throws IOException {
        try (FileInputStream fi = new FileInputStream(xlfile); 
             XSSFWorkbook wb = new XSSFWorkbook(fi)) {
            XSSFSheet ws = wb.getSheet(xlsheet);
            XSSFRow row = ws.getRow(rownum);
            XSSFCell cell = row.getCell(column);
            CellStyle style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);
            try (FileOutputStream fo = new FileOutputStream(xlfile)) {
                wb.write(fo);
            }
        }
    }
}
