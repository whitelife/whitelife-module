package kr.co.whitelife.spring.util;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpSupport Utils
 *
 * @author whitelife
 * @since 2014.10.16
 * @version 0.1
 */
public class HttpSupportUtils {

	/**
	 * Ajax 여부 판단
	 *
	 * @param request HttpServletRequest
	 * @return isAjax
	 */
	public static boolean isAjax(HttpServletRequest request) {
		if (request == null) throw new IllegalArgumentException("request is null");

		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

	/**
	 * Http query string 생성
	 *
	 * @param parameters
	 * @return querystring
	 */
	public static String makeQueryString(String... parameters) {
		StringBuffer buffer = new StringBuffer();
		boolean isFirst = false;

		buffer.append("?");

		if (parameters == null) return "";
		if (parameters.length == 0) return "";

		for (String parameter : parameters) {
			if (isFirst) {
				buffer.append("&");
			}

			if (!isFirst) {
				isFirst = true;
			}

			buffer.append(parameter);
		}

		return buffer.toString();
	}
}
