package Restassured;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData 
{

	static  XSSFWorkbook workbook;
	static XSSFSheet sheet;


	public ExcelData(String excelpath,String sheetName)
	{

		try {
			workbook =new XSSFWorkbook(excelpath);
			sheet =workbook.getSheet(sheetName);

		}catch (Exception exp)
		{
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}



	public static String getCellData(int RowNum,int ColNum)
	{
		DataFormatter formatter = new DataFormatter();
		Object value =formatter.formatCellValue(sheet.getRow(RowNum).getCell(ColNum));
		System.out.println(value);
		return (String) value;


	}



	public static void getRowCount()
	{

		int rowcount =sheet.getPhysicalNumberOfRows();
		System.out.println("No of Rows:"+rowcount);

	}




}
