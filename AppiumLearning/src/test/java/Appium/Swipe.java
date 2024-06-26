package Appium;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;

public class Swipe 
{
	DesiredCapabilities capabilities;
	AndroidDriver driver;
	WebElement photoElement;

	public void swipe(WebElement element, String direction) {
	    ((JavascriptExecutor) driver).executeScript("mobile:swipeGesture",
	            ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
	                            "direction", direction,
	                            "percent",0.9)
	    );
	}
	@BeforeTest
	public void setup() throws MalformedURLException
	{
		capabilities = new DesiredCapabilities();
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
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Gallery\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"1. Photos\"]")).click();
		photoElement=driver.findElement(By.xpath("//android.widget.Gallery[@resource-id=\"io.appium.android.apis:id/gallery\"]/android.widget.ImageView[1]"));
		assertEquals(photoElement.getAttribute("focusable"), "true");
		swipe(photoElement,"left");
		WebElement photoElement_1=driver.findElement(By.xpath("//android.widget.Gallery[@resource-id=\"io.appium.android.apis:id/gallery\"]/android.widget.ImageView[2]"));
		swipe(photoElement_1, "left");
		assertEquals(photoElement_1.getAttribute("focusable"), "false");
		
		
		
		
	}
	
	@AfterTest
	
	public void tear_down() throws InterruptedException
	{
		Thread.sleep(2500);
		driver.quit();
	}
}
