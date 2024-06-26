package Appium;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Driver;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverCommandExecutor;
import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;

public class HandleUiElements 
{

	DesiredCapabilities capabilities;
	AndroidDriver driver;
	
	@BeforeTest
	public void setup() throws MalformedURLException
	{

		capabilities =  new DesiredCapabilities();
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("deviceName","AndroidPhone");
		capabilities.setCapability("appPackage","io.appium.android.apis");
		capabilities.setCapability("appActivity","io.appium.android.apis.ApiDemos");
		capabilities.setCapability("platformVersion", "13");
		URL url = URI.create("http://10.0.0.82:4723/").toURL();
		driver = new AndroidDriver(url,capabilities);
		
	}
	@Test
	public void test_01()
	{
	
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Controls\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"1. Light Theme\"]")).click();
		driver.findElement(By.id("io.appium.android.apis:id/edit")).sendKeys("Kapil Kukkar");
		driver.findElement(By.id("io.appium.android.apis:id/check2")).click();
		driver.findElement(By.id("io.appium.android.apis:id/radio2")).click();
		driver.findElement(By.id("io.appium.android.apis:id/star")).click();
		driver.findElement(By.id("io.appium.android.apis:id/toggle2")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\"]")).click();
//		List<WebElement> planet_list = driver.findElements(By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]"));
//		for(WebElement element: planet_list)
//		{
//			System.out.println(element.getText());
//		}
		driver.findElement(By.xpath("//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"Earth\"]")).click();


	}
	
	@AfterTest
	
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2500);
		driver.quit();
	}
	
	
}
