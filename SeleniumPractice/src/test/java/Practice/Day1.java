package Practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Day1 
{

	WebDriver driver;
	@BeforeTest
	public void setup()
	{
		driver = new FirefoxDriver();
		driver.get("https://www.google.com/");
		
	}
	@Test
	public void test_01()
	{
		WebElement search_boxElement = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
		search_boxElement.sendKeys("Taj Mahal");
		search_boxElement.sendKeys(Keys.ENTER);
	}
	@AfterTest
	public void tear_down() throws InterruptedException
	{
		Thread.sleep(2500);
		driver.close();
	}
}
