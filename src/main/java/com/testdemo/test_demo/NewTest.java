package com.testdemo.test_demo;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;


public class NewTest {

  
public WebDriver driver;

	
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", "E:\\tools\\geckodriver.exe");
		driver= new FirefoxDriver();
	}
	
	
	@Test
	public void openurl() {
		System.out.println("opening url");
		driver.get("http://automationpractice.com/index.php");
	}
	
	@Test(priority=1)
	public void register() throws InterruptedException {
		
		System.out.println("heading to signin");
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email_create")).sendKeys("beera@gmail.com");
		driver.findElement(By.id("SubmitCreate")).click();
		
		Thread.sleep(5000);
		
	}
	
	@Test(priority=2)
	public void details() {
		
		String actualurl="http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation";
		String currenturl=driver.getCurrentUrl();
		
		if(actualurl.equals(currenturl)){
			System.out.println("Entering registration details..");
			driver.findElement(By.id("id_gender2")).click();
			driver.findElement(By.id("customer_firstname")).sendKeys("Alex");
			driver.findElement(By.id("customer_lastname")).sendKeys("Beera");
			driver.findElement(By.id("passwd")).sendKeys("beera");
			driver.findElement(By.id("firstname")).sendKeys("Alex");
			driver.findElement(By.id("lastname")).sendKeys("Beera");
			driver.findElement(By.id("address1")).sendKeys("K.R.Puram");
			driver.findElement(By.id("city")).sendKeys("Nagercoil");
			Select drpstate=new Select(driver.findElement(By.name("id_state")));
			drpstate.selectByVisibleText("Florida");
			driver.findElement(By.id("postcode")).sendKeys("32004");
			Select drpcountry=new Select(driver.findElement(By.name("id_country")));
			drpcountry.selectByVisibleText("United States");
			driver.findElement(By.id("phone_mobile")).sendKeys("1234567890");
			driver.findElement(By.id("alias")).sendKeys("Chinnathurai");
			driver.findElement(By.id("submitAccount")).click();
			driver.findElement(By.className("logout")).click();
		}else {
			System.out.println("Account registered already..");
		}
		
		
		
	}
	
	@Test(priority=3)
	@Parameters({"username","password"})
	public void login(String username, String password) throws InterruptedException {
		
		System.out.println("entering login details..");
		
		driver.findElement(By.className("login")).click();
		
		if(username.equals("beera@gmail.com")&&password.equals("beera")) {
			System.out.println("Login successful");
		driver.findElement(By.id("email")).sendKeys("beera@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("beera");
		driver.findElement(By.id("SubmitLogin")).click();
		}
		else {
			System.out.println("Login failed..");
		}
		Thread.sleep(5000);
	}
	
	@Test(priority=4)
	public void search() {
		
		System.out.println("Searching item...");
		driver.findElement(By.id("search_query_top")).sendKeys("dresses");
		driver.findElement(By.name("submit_search")).click();
	}
	
	@Test(priority=5)
	public void addToCart() throws InterruptedException {
		
		System.out.println("Adding items to cart");
		driver.findElement(By.xpath("//div[@id='center_column']/ul/li/div/div/div/a/img")).click();		  
		  
		 driver.findElement(By.id("quantity_wanted")).sendKeys("2");
		 Select drpsize=new Select(driver.findElement(By.id("group_1")));
			drpsize.selectByVisibleText("M");
			driver.findElement(By.xpath("//p[@id='add_to_cart']/button/span")).click();
			System.out.println("sigining out...");
		  driver.findElement(By.linkText("Sign out")).click();
		Thread.sleep(5000);
	}
	
	@AfterTest
	public void afterTest() {
		driver.close();
	}
}
