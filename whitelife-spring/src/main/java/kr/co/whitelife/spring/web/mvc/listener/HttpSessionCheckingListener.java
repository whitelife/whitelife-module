package kr.co.whitelife.spring.web.mvc.listener;

import java.util.Date;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HttpSessionCheckingListener
 *
 * @author whitelife
 * @since 2014.10.07
 * @version 0.1
 */
public class HttpSessionCheckingListener implements HttpSessionListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void sessionCreated(HttpSessionEvent event) {
		if (logger.isDebugEnabled()) {
			logger.debug("Session ID".concat(event.getSession().getId()).concat(" created at ").concat(new Date().toString()));
		}
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		if (logger.isDebugEnabled()) {
			logger.debug("Session ID".concat(event.getSession().getId()).concat(" destroyed at ").concat(new Date().toString()));
		}
	}
}
