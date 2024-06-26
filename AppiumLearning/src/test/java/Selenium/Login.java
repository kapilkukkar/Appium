package Selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class Login 
{
	public String base_url="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	public WebDriver driver;
	
	
	@BeforeTest
	public void setup()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(base_url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void test_01()
	{
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin23");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		
		
		
		
	}
	
	@AfterTest()
	public void teardown() throws InterruptedException
	{
		Thread.sleep(3500);
		driver.close();
	}

}
