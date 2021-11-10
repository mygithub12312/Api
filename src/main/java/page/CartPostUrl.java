package page;

public class CartPostUrl {

	final static String newCart = "https://www.kruidvat.nl/api/v2/kvn/users/anonymous/carts?lang=nl";
	final static String cart = "https://www.kruidvat.nl/api/v2/kvn/users/anonymous/carts/cartId/entries?lang=nl";

	public CartPostUrl() {

	}

	public static String getCreateNewCartUrl() {
		return newCart;
	}

	public static String getCreatedCartUrl(String cartId) {
		return cart.replace("cartId", cartId);
	}
}
