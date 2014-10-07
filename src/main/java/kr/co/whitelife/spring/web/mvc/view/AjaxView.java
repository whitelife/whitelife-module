package kr.co.whitelife.spring.web.mvc.view;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.whitelife.spring.model.Common;
import kr.co.whitelife.spring.util.ClassUtils;

import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * AjaxJsonView
 *
 * @author whitelife
 * @since 2014.09.30
 * @version 0.1
 */
public class AjaxView extends AbstractCommonView {

	private Gson gson;

	public AjaxView() {
		this.gson = new Gson();
	}

	/**
	 * JSON 변환 후 응답
	 *
	 * @param model ModelAndView
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void json(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.setContentType("text/json;charset=utf-8");
		this.setResponseContentType(request, response);

		Map<String, JsonElement> responseModel = new HashMap<String, JsonElement>();

		for (String key : model.keySet()) {
			Object value = model.get(key);

			if (value == null) continue;

			if ("dataType".equals(key)) continue;
			if (value instanceof BindingResult) continue;
			if (ClassUtils.isTargetSuperClass(value, Common.class)) continue;

			responseModel.put(key, this.gson.toJsonTree(value));
		}

		this.responseWrite(this.gson.toJsonTree(responseModel).toString(), response);
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			this.activeNoCache(response);

			String dataType = String.valueOf(model.get("dataType"));

			if (logger.isDebugEnabled()) {
				logger.debug("dataType: " + dataType);
			}

			if (StringUtils.hasLength(dataType) && !dataType.equals("null")) {
				this.getClass().getDeclaredMethod(dataType, Map.class, HttpServletRequest.class, HttpServletResponse.class).invoke(this, model, request, response);
			}
		} catch (NoSuchMethodException e) {
			throw new NoSuchMethodException("invalid request");
		} catch (Exception e) {
			throw e;
		}
	}
}
