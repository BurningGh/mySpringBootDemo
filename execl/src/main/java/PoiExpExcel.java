import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class PoiExpExcel {
    public static void main(String[] a) {
        String[] title = {"id", "name", "sex"};
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell;
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        for (int i = 1; i < 10; i++) {
            XSSFRow newRow = sheet.createRow(i);
            XSSFCell cell0 = newRow.createCell(0);
            cell0.setCellValue("a" + i);
            XSSFCell cell1 = newRow.createCell(1);
            cell1.setCellValue("user" + i);
            XSSFCell cell2 = newRow.createCell(2);
            cell2.setCellValue("男");
        }
        try {


            File file = new File("e:/poi_test.xlsx");
            file.createNewFile();
            FileOutputStream stream = FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
            System.out.println("成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
