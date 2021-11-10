package Map;

import org.testng.collections.Maps;

import com.google.common.collect.ImmutableMap;


public class Map {

	private Map() {
	}

	public static <K, V> java.util.Map<K, V> mapOf(K k1, V v1, K k2, V v2) {
		return Maps.newHashMap(ImmutableMap.of(k1, v1, k2, v2));
	}
}
