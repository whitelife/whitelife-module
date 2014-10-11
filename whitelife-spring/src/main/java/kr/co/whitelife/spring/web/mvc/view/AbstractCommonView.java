package kr.co.whitelife.spring.web.mvc.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.view.AbstractView;

/**
 * AbstractCommonView
 *
 * <pre>
 * 공통 처리 View
 * </pre>
 *
 * @author whitelife
 * @since 2014.09.30
 * @version 0.1
 */
public abstract class AbstractCommonView extends AbstractView {

	/**
	 * noCache 활성화
	 *
	 * @param response HttpServletResponse
	 */
	protected void activeNoCache(HttpServletResponse response) {
		response.setHeader("Cache-Control", "no-cache");
	}

	/**
	 * 데이터 응답
	 *
	 * @param s response data
	 * @param response HttpServletResponse
	 * @throws IOException
	 */
	protected void responseWrite(String s, HttpServletResponse response) throws IOException {
		try {
			PrintWriter writer = response.getWriter();
			writer.print(s);
			writer.flush();

			IOUtils.closeQuietly(writer);
		} catch (IOException e) {
			throw e;
		}
	}
}
