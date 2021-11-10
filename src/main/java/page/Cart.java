package page;

import static driver.Driver.getDriver;

import org.openqa.selenium.*;


public class Cart {

	final String cartUrl = "https://www.kruidvat.nl/cart";
	final By productName = By.xpath("//a[@class='product-summary__img-link']");

	public WebElement findElement(By by) {
		return getDriver().findElement(by);
	}

	public WebElement getProductName() {
		return findElement(productName);
	}

	public void openSite(String cartId) {
		Cookie cookie = new Cookie("kvn-cart", cartId);
		getDriver().get(cartUrl);
		getDriver().manage().deleteAllCookies();
		getDriver().manage().addCookie(cookie);
		getDriver().navigate().refresh();
	}


}
