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

}
