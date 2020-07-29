package CommonFunLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {
	public static WebDriver driver;
	//method for start browser
	public static WebDriver startBrowser()throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			System.out.println("Executing on Chrome Browser");
			System.setProperty("webdriver.chrome.driver", "D:\\6OclockOJT\\ERP_Maven\\CommonDrivers\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "D:\\6OclockOJT\\ERP_Maven\\CommonDrivers\\geckodriver.exe");
			driver= new FirefoxDriver();
		}
		else 
		{
			System.out.println("Browser key value not matching");
		}
		return driver;
	}
	//method for open application
	public static void openApplication(WebDriver driver)throws Throwable
	{
		driver.get(PropertyFileUtil.getValueForKey("Url"));
		driver.manage().window().maximize();
	}
	//method for waitForElement
	public static void waitForElement(WebDriver driver,String locatortype,String locatorvalue,
			String waitime)
	{
		WebDriverWait myWait= new WebDriverWait(driver, Integer.parseInt(waitime));
		if(locatortype.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));	
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
		}
		else
		{
			System.out.println("Waitfor element is failed");
		}
	}
	//method for typeAction
	public static void typeAction(WebDriver driver,String locatortype,String locatorvalue,
			String testdata)
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).clear();
			driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).clear();
			driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
		}
		else
		{
			System.out.println("typeaction is failed");
		}
	}
	//method for clickAction
	public static void clickAction(WebDriver driver,String locatortype,String locatorvalue)
	{
		if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).sendKeys(Keys.ENTER);
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).click();
		}
		else
		{
			System.out.println("Unable to execute clickaction");
		}
	}
	//method for close browser
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	//method date generate
	public static String generateDate()
	{
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
		return sdf.format(d);
	}
	//method for capture data
public static void captureData(WebDriver driver,String locatorname,String locatorvalue)
throws Throwable{
	String snumber="";
	if(locatorname.equalsIgnoreCase("id"))
	{
	snumber=driver.findElement(By.id(locatorvalue)).getAttribute("value");	
	}
	else if(locatorname.equalsIgnoreCase("name"))
	{
		snumber=driver.findElement(By.name(locatorvalue)).getAttribute("value");
	}
	else if(locatorname.equalsIgnoreCase("xpath"))
	{
		snumber=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
	}
	//writing snumber into notepad .txt under capturedata folder
	FileWriter fw= new FileWriter("D:\\6OclockOJT\\ERP_Maven\\CaptureData\\snumber.txt");
	BufferedWriter bw= new BufferedWriter(fw);
	bw.write(snumber);
	bw.flush();
	bw.close();
}
//method for supplier table
public static void tableValidation(WebDriver driver,String testdata)
throws Throwable{
//read snumber from note pad
	FileReader fr=new FileReader("D:\\6OclockOJT\\ERP_Maven\\CaptureData\\snumber.txt");
	BufferedReader br= new BufferedReader(fr);
	String exp_data=br.readLine();
	//convert testdata into integer
	int columdata=Integer.parseInt(testdata);
if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtext"))).isDisplayed())
//if search text box not displayed click on search panel
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchpanel"))).click();
Thread.sleep(4000);
//enter exp_data into serach textbox
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtext"))).sendKeys(exp_data);
Thread.sleep(4000);
//click on search button
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchbutton"))).click();
Thread.sleep(4000);
//store supplier table into webelement
WebElement table= driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("stable")));
//get collection of rows in a table
List<WebElement> rows=table.findElements(By.tagName("tr"));
for(int i=1;i<rows.size();i++)
{
	//capture six cell data
String act_data=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+columdata+"]/div/span/span")).getText();
Assert.assertEquals(exp_data, act_data,"Supplier number not matching");
System.out.println(act_data+"    "+exp_data);
break;
}
}
//method for stockitems
public static void stockCategories(WebDriver driver)throws Throwable
{
	Actions ac= new Actions(driver);
	ac.moveToElement(driver.findElement(By.linkText("Stock Items"))).perform();
	Thread.sleep(4000);
	ac.moveToElement(driver.findElement(By.xpath("(//a[contains(text(),'Stock Categories')])[2]"))).click();
	ac.build().perform();
	Thread.sleep(4000);
}
//method for stock table
public static void stockValidation(WebDriver driver,String testdata)throws Throwable
{
if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtext1"))).isDisplayed())
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchpanel1"))).click();
Thread.sleep(4000);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtext1"))).sendKeys(testdata);
driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchbutton1"))).click();
Thread.sleep(4000);
WebElement table=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("stocktable")));
//get collection of rows in a table
List<WebElement>rows=table.findElements(By.tagName("tr"));
for(int i= 1; i<rows.size();i++)
{
	//capture cell data
String actual=driver.findElement(By.xpath("//table[@id='tbl_a_stock_categorieslist']/tbody/tr[1]/td[4]/div/span/span")).getText();
Assert.assertEquals(testdata, actual,"Stock is not added");
System.out.println(testdata+"    "+actual);
break;
}
}
}










