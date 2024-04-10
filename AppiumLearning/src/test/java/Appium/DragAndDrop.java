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

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

@SuppressWarnings("deprecation")
public class DragAndDrop 
{
	DesiredCapabilities capabilities;
	static AndroidDriver driver;
	static Point locationPoint;
	static Dimension size;
	
	public static Point center_point(WebElement element)
	{
		
		locationPoint = element.getLocation();
		size= element.getSize();
		int center_x= locationPoint.getX()+ size.getWidth()/2;
		int center_y=locationPoint.getY() + size.getHeight()/2;
		return new Point(center_x,center_y);		
		
	}
	public static void validation()
	{
		WebElement dropElement= driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"io.appium.android.apis:id/drag_result_text\"]"));
		String droptextString=dropElement.getText();
		if(droptextString.equals("dropped!"))
		{
			System.out.println("Tess passed");
		}
		else
		{
		System.out.println("Test Failed");	
		}
	}
	
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
	@SuppressWarnings({ "deprecation", "rawtypes" })
	@Test()
	public void test_01() throws InterruptedException
	{
	
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Drag and Drop\"]")).click();
		WebElement sourcElement =driver.findElement(By.xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_1\"]"));
		WebElement destinationElement = driver.findElement(By.xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_3\"]"));
		Thread.sleep(2500);
		TouchAction action=  new TouchAction(driver);
//		action.longPress(longPressOptions().withElement(element(sourcElement))).moveTo(element(destinationElement)).release().perform();
		action.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(sourcElement)))
	    .moveTo(ElementOption.element(destinationElement))
	    .release()
	    .perform();
		Thread.sleep(2500);	
		validation();

	}
	@Test
	public void test_02() throws InterruptedException
	{
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Drag and Drop\"]")).click();
		WebElement sourcElement =driver.findElement(By.xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_1\"]"));
		WebElement destinationElement = driver.findElement(By.xpath("//android.view.View[@resource-id=\"io.appium.android.apis:id/drag_dot_3\"]"));
		Thread.sleep(1000);
		Point source_center= center_point(sourcElement);
		Point destination_center=center_point(destinationElement);
		PointerInput finger= new PointerInput(PointerInput.Kind.TOUCH,"finger1");
		Sequence sequence= new Sequence(finger, 1)
									.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), source_center))
									.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
									.addAction(new Pause(finger,Duration.ofMillis(550)))
									.addAction(finger.createPointerMove(Duration.ofMillis(550),PointerInput.Origin.viewport(), destination_center))
									.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Arrays.asList(sequence));
		Thread.sleep(1500);
		validation();
		
	}
	
	@AfterTest
	
	public void teardown() throws InterruptedException
	{
		Thread.sleep(2500);
		driver.quit();
	}
	

}
