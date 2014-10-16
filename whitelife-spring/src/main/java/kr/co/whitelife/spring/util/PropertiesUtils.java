package kr.co.whitelife.spring.util;

import java.util.Properties;

/**
 * Properties Utils
 *
 * @author whitelife
 * @since 2014.09.04
 * @version 0.1
 */
public class PropertiesUtils {

	/**
	 * 특정 구분자로 저장되어 있는 Property 값 추출 시 사용
	 *
	 * @param properties
	 * @param key
	 * @param regex 문자열 파싱 구분자
	 * @return
	 */
	public static String[] getProperties(Properties properties, String key, String regex) {
		if (properties == null) {
			throw new IllegalArgumentException("properties is null");
		}

		if (key == null || key.isEmpty()) {
			throw new IllegalArgumentException("key is null or empty");
		}

		if (regex == null || regex.isEmpty()) {
			throw new IllegalArgumentException("regex is null or empty");
		}

		String value = properties.getProperty(key);

		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException("value is null or empty");
		}

		return value.split(regex);
	}

}
