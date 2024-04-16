package Appium;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Drawing 
{
	DesiredCapabilities capabilities;
	static AndroidDriver driver;
	
	@BeforeTest
	public void setup() throws MalformedURLException
	{
		capabilities =  new DesiredCapabilities();
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("deviceName","AndroidPhone");
		capabilities.setCapability("appPackage","com.saucelabs.mydemoapp.android");
		capabilities.setCapability("appActivity","com.saucelabs.mydemoapp.android.view.activities.SplashActivity");		
		capabilities.setCapability("platformVersion", "13");
		URL url = URI.create("http://10.0.0.82:4723/").toURL();
		driver = new AndroidDriver(url,capabilities);
	}
	@Test
	public void test_01() throws InterruptedException
	{
		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"View menu\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/itemTV\" and @text=\"Drawing\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]")).click();
		Thread.sleep(1000);
		WebElement drawing_pad= driver.findElement(By.xpath("//android.view.View[@content-desc=\"Pad to draw on\"]"));
		Point location = drawing_pad.getLocation();
		Dimension size= drawing_pad.getSize();
		int center_x= location.getX()+ size.getWidth()/2;
		int center_y=location.getY() + size.getHeight()/2 + 380;
		int x_axis = location.getX();
		int y_axis = location.getY() + 100;
		System.out.println(x_axis);
		System.out.println(y_axis);
		System.out.println(size.getHeight());
		System.out.println(size.getWidth());
		System.out.println(center_x);
		System.out.println(center_y);
		
		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger1");
		Sequence sequence = new Sequence(finger, 1)
						.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), center_x, y_axis))
						.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger, Duration.ofMillis(550)))
						.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), center_x,center_y))
						.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Arrays.asList(sequence));
		
	}
	@AfterTest
	public void tear_down() throws InterruptedException
	{
		Thread.sleep(2500);
		driver.quit();
	}
}
