package page;

public class CartPostUrl {

	final static String newCart = "https://www.kruidvat.nl/api/v2/kvn/users/anonymous/carts?lang=nl";
	final static String cart = "https://www.kruidvat.nl/api/v2/kvn/users/anonymous/carts/cartId/entries?lang=nl";


	public static String createCart() {
		return newCart;
	}

	public static String createCustomerCurt(String cartId) {
		return cart.replace("cartId", cartId);
	}
}
