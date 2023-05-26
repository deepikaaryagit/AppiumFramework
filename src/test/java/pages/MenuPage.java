package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import qa.base.Base;

public class MenuPage {
	Base base;
	public MenuPage() {
		base = new Base();
		PageFactory.initElements(new AppiumFieldDecorator(base.getDriver()), this);
	}

	
	@AndroidFindBy(xpath ="//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
	private WebElement settingsBtn;
	
	
	public SettingsPage clickSettings() {
		base.click(settingsBtn);
		return new SettingsPage();
	}
	
	
	
	
	
}
