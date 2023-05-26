import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class CreateDriverSession {
	public static void main(String[] args) throws MalformedURLException {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Andriod");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_5_API_30");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		caps.setCapability("avd", "Pixel_5_API_30");
		caps.setCapability("avdLaunchTimeout", 1000000);
		
		//caps.setCapability(MobileCapabilityType.APP, "C:\\Users\\deearya\\OneDrive - Capgemini\\EclipseWorkspaces\\RestAssuredWorkspace\\BDDAppium\\target\\test-classes\\apps\\ApiDemos-debug.apk");
		URL url = new URL("http://0.0.0.0:4723/wd/hub");

		caps.setCapability("appPackage", "io.appium.android.apis");
		//caps.setCapability("appActivity", ".accessibility.CustomViewAccessibilityActivity");
		caps.setCapability("appActivity", ".ApiDemos");
		caps.setCapability("newCommandTimeout", 300);
		AppiumDriver driver = new AndroidDriver(url,caps);
		System.out.println("Session Id " + driver.getSessionId());
		
		WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Animation\"]"));
		System.out.println("Text : " + element.getText());
		element.click();
	}
}
