package qa.mobile.test;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import qa.base.Base;

public class Test1 extends Base{
	
	LoginPage LoginPage = new pages.LoginPage() ; 
  @Test
  public void invalidUserName() {
	  
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]")).sendKeys("Deepika");
		
		
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]")).sendKeys("anshuarya00");
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]/android.widget.TextView")).click();
		
		String actualErrMsg = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")).getText();
		
		String expectedErrMsg = "Username and password do not match any user in this service.";
		Assert.assertEquals(actualErrMsg, expectedErrMsg, "Actual Error Message: " + actualErrMsg +" is not equal to the expectedErrMessage: " + expectedErrMsg);
	
  }
  
  
  @Test
  public void validUserNamePassword() {
	  
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]")).sendKeys("standard_user");
		driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]/android.widget.TextView")).click();
		assertTrue(driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")).isDisplayed());
  }
  
  
  

 

}
