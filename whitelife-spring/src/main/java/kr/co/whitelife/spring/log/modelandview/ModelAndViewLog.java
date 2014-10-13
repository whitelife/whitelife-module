package kr.co.whitelife.spring.log.modelandview;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.co.whitelife.spring.log.Log;

import org.slf4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * ModelAndViewLog
 *
 * <pre>
 * Ajax Log 기능 추가, Controller Return Type 별 Log 기능 추가
 * </pre>
 *
 * @author whitelife
 * @since 2014.10.13
 * @version 0.2
 */
public class ModelAndViewLog implements Log {

	public void resultLog(Object result, Logger logger) {
		this.resultLog(result, logger, null);
	}

	@SuppressWarnings("unchecked")
	public void resultLog(Object result, Logger logger, Object[] args) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

		if (result == null) return;
		if (logger == null) return;

		if (!isAjax) {
			if (result instanceof ModelAndView) {
				this.modelAndViewLog((ModelAndView) result, logger);
			}

			if (result instanceof String) {
				ModelMap modelMap = null;

				for (Object obj : args) {
					if (obj instanceof ModelMap) {
						modelMap = (ModelMap) obj;
						break;
					}
				}

				ModelAndView mav = new ModelAndView();
				mav.setViewName(String.valueOf(result));
				mav.addAllObjects(modelMap);

				this.modelAndViewLog(mav, logger);
			}

			if (result instanceof Map) {
				ModelAndView mav = new ModelAndView();

				ModelMap modelMap = new ModelMap();
				modelMap.addAllAttributes((Map<String, ?>) result);

				mav.addAllObjects(modelMap);

				this.modelAndViewLog(mav, logger);
			}
		}

		if (isAjax) {
			this.resultAjaxLog(result, logger, args);
		}
	}

	/**
	 * model Logger
	 *
	 * @param model Model
	 * @param report StringBuffer
	 */
	private void modelLog(Map<String, Object> model, StringBuffer report) {
		if (model != null) {
			boolean isFirst = true;

			for (String key : model.keySet()) {
				if (isFirst) {
					report.append("\n").append("objects     [");
					isFirst = false;
				}

				Object value = model.get(key);
				if (value == null) value = "";

				if (value instanceof List<?>) value = Arrays.toString(((List<?>) value).toArray());

				value = value.toString();

				report.append("\n    { ").append(key).append(" - ").append(value).append(" }");
			}

			if (!isFirst) {
				report.append("\n]");
			}
		}
	}

	/**
	 * ajax Logger
	 *
	 * @param result 결과 값
	 * @param logger object
	 * @param args object[]
	 */
	private void resultAjaxLog(Object result, Logger logger, Object[] args) {
		StringBuffer report = new StringBuffer();

		report.append("\n").append("-----------------------------------------ModelAndView Information----------------------------------------");
		report.append("\n").append("ajax        : ").append("true");

		if (result instanceof ModelAndView) {
			this.modelLog(((ModelAndView) result).getModel(), report);
		}

		if (result instanceof String) {
			report.append("\n").append("json        : ").append(result == null ? "" : result);
		}

		if (result instanceof Map) {
			report.append("\n").append("json        : ").append(result == null ? "" : result.toString());
		}

		report.append("\n").append("---------------------------------------------------------------------------------------------------------");

		if (logger.isDebugEnabled()) {
			logger.debug(report.toString());
		}
	}

	/**
	 * modelAndView Logger
	 *
	 * @param mav ModelAndView
	 * @param logger Logger
	 */
	private void modelAndViewLog(ModelAndView mav, Logger logger) {
		Map<String, Object> model = mav.getModel();
		StringBuffer report = new StringBuffer();

		report.append("\n").append("-----------------------------------------ModelAndView Information----------------------------------------");
		report.append("\n").append("ajax        : ").append("false");
		report.append("\n").append("view        : ").append(mav.getView() == null ? "" : mav.getView().toString());
		report.append("\n").append("viewname    : ").append(mav.getViewName() == null ? "" : mav.getViewName());

		this.modelLog(model, report);

		report.append("\n").append("---------------------------------------------------------------------------------------------------------");

		if (logger.isDebugEnabled()) {
			logger.debug(report.toString());
		}
	}

}