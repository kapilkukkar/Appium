package Appium;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;



public class ApkFile {

	public static void main(String[] args) throws MalformedURLException, InterruptedException 
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability("deviceName", "AndroidPhone");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("automationName", "uiautomator2");
		capabilities.setCapability("app", "C:\\Users\\kumar\\Downloads\\APKFiles\\MyDemo.apk");
		capabilities.setCapability("platformVersion", "13");
		URL url = URI.create("http://10.0.0.82:4723/").toURL();  
		AndroidDriver driver = new AndroidDriver(url,capabilities);
		Thread.sleep(5000);
		System.out.println("Application installed");
		driver.quit();//CLOSE SESSION


	}

}
