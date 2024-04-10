package Appium;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Driver;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.openqa.selenium.Point;
import io.appium.java_client.android.AndroidDriver;

public class LongPress 
{

	DesiredCapabilities capabilities;
	static AndroidDriver driver;
	WebElement backspace;

	public static void long_press(WebElement ele)
	{
		Point location = ele.getLocation();
		PointerInput long_press = new PointerInput(PointerInput.Kind.TOUCH,"finger");
		Sequence sequence = new Sequence(long_press, 1)
				.addAction(long_press.createPointerMove(Duration.ofMillis(0),PointerInput.Origin.viewport() , location.x,location.y))
				.addAction(long_press.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(long_press.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), location.x,location.y))
				.addAction(long_press.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(sequence));




	}
	@BeforeTest	
	public void setup() throws MalformedURLException
	{
		capabilities= new DesiredCapabilities();
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("automationName","uiautomator2");
		capabilities.setCapability("deviceName","AndroidPhone");
		capabilities.setCapability("appPackage","com.google.android.dialer");
		capabilities.setCapability("appActivity","com.google.android.dialer.extensions.GoogleDialtactsActivity");
		capabilities.setCapability("platformVersion", "13");
		URL url = URI.create("http://10.0.0.82:4723/").toURL();
		driver =  new AndroidDriver(url,capabilities);

	}	
	@Test	
	public void test_01()
	{
		String dialerid="com.google.android.dialer:id/";
		driver.findElement(By.id(dialerid+"dialpad_fab")).click();
		driver.findElement(By.id(dialerid+"four")).click();
		driver.findElement(By.id(dialerid+"one")).click();
		driver.findElement(By.id(dialerid+"six")).click();
		driver.findElement(By.id(dialerid+"eight")).click();
		driver.findElement(By.id(dialerid+"seven")).click();
		driver.findElement(By.id(dialerid+"eight")).click();
		driver.findElement(By.id(dialerid+"six")).click();
		driver.findElement(By.id(dialerid+"two")).click();
		driver.findElement(By.id(dialerid+"three")).click();
		driver.findElement(By.id(dialerid+"five")).click();
		backspace = driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"backspace\"]"));
		long_press(backspace);

	}
	@AfterTest
	public void tear_down() throws InterruptedException
	{
		Thread.sleep(3500);
		driver.quit();
	}
}
