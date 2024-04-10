package Appium;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;

public class SwipeAndScroll 
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
		capabilities.setCapability("appActivity","io.appium.android.apis.ApiDemos");
		capabilities.setCapability("platformVersion", "13");
		URL url = URI.create("http://10.0.0.82:4723/").toURL();
		driver = new AndroidDriver(url,capabilities);
	}

	@Test
	public void test_01()
	{
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
		Dimension size= driver.manage().window().getSize();		
		int center_x= size.getWidth()/2;
		int center_y=size.getHeight()/2;
		int end_x=center_x;
		int end_y= (int)((size.getHeight()) * 0.25);
		
		
		PointerInput finger =  new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		Sequence sequence = new Sequence(finger,1)
							.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), center_x,center_y))
							.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(finger, Duration.ofMillis(250)))
							.addAction(finger.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),end_x,end_y))
							.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(sequence));
		

	}
	@Test()
	public void test_02()
	{
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
		Dimension size = driver.manage().window().getSize();
		
		//find the position where you need to touch
		int startX = size.getWidth() / 2;
		int startY = size.getHeight() / 2;
		
		//position till you want to move your finger to swipe
		int endX = startX;
		int endY = (int) (size.getHeight() * 0.25);
		int endZ = (int) (size.getHeight() * 0.7);
		System.out.println(startX);
		System.out.println(startY);
		System.out.println(endX);
		System.out.println(endY);
		System.out.println(endZ);
		
		
		//PointerInput class to create a sequence of actions 
		PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
		PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
		//Sequence object, which is a list of actions that will be performed on the device
		Sequence sequence = new Sequence(finger1, 1)
				.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1, Duration.ofMillis(200))) //wait for some time
				.addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
				.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger1, Duration.ofSeconds(7))); 
		
		Sequence sequence1 = new Sequence(finger2, 1)
				.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
				.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(finger2, Duration.ofMillis(200))) //wait for some time
				.addAction(finger2.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endZ))
				.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); 

		//perform the Sequence of action
		List<Sequence> sequences = new ArrayList<Sequence>();
		sequences.add(sequence);
		sequences.add(sequence1);
//		driver.perform(Arrays.asList(sequence,sequence1));
		driver.perform(sequences);
		System.out.println("-----------------------------------------------");
		System.out.println(startX);
		System.out.println(startY);
		System.out.println(endX);
		System.out.println(endY);
		System.out.println(endZ);
	}
	@Test
	public void test_03()
	{
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
		Dimension size = driver.manage().window().getSize();
		int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endX = startX;
        int endY = (int) (size.getHeight() * 0.25);
        int endZ = (int) (size.getHeight() * 0.7);

        // PointerInput class to create a sequence of actions
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");

        // Sequence object for the first finger
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200))) // Wait for some time
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofSeconds(7)));

        // Sequence object for the second finger
        Sequence sequence1 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger2, Duration.ofMillis(200))) // Wait for some time
                .addAction(finger2.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endZ))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the sequences of actions
        List<Sequence> sequences = new ArrayList<Sequence>();
        sequences.add(sequence);
        sequences.add(sequence1);
        driver.perform(sequences);

        // Print out the start and end positions within the sequences
        System.out.println("Sequence 1:");
        System.out.println("StartX: " + startX + ", StartY: " + startY + ", EndX: " + endX + ", EndY: " + endY + ", EndZ: " + endZ);
	}

	@AfterTest

	public void teardown() throws InterruptedException
	{
		Thread.sleep(3500);
		driver.quit();
	}



}
