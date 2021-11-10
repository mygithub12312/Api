
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import Map.Map;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import page.Cart;
import page.CartPostUrl;
import payload.BasePayload;


public class ApiTest {

	final Cart cartUi = new Cart();
	CartPostUrl cartPostUrl = new CartPostUrl();

	final String product = "5401677";
	final String contentType = "application/json";
	final int productQty = 1;

	@Test
	public void addProductToTheShoppingCartApiTest() {

		Response createNewCart =
				given()
						.contentType(contentType)
						.accept(contentType)
						.when()
						.post(cartPostUrl.createCart()).then().extract().response();
		String cart = createNewCart.jsonPath().getString("guid");
		String request = BasePayload.getTemplatePayload("src/main/resources/addToCartPayload.txt",
				Map.mapOf("code", "5401677", "quantity", "1"));
		given().header("Content-Type", contentType).body(request).post(
				cartPostUrl.createCustomerCurt(cart))
				.then()
				.log()
				.all()
				.assertThat()
				.statusCode(200)
				.and()
				.contentType(
				ContentType.JSON).body(matchesJsonSchemaInClasspath("schemaCartResponse.json")).body("entry.product.code",
				equalTo(product)).body("quantity", equalTo(productQty));
		cartUi.openSite(cart);

		assertThat(cartUi.getProductName().getAttribute("href").contains(product))
				.overridingErrorMessage("Product is not added to Shopping Cart");
	}
}
