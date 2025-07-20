package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class dataProvider {
	
	//Data Provider 1
	/*
	 ............ 
	 ...........
	 */
	
	
	//Data Provider 2
	
	@DataProvider(name = "DP")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\testData.xlsx";	//path of the source excel file having test data
		ExcelUtility exlUtil=new ExcelUtility(path);	//creating object of Excel Utility class
		
		int totalrows=exlUtil.getRowCount("Sheet1");	//getting total number of rows in the sheet
		int totalcols=exlUtil.getColumnCount("Sheet1",1);	//getting total number of columns in the sheet
		
		String 	data[][]=new String[totalrows][totalcols];	//creating two dimensional array to hold the data from excel sheet
		for (int i = 1; i <= totalrows; i++) // starting from 1 to skip header row
		{
			for (int j = 0; j < totalcols; j++) 
			{
				data[i - 1][j] = exlUtil.getCellData("Sheet1", i, j); // getting data from excel sheet and storing in the array
																		
			}
		}
		
		
		return data;	// returning the data array to be used in test methods;
	}

	//Data Provider 3
	
	//Data Provider 4
	
	
}
