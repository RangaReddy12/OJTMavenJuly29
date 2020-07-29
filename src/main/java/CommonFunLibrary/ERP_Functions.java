package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

public class ERP_Functions {
public static WebDriver driver;
//method launch url and browser
public static String verifyUrl(String url)throws Throwable
{
	String res=null;
System.setProperty("webdriver.chrome.driver", "D:\\6OclockOJT\\ERP_StockAccounting\\CommonDrivers\\chromedriver.exe");
driver=new ChromeDriver();
driver.get(url);
driver.manage().window().maximize();
Thread.sleep(4000);
if(driver.findElement(By.name("btnsubmit")).isDisplayed())
{
	res="Application Launch successfully";
}
else
{
	res="Application Launch Fail";	
}
return res;
}
//method for Login
public static String verifyLogin(String username,String password)throws Throwable
{
	String res=null;
	WebElement user =driver.findElement(By.name("username"));
	user.clear();
	user.sendKeys(username);
	Thread.sleep(4000);
	WebElement pass =driver.findElement(By.name("password"));
	pass.clear();
	pass.sendKeys(password);
	Thread.sleep(4000);
	driver.findElement(By.name("btnsubmit")).click();
	Thread.sleep(4000);
	if(driver.findElement(By.id("mi_logout")).isDisplayed())
	{
		res="Login success";
	}
	else
	{
		res="Login Fail";
	}
	return res;
}
//method supplier creation
public static String verifySupplier(String sname,String address,String city,
String country,String cperson,String pnumber,String mail,String mnumber,String notes)
throws Throwable{
	String res=null;
driver.findElement(By.xpath("/html[1]/body[1]/div[2]/div[2]/div[1]/div[1]/ul[1]/li[3]/a[1]")).click();
Thread.sleep(4000);
driver.findElement(By.xpath("/html[1]//div[1]/div[1]/div[1]/div[1]/a[1]/span[1]")).click();
Thread.sleep(4000);
String expected=driver.findElement(By.name("x_Supplier_Number")).getAttribute("value");
driver.findElement(By.name("x_Supplier_Name")).sendKeys(sname);
driver.findElement(By.name("x_Address")).sendKeys(address);
driver.findElement(By.name("x_City")).sendKeys(city);
driver.findElement(By.name("x_Country")).sendKeys(country);
driver.findElement(By.name("x_Contact_Person")).sendKeys(cperson);
driver.findElement(By.name("x_Phone_Number")).sendKeys(pnumber);
driver.findElement(By.name("x__Email")).sendKeys(mail);
driver.findElement(By.name("x_Mobile_Number")).sendKeys(mnumber);
driver.findElement(By.name("x_Notes")).sendKeys(notes);
driver.findElement(By.name("btnAction")).sendKeys(Keys.ENTER);
Thread.sleep(4000);
driver.findElement(By.xpath("//button[text()='OK!']")).click();
Thread.sleep(4000);
driver.findElement(By.xpath("(//button[text()='OK'])[6]")).click();
Thread.sleep(4000);
if(!driver.findElement(By.id("psearch")).isDisplayed())
	//if search text is not displayed click on search panel
driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon']")).click();
Thread.sleep(4000);
driver.findElement(By.id("psearch")).clear();
//enter expected data
driver.findElement(By.id("psearch")).sendKeys(expected);
//click on search button
driver.findElement(By.name("btnsubmit")).click();
Thread.sleep(4000);
//capture snumber from stable
String actual=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
if(actual.equals(expected))
{
	res="pass";
	System.out.println(expected+"   "+actual);
}
else
{
	res="fail";
	System.out.println(expected+"   "+actual);
}
return res;
}
}





