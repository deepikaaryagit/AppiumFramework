package qa.mobile.test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.LoginPage;
import qa.base.Base;

public class LoginTest {

	LoginPage loginPage;
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
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
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
		System.out.println("********Starting test : " + m.getName());

	}

	@Test
	public void invalidUserName() {
		//Master Branch 
		Base base = new Base();
		loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"))
				.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password")).clickLogin();
		String actualErrMsg = loginPage.getError();
		String expectedErrMsg = base.strings.get("err_invalid_user");
		Assert.assertEquals(actualErrMsg, expectedErrMsg,
				"Actual Error Message: " + actualErrMsg + " is not equal to the expectedErrMessage: " + expectedErrMsg);

	}

}
