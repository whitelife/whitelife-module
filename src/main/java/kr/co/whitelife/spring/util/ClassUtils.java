package kr.co.whitelife.spring.util;


/**
 * Class Utils
 *
 * @author whitelife
 * @since 2014.10.02
 * @version 0.1
 */
public class ClassUtils {

	/**
	 * 객체에 상위 Class 타입 확인 여부
	 *
	 * @param obj
	 * @param targetClazz
	 * @return
	 */
	public static boolean isTargetSuperClass(Object obj, Class<?> targetClazz) {

		if (obj == null) throw new IllegalArgumentException("obj is null");
		if (targetClazz == null) throw new IllegalArgumentException("targetClazz is null");

		Class<?> clazz = obj.getClass();

		while(clazz != null) {
			if (clazz == targetClazz) {
				return true;
			}

			clazz = clazz.getSuperclass();
		}

		return false;
	}

}
