package kr.co.whitelife.spring.file;

import java.io.File;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 기능 지원 인터페이스
 *
 * @author whitelife
 * @since 2014.09.30
 * @version 0.11
 */
public interface FileSupport {

	/**
	 * SEPARATOR
	 */
	public static String SEPARATOR = "/";

	/**
	 * 파일 생성
	 *
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public File makeFile(String fileName) throws Exception;

	/**
	 * 파일 생성
	 *
	 * @param fileName
	 * @param directoryName
	 * @return
	 * @throws Exception
	 */
	public File makeFile(String fileName, String directoryName) throws Exception;

	/**
	 * 파일 리턴
	 *
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public File getFile(String fileName) throws Exception;

	/**
	 * 파일 리턴
	 *
	 * @param fileName
	 * @param directoryName
	 * @return
	 * @throws Exception
	 */
	public File getFile(String fileName, String directoryName) throws Exception;

	/**
	 * 파일 확인
	 *
	 * @param fileName
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isFile(String fileName) throws Exception;

	/**
	 * 파일 확인
	 *
	 * @param fileName
	 * @param directoryName
	 * @return boolean
	 * @throws Exception
	 */
	public boolean isFile(String fileName, String directoryName) throws Exception;

	/**
	 * 폴더 생성
	 *
	 * @return
	 * @throws Exception
	 */
	public void makeDirectory() throws Exception;

	/**
	 * 파일 명 [directory, file] 분리
	 *
	 * @param fileName
	 * @return [directory, file]
	 * @throws Exception
	 */
	public Map<String, String> directoryToFile(String fileName) throws Exception;

	/**
	 * 파일 쓰기
	 *
	 * @param file
	 * @return 저장된 파일 이름
	 * @throws Exception
	 */
	public String writeFile(MultipartFile file) throws Exception;
}
