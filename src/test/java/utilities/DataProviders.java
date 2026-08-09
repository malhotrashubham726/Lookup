package utilities;
import java.io.IOException;

import org.testng.annotations.*;

public class DataProviders {

	@DataProvider(name="LoginData")
	public String[][] getLoginData() throws IOException {
//		String [][] data= {
//				{"abcpavanol123@gmail.com", "test@123"},
//				{"lakshmi@gmail.com", "Laxmi"},
//				{"laksh@gmail.com", "Lakshmi"},
//				{"abc123@gmail.com", "test@123"},
//				{"laks@gmail.com", "xyz"}
//				
//		};
		
		ExcelUtility utils=new ExcelUtility(System.getProperty("user.dir") + "//testData//LoginData.xlsx");
		int rows=utils.getRowCount("Sheet1");
		int cols=utils.getCellCount("Sheet1", 0);
		
		String loginData[][]=new String[rows][cols];

		for(int i=1; i<=rows; i++) {
			for(int j=0; j<cols-1; j++) {
				loginData[i-1][j]=utils.getCellData("Sheet1", i, j);
			}
			loginData[i-1][2]=String.valueOf(i);
		}
		
		
		return loginData;
	}
}
