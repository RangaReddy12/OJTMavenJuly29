package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class DataDriven {
WebDriver driver;
String inputpath="D:\\6OclockOJT\\ERP_StockAccounting\\TestInput\\TestData.xlsx";
String outputpath="D:\\6OclockOJT\\ERP_StockAccounting\\TestOutput\\DataDriven.xlsx";
@BeforeTest
public void setUp()throws Throwable
{
	String launch=ERP_Functions.verifyUrl("http://projects.qedgetech.com/webapp");
	Reporter.log(launch,true);
	String loginresults=ERP_Functions.verifyLogin("admin", "master");
	Reporter.log(loginresults,true);
}
@Test
public void supplier()throws Throwable
{
	//access excel methods
	ExcelFileUtil xl =new ExcelFileUtil(inputpath);
	//count no of rows in a sheet
	int rc=xl.rowCount("Supplier");
	Reporter.log("No of rows are::"+rc,true);
	for(int i=1;i<=rc;i++)
	{
		//read all cell from supplers sheet
	String sname=xl.getCellData("Supplier", i, 0);
	String address=xl.getCellData("Supplier", i, 1);
	String city=xl.getCellData("Supplier", i, 2);
	String country=xl.getCellData("Supplier", i, 3);
	String cperson=xl.getCellData("Supplier", i, 4);
	String pnumber=xl.getCellData("Supplier", i, 5);
	String mail=xl.getCellData("Supplier", i, 6);
	String mnumber=xl.getCellData("Supplier", i, 7);
	String notes=xl.getCellData("Supplier", i, 8);
String Results=ERP_Functions.verifySupplier(sname, address, city, country, cperson, pnumber, mail, mnumber, notes);
Reporter.log(Results,true);
xl.setCellData("Supplier", i, 9, Results, outputpath);
	}
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}














