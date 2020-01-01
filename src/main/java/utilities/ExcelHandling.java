package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

public class ExcelHandling {
	public static Object[][] getDatafromExcel(String wb, String sh){
		File file=new File(wb);
		Object[][] ob=new Object[1][1];
		try {
			FileInputStream fis=new FileInputStream(file);
			try {
				XSSFWorkbook workbook=new XSSFWorkbook(fis);
				XSSFSheet sheet=workbook.getSheet(sh);
				int rowno=sheet.getLastRowNum();
				int colno=sheet.getRow(0).getLastCellNum();
				int runstatus_colno=0;
				
				for(int k=0;k<colno;k++) {
					if(sheet.getRow(0).getCell(k).toString().equalsIgnoreCase("Run Status")) {
						runstatus_colno=k;
						break;
					}
				}
				
				HashMap<Object,Object> map=new HashMap<Object,Object>();
				for(int i=0;i<rowno;i++) {
					if(sheet.getRow(i+1).getCell(runstatus_colno).toString().equalsIgnoreCase("Yes")) {
						for(int j=0;j<colno-1;j++) {
							
							map.put(sheet.getRow(0).getCell(j).toString(), sheet.getRow(i+1).getCell(j).toString());
							
						}
						
					}
					
				}
				Assert.assertTrue(map.size()>0);
				ob[0][0]=map;
				
				
			} catch (IOException e) {
				System.out.println("Workbook not found");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		return ob;
		
//		https://reqres.in/api/users?page=2
		
		
		
	}
}
