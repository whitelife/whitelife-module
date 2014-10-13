package kr.co.whitelife.spring.log.modelandview;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import kr.co.whitelife.spring.log.Log;

import org.slf4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

/**
 * ModelAndViewLog
 *
 * @author whitelife
 * @since 2014.10.10
 * @version 0.1
 */
public class ModelAndViewLog implements Log {

	public void resultLog(Object result, Logger logger) {
		this.resultLog(result, logger, null);
	}

	@SuppressWarnings("unchecked")
	public void resultLog(Object result, Logger logger, Object[] args) {
		if (result == null) return;
		if (logger == null) return;

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
		report.append("\n").append("view        : ").append(mav.getView() == null ? "" : mav.getView().toString());
		report.append("\n").append("viewname    : ").append(mav.getViewName() == null ? "" : mav.getViewName());

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

		report.append("\n").append("---------------------------------------------------------------------------------------------------------");

		if (logger.isDebugEnabled()) {
			logger.debug(report.toString());
		}
	}

}