package payload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;


public class BasePayload {

	public static String getTemplatePayload(String path, Map<String, String> cartData) {
		String template = getTemplateAsString("src/main/resources/addToCartPayload");
		StringSubstitutor stringSubstitutor = new StringSubstitutor(cartData, "{{", "}}");
		return stringSubstitutor.replace(template);
	}

	public static String getTemplateAsString(String path) {
		String content = null;
		try {
			content = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return content;
	}
}
