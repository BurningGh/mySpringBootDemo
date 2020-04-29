import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;

public class PoiReadExcel {
    public static void main(String[] args) {
        try {
            File file = new File("e:/poi_test.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(FileUtils.openInputStream(file));
//          workbook.getSheet("Sheet0");
            HSSFSheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 0; i < lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                short lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    HSSFCell cell = row.getCell(j);
                    String value = cell.getStringCellValue();
                    System.out.print(value+" ");
                }
                System.out.println("");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
