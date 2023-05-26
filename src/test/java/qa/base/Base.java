package qa.base;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import utils.TestUtils;

public class Base {
	protected static AppiumDriver driver;
	protected static Properties props;
	InputStream inputStream;
	public static HashMap<String,String> strings = new HashMap<String,String>();
	TestUtils utils;
	InputStream stringsis; 
	
	
	public Base() {
		}

	public void setDriver(AppiumDriver driver)
	{
		this.driver = driver;
		
	}
	
	public AppiumDriver getDriver()
	{
		return driver;
	}
	
	
	
	
	  public void initializeDriver(String platformName, String platformVersion ) throws Exception {
		  try {
			  props = new Properties();
			  String propFileName = "config.properties";
			  String xmlFileName = "strings/strings.xml";
			  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			  props.load(inputStream);
			  
			  
			  stringsis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
			  utils = new TestUtils();
			  strings = utils.parseStringXML(stringsis);
			  
			  
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, props.getProperty("androidDeviceName"));
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
			caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
			caps.setCapability("avd", "Pixel_5_API_30");
			caps.setCapability("avdLaunchTimeout", 1000000);
			
			//URL appURL = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
			
			String appURL = getClass().getResource(props.getProperty("androidAppLocation")).getFile();
			System.out.println("appURL +" + appURL);
		//	caps.setCapability("app", appURL);
			
			URL url = new URL(props.getProperty("appiumURL"));

			caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
			caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
			
			caps.setCapability("newCommandTimeout", 300);
			driver = new AndroidDriver(url,caps);
		  }
		  catch(Exception e){
			  e.printStackTrace();
			  throw e;
			  
		  }finally {
			  
			  if(inputStream!=null)
			  {
				  inputStream.close();
			  }

			  if(stringsis!=null)
			  {
				  stringsis.close();
			  }
			  
		  }
			
	  }
	
	
	public void waitforVisibility(WebElement e)
	{
		
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(TestUtils.WAIT));
		wait.until(ExpectedConditions.visibilityOf(e));
		
	}

	
	public void click(WebElement e)
	{
		
		waitforVisibility(e);
		e.click();
		
	}
	
	public void sendKeys(WebElement e, String str)
	{
		
		waitforVisibility(e);
		e.sendKeys(str);
		
	}
	
	public String getText(WebElement e)
	{
		
		waitforVisibility(e);
		return e.getText();
		
	}

	public boolean isDisplayed(WebElement e)
	{
		
		waitforVisibility(e);
		if(e.isDisplayed())
		{
			return true;
		}
		return false;
	}
	

	public void quitDriver() {
		driver.quit();
	}

}
