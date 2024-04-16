package Appium;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Browser
{
	DesiredCapabilities capabilities;
	AndroidDriver driver;
	@BeforeTest
	public void setup() throws MalformedURLException
	{
//		String path =""; 
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("deviceName","AndroidPhone");
		//capabilities.setCapability("browserName", "firefox");
		capabilities.setCapability("appPackage", "org.mozilla.firefox");
		capabilities.setCapability("appActivity", "org.mozilla.firefox.App");
		capabilities.setCapability("geckodriverExecutable","C:\\Users\\kumar\\AppiumLearning\\AppiumLearning\\driver\\geckodriver.exe");
		capabilities.setCapability("platformVersion", "13");
		URL url = URI.create("http://10.0.0.82:4723/").toURL();
		driver = new AndroidDriver(url,capabilities);
	}
	@Test
	public void test_01() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.get("https://www.google.com/");
		Thread.sleep(2000);
		WebElement search_boxElement = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
		search_boxElement.sendKeys("Taj Mahal");
		search_boxElement.sendKeys(Keys.ENTER);
		
	}
	@AfterTest
	public void tear_down() throws InterruptedException
	{
		Thread.sleep(8000);
		driver.quit();
	}

}
