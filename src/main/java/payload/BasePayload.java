package payload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.text.StringSubstitutor;


public class BasePayload {

	public static String getTemplate(String pathToTemplate) {
		String content = null;
		try {
			content = new String(Files.readAllBytes(Paths.get(pathToTemplate)));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return content;
	}

	public static String getTemplatePayload(String path, Map<String, String> cartData) {
		String template = getTemplate("src/main/resources/addToCartPayload");
		StringSubstitutor stringSubstitutor = new StringSubstitutor(cartData, "{{", "}}");
		return stringSubstitutor.replace(template);
	}
}
