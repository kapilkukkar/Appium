package Appium;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class AutomateCalculator {

	public static void main(String[] args) throws MalformedURLException, InterruptedException 
	{
		DesiredCapabilities capabilities= new DesiredCapabilities();
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("deviceName","AndroidPhone");
		capabilities.setCapability("appPackage","com.google.android.calculator");
		capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
		capabilities.setCapability("platformVersion", "13");
		URL url = URI.create("http://10.0.0.82:4723/").toURL();
		AndroidDriver driver =  new AndroidDriver(url,capabilities);
		Thread.sleep(3000);
		System.out.println("Calculator opened");
		String calculatorlocator="com.google.android.calculator:id/";
		
		driver.findElement(By.id(calculatorlocator+"digit_8")).click();
		driver.findElement(By.id(calculatorlocator+"op_mul")).click();
		driver.findElement(By.id(calculatorlocator+"digit_8")).click();
		driver.findElement(By.id(calculatorlocator+"eq")).click();
		WebElement resultElement = driver.findElement(By.id("com.google.android.calculator:id/result_final"));
		String resultTextString= resultElement.getText();
		
		if(resultTextString.equals("64"))
		{
			System.out.println("Test passed");
		}
		else 
		{
			System.out.println("Test Failed");
		}
		Thread.sleep(1000);
		driver.quit();

	}

}
