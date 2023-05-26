package qa.mobile.test;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.ProductPage;
import qa.base.Base;

public class ProductTest {
	LoginPage loginPage; 
	ProductPage productPage;
	InputStream datais;
	JSONObject loginUsers;

	@Parameters({ "platformName", "platformVersion" })
	@BeforeClass
	public void beforeClass(String platformName, String platformVersion) throws IOException {
	
		try {
			String dataFileName = "data/loginUser.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener tokener = new JSONTokener(datais);
			loginUsers = new JSONObject(tokener);
		} catch (Exception e) {	e.printStackTrace();
		}finally {
			if (datais != null) {
				datais.close();
			}

		}
		
		Base base = new Base();
		try {
			base.initializeDriver(platformName, platformVersion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		Base base = new Base();
		base.quitDriver();

	}

	
	
	@BeforeMethod
	public void beforeMethod(Method m) {
		loginPage = new LoginPage();
		productPage = new ProductPage();
		System.out.println("********Starting test : " + m.getName() + "*********");
	}

	
	@Test
	  public void validUserNamePassword() throws InterruptedException {
		  
		loginPage
				.enterUserName(loginUsers.getJSONObject("validUser").getString("username"))
				.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
		
		productPage = loginPage.clickLogin();
		assertTrue(productPage.isTitleAvailable());
	  }

}
