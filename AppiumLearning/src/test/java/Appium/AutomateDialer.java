package Appium;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class AutomateDialer {

	public static void main(String[] args) throws MalformedURLException, InterruptedException 
	{
		DesiredCapabilities capabilities= new DesiredCapabilities();
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("deviceName","AndroidPhone");
		capabilities.setCapability("appPackage","com.google.android.dialer");
		capabilities.setCapability("appActivity","com.google.android.dialer.extensions.GoogleDialtactsActivity");
		
		capabilities.setCapability("platformVersion", "13");
		URL url = URI.create("http://10.0.0.82:4723/").toURL();
		AndroidDriver driver =  new AndroidDriver(url,capabilities);
		Thread.sleep(3000);
		System.out.println("Dialer opened");
		String dialerid="com.google.android.dialer:id/";
		driver.findElement(By.id(dialerid+"dialpad_fab")).click();
		driver.findElement(By.id(dialerid+"four")).click();
		driver.findElement(By.id(dialerid+"one")).click();
		driver.findElement(By.id(dialerid+"six")).click();
		driver.findElement(By.id(dialerid+"eight")).click();
		driver.findElement(By.id(dialerid+"seven")).click();
		driver.findElement(By.id(dialerid+"eight")).click();
		driver.findElement(By.id(dialerid+"six")).click();
		driver.findElement(By.id(dialerid+"four")).click();
		driver.findElement(By.id(dialerid+"seven")).click();
		driver.findElement(By.id(dialerid+"eight")).click();
		driver.findElement(By.id(dialerid+"dialpad_voice_call_button")).click();
		System.out.println("Call has been placed");
		Thread.sleep(3500);
		driver.findElement(By.id(dialerid+"incall_end_call")).click();
		System.out.println("Call has been disconnected");		
		driver.quit();
		

	}

}
