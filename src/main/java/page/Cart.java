package page;

import static driver.Driver.getDriver;

import org.openqa.selenium.*;


public class Cart {

	final String cartUrl = "https://www.kruidvat.nl/cart";
	final By product = By.xpath("//a[@class='product-summary__img-link']");

	public WebElement findElement(By by) {
		return getDriver().findElement(by);
	}

	public WebElement getProductLink() {
		return findElement(product);
	}

	public void openCartOnUiWithCookie(String cartId) {
		Cookie cartCookie = new Cookie("kvn-cart", cartId);
		getDriver().get(cartUrl);
		getDriver().manage().deleteAllCookies();
		getDriver().manage().addCookie(cartCookie);
		getDriver().navigate().refresh();
	}


}
