package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import qa.base.Base;

public class ProductPage extends MenuPage {

	Base base;
	public ProductPage() {
		base = new Base();
		PageFactory.initElements(new AppiumFieldDecorator(base.getDriver()), this);
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Cart drop zone\"]/android.view.ViewGroup/android.widget.TextView")
	private WebElement productTitle;

	public boolean isTitleAvailable() throws InterruptedException {
		return base.isDisplayed(productTitle);
	}
	
	

}
