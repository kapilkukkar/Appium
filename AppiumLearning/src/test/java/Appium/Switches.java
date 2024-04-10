package Appium;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Switches 
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
		String scroll= "Switches";
		WebElement element = driver.findElement(AppiumBy.androidUIAutomator
									("new UiScrollable(new UiSelector().scrollable(true))"
									   +".scrollIntoView(new UiSelector().text(\"" + scroll +"\"))"));
		
		element.click();
		
		driver.findElement(By.xpath("//android.widget.Switch[@content-desc=\"Monitored switch\"]")).click();
		WebElement montier_switch=driver.findElement(By.xpath("//android.widget.Toast[@text=\"Monitored switch is on\"]"));
		String switch_text=montier_switch.getText();
		assertEquals(switch_text, "Monitored switch is ON");
		WebElement switch_status=driver.findElement(By.xpath("//android.widget.Switch[@content-desc=\"Customized text\"]"));
		
		if (switch_status.isSelected()==true)
		{
			System.out.println("Switch is on");
		}
		else
		{
			System.out.println("Switch is OFF");
		}

	}
	
	@AfterTest
	
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2500);
		driver.quit();
	}
	
	

}
