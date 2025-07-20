package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	String path;
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	

	
	public ExcelUtility(String path) 
	{
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException // Logic to get row count from the Excel file
	{
		fi = new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum(); // Last row number gives the count of rows
		workbook.close();
		fi.close();
		
		return rowcount; // Placeholder return value
	}
	
	public int getColumnCount(String sheetName, int rowNum) throws IOException // Logic to get column count from the Excel file
	{
		fi = new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int columncount=sheet.getRow(rowNum).getLastCellNum(); // Last cell number gives the count of columns
		workbook.close();
		fi.close();
		
		return columncount; // Placeholder return value
	}
	
	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException // Logic to get cell data from the Excel file
	{
		fi = new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		
		try
		{
		data= formatter.formatCellValue(cell); // Using DataFormatter to get the cell value as a string
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data; // Returning the cell data as a string
			
	}
		
	
	
	
	
}	
	
	
