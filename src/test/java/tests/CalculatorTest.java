package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

import static org.junit.Assert.assertTrue;

public class CalculatorTest {
	
	static AppiumDriver<MobileElement> driver;

	public static void main(String[] args) {
		
		try {
			openCalculator();
		} catch (MalformedURLException e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void openCalculator() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
//		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator");
		cap.setCapability("deviceName", "emulator");
		cap.setCapability("udid", "emulator-5554");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "9");
		cap.setCapability("appPackage", "com.android.calculator2");
		cap.setCapability("appActivity", ".Calculator");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AppiumDriver<MobileElement>(url, cap);
		
		System.out.println("Application started...");
		
		MobileElement two = driver.findElement(By.id("com.android.calculator2:id/digit_2"));
		MobileElement plus = driver.findElement(By.id("com.android.calculator2:id/op_add"));
		MobileElement three = driver.findElement(By.id("com.android.calculator2:id/digit_3"));
		MobileElement equals = driver.findElement(By.id("com.android.calculator2:id/eq"));
		MobileElement result = driver.findElement(By.id("com.android.calculator2:id/result"));
		
		two.click();
		plus.click();
		three.click();
		equals.click();
		String res = result.getText();
		System.out.println("Result is " + res);
		boolean validate = res.contains("5");
		assertTrue("assert failed", validate);
		driver.quit();
	}

}
