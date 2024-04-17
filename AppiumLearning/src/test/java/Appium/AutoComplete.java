package Appium;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AutoComplete 
{

	DesiredCapabilities capabilities;
	AndroidDriver driver;
	@BeforeTest
	public void setup() throws MalformedURLException
	{
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("deviceName","AndroidPhone");
		capabilities.setCapability("appPackage","io.appium.android.apis");
		capabilities.setCapability("appActivity","io.appium.android.apis.view.AutoComplete1");
		capabilities.setCapability("platformVersion", "13");
		URL url = URI.create("http://10.0.0.82:4723/").toURL();
		driver = new AndroidDriver(url,capabilities);
	}
	@Test
	public void test_01() throws InterruptedException
	{
		driver.findElement(By.xpath("//android.widget.AutoCompleteTextView[@resource-id=\"io.appium.android.apis:id/edit\"]")).sendKeys("ca");
		Thread.sleep(2500);
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
	}
	@AfterTest
	public void tear_down() throws InterruptedException
	{
		Thread.sleep(2500);
		driver.quit();
	}
}
