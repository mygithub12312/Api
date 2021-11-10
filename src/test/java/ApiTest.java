
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

	final String product = "2876350";
	final String contentType = "application/json";
	final int productQty = 1;

	@Test
	public void addProductToTheShoppingCartApiTest() {

		Response createNewCartResponse = given().contentType(contentType).accept(contentType).when().post(
				cartPostUrl.getCreateNewCartUrl()).then().extract().response();
		String cart = createNewCartResponse.jsonPath().getString("guid");
		String request = BasePayload.getTemplatePayload("src/main/resources/addToCartPayload.txt",
				Map.mapOf("code", "2876350", "quantity", "1"));
		given().header("Content-Type", contentType).body(request).post(
				cartPostUrl.getCreatedCartUrl(cart)).then().log().all().assertThat().statusCode(200).and().contentType(
				ContentType.JSON).body(matchesJsonSchemaInClasspath("schemaCartResponse.json")).body("entry.product.code",
				equalTo(product)).body("quantity", equalTo(productQty));
		cartUi.openCartOnUiWithCookie(cart);

		assertThat(cartUi.getProductLink().getAttribute("href").contains(product)).as(
				"Cart does not contain expected product").isTrue();
	}
}
