package Appium;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

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
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("deviceName","AndroidPhone");
		capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("appPackage","com.android.chrome");
		capabilities.setCapability("appActivity","com.google.android.apps.chrome.Main");
		capabilities.setCapability("platformVersion", "13");
		URL url = URI.create("http://10.0.0.82:4723/").toURL();
		driver = new AndroidDriver(url,capabilities);
	}
	@Test
	public void test_01()
	{
		
	}
	@AfterTest
	public void tear_down() throws InterruptedException
	{
		Thread.sleep(3500);
		driver.quit();
	}

}
