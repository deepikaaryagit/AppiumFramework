package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import qa.base.Base;

public class SettingsPage {
	Base base;
	public SettingsPage() {
		base = new Base();
		PageFactory.initElements(new AppiumFieldDecorator(base.getDriver()), this);
	}
	
	@AndroidFindBy(xpath="(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
			private WebElement logoutBtn;
	
	public LoginPage clickLogout() {
		base.click(logoutBtn);
		return new LoginPage();
	}
	
	
}
