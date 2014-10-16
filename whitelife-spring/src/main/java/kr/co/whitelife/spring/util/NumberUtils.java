package kr.co.whitelife.spring.util;

import java.text.DecimalFormat;

/**
 * Number Utils
 *
 * @author whitelife
 * @since 2014.09.04
 * @version 0.1
 */
public class NumberUtils {

	public static String STANDARD_PATTERN = "#,##0"; // Comma

	/**
	 * 숫자를 특정 패턴에 맞게 문자열로 변환
	 *
	 * @param number
	 * @param pattern
	 * @return String
	 */
	private static String parseNumber(Number number, String pattern) {
		if (number == null) {
			throw new IllegalArgumentException("number is null");
		}

		if (pattern == null || pattern.isEmpty()) {
			throw new IllegalArgumentException("pattern is null or empty");
		}

		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.applyPattern(pattern);

		return decimalFormat.format(number);
	}

	/**
	 * 숫자를 특정 패턴에 맞게 문자열로 변환
	 *
	 * @param number
	 * @return String
	 */
	public static String parseNumberToString(Number number) {
		return parseNumber(number, STANDARD_PATTERN);
	}

	/**
	 * 숫자를 특정 패턴에 맞게 문자열로 변환
	 *
	 * @param number
	 * @param pattern
	 * @return String
	 */
	public static String parseNumberToString(Number number, String pattern) {
		return parseNumber(number, pattern);
	}
}
