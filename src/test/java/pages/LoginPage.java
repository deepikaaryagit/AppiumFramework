package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import qa.base.Base;

public class LoginPage {
Base base;
	public LoginPage() {
		base = new Base();
		
		PageFactory.initElements(new AppiumFieldDecorator(base.getDriver()), this);
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Username\"]")
	private WebElement userNameTxtFld;

	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Password\"]")
	private WebElement passwordTxtFld;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]/android.widget.TextView")
	private WebElement loginBtn;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
	private WebElement errMsg;

	public LoginPage enterUserName(String userName) {
		base.sendKeys(userNameTxtFld, userName);
		return this;

	}

	public LoginPage enterPassword(String password) {
		base.sendKeys(passwordTxtFld, password);
		return this;

	}

	public ProductPage clickLogin() {
		base.click(loginBtn);
		return new ProductPage();

	}

	public String getError() {
		return base.getText(errMsg);

	}
	/*
	 * driver.findElement(By.xpath(
	 * "//android.widget.EditText[@content-desc=\"test-Username\"]")).sendKeys(
	 * "Deepika"); driver.findElement(By.xpath(
	 * "//android.widget.EditText[@content-desc=\"test-Password\"]")).sendKeys(
	 * "anshuarya00"); driver.findElement(By.xpath(
	 * "//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]/android.widget.TextView"
	 * )).click();
	 */

}
