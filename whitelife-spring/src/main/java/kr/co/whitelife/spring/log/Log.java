package kr.co.whitelife.spring.log;

import org.slf4j.Logger;

/**
 * Log
 *
 * @author whitelife
 * @since 2014.10.10
 * @version 0.1
 */
public interface Log {

	/**
	 * 결과 로그
	 *
	 * @param result 결과 값
	 * @param logger logger object
	 */
	public void resultLog(Object result, Logger logger);

	/**
	 * 결과 로그
	 *
	 * @param result 결과 값
	 * @param logger logger object
	 * @param args Object[]
	 */
	public void resultLog(Object result, Logger logger, Object[] args);

}
