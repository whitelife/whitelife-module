package kr.co.whitelife.spring.web.mvc.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.util.FileCopyUtils;

/**
 * DownloadView
 *
 * @author whitelife
 * @since 2014.10.30
 * @version 0.12
 */
public class DownloadView extends AbstractCommonView {

	public DownloadView() {
		setContentType("applicaiton/download;charset=utf-8");
	}

	/**
	 * 파일 명 생성
	 *
	 * @param fileName
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws UnsupportedEncodingException
	 */
	private void setDownloadFileName(String fileName, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		fileName = URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20");

		if (logger.isDebugEnabled()) {
			logger.debug("fileName: " + fileName);
		}

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
	}

	/**
	 * 파일 다운로드
	 *
	 * @param downloadFile
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @throws Exception
	 */
	private void downloadFile(File downloadFile, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(downloadFile);

		try {
			FileCopyUtils.copy(in, out);
			out.flush();
		} catch (Exception e) {
			throw e;
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			this.setResponseContentType(request, response);

			File downloadFile = (File) model.get("downloadFile");
			String oriFileName = String.valueOf(model.get("oriFileName"));

			if (logger.isDebugEnabled()) {
				logger.debug("downloadFile: " + downloadFile);
				logger.debug("oriFileName: " + oriFileName);
			}

			this.setDownloadFileName(oriFileName, request, response);

			response.setContentLength((int) downloadFile.length());
			this.downloadFile(downloadFile, request, response);
		} catch (Exception e) {
			throw e;
		}
	}
}
