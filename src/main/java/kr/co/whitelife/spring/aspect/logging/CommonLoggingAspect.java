package kr.co.whitelife.spring.aspect.logging;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import kr.co.whitelife.spring.aspect.CommonAspect;
import kr.co.whitelife.spring.log.modelandview.ModelAndViewLog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Controller Common Logging
 *
 * @author whitelife
 * @see Signature
 * @see StopWatch
 * @see HttpServletRequest
 * @see CommonAspect
 * @since 2014.08.21
 * @version 0.13
 */
public class CommonLoggingAspect extends CommonAspect {

	private ModelAndViewLog modelAndViewLog = new ModelAndViewLog();

	/**
	 * Controller Common Logging
	 *
	 * @param joinPoint Proxy Method Info
	 * @return joinPoint
	 * @throws Throwable
	 */
	public Object loggingAspect(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;

		StopWatch stopWatch = new StopWatch();
		StringBuffer report = new StringBuffer();

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

		try {
			if (logger.isDebugEnabled() || logger.isInfoEnabled()) {
				stopWatch.start();
				report.append("\n").append("---------------------------------------Servlet Request Information---------------------------------------");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}

		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			throw e;
		}

		try {
			if (logger.isDebugEnabled() || logger.isInfoEnabled()) {
				stopWatch.stop();

				Signature signature = joinPoint.getSignature();
				boolean isFirst = true;

				report.append("\n").append("processTime : ").append(stopWatch.getLastTaskTimeMillis()).append(" ms");
				report.append("\n").append("method      : ").append(signature.getDeclaringTypeName()).append(".").append(signature.getName());

				if (request != null) {
					if (logger.isDebugEnabled()) {
						report.append("\n").append("requestUrl  : ").append(request.getRequestURI());
						report.append("\n").append("remoteAddr  : ").append(request.getRemoteAddr());
						report.append("\n").append("remoteHost  : ").append(request.getRemoteHost());
						report.append("\n").append("remotePort  : ").append(request.getRemotePort());

						Cookie[] cookies = request.getCookies();

						if (cookies != null) {
							for (Cookie cookie : cookies) {
								if (isFirst) {
									report.append("\n").append("cookies     [");
									isFirst = false;
								}

								report.append("\n    { ").append(cookie.getName()).append(" - ").append(cookie.getValue()).append(" }");
							}

							if (!isFirst) {
								report.append("\n]");
							}
						}
					}

					Enumeration<?> names = request.getParameterNames();
					isFirst = true;

					while(names.hasMoreElements()) {
						if (isFirst) {
							report.append("\n").append("parameters  [");
							isFirst = false;
						}

						String name = String.valueOf(names.nextElement());

						report.append("\n    { ").append(name).append(" - ").append(request.getParameter(name)).append(" }");
					}

					if (!isFirst) {
						report.append("\n]");
					}
				}

				report.append("\n").append("---------------------------------------------------------------------------------------------------------");

				if (logger.isDebugEnabled()) {
					modelAndViewLog.resultLog(result, logger);
					logger.debug(report.toString());
				}

				else if (logger.isInfoEnabled()) {
					logger.info(report.toString());
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
		}

		return result;
	}
}

