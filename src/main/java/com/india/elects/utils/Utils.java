package com.india.elects.utils;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @author id854906
 *
 */
public final class Utils {

	public static final int NOT_FOUND = -1;
	public static final String EMPTY = "";
	public static final String OTHER = "Other";

	public static final String XLSX_FILE_EXTENSION = ".xlsx";
	public static final String TEMP_FILE_EXTENSION = ".raf";

	private static final String[] TRUE_VALUES = { "true", "1", "y", "yes" };

	public static final String REGEXP_GENDER = "^[MF]$";
	public static final String REGEXP_DIGITS = "^\\d*$";
	public static final String REGEXP_NO_DIGITS = "^\\D*$";
	public static final String REGEXP_NO_SPECIAL = "^[a-zA-Z0-9]*$";
	public static final String REGEXP_EMAIL = "\\A[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\z";
	public static final String REGEXP_BOOLEAN = "^[YN ]$";
	public static final String REGEXP_PHONE_COUNTRY = "^(\\+?\\d{1,3}|\\d{2,5}|[0]{2}\\d{1,3})$";

	public static final String REGEXP_ALPHA_SPACE = "^[a-zA-Z ]*$";
	public static final String REGEXP_ALPHA_SPACE_HYPHEN = "^[\\p{L}\\s-]*$";

	/**
	 * @param path
	 * @param fileName
	 * @param postFix
	 * @param extension
	 * @return
	 */
	public static String getFileName(File path, String fileName, String postFix, String extension) {
		StringBuilder buf = new StringBuilder();
		buf.append(path.getPath());
		buf.append(File.separatorChar);
		buf.append(fileName.substring(0, fileName.lastIndexOf('.')));
		buf.append(postFix);
		buf.append(extension);
		return buf.toString();
	}

	/**
	 * @param string
	 * @return
	 */
	public static String getTrimmed(String string) {
		if (isEmpty(string)) {
			return "";

		} else {
			return string.trim();
		}
	}

	/**
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(boolean[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(byte[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(char[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(double[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(float[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(int[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * @param cells
	 * @return
	 */

	/**
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(long[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	/**
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object) {
		return object == null || object.toString().isEmpty();
	}

	/**
	 * @param objects
	 * @return
	 */
	public static boolean isEmpty(Object[] objects) {
		return objects == null || objects.length == 0;
	}

	/**
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(short[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * @param trueFalseValue
	 * @return
	 */
	public static boolean isTrue(Object trueFalseValue) {
		if (isEmpty(trueFalseValue)) {
			return false;

		} else if (trueFalseValue instanceof Boolean) {
			return ((Boolean) trueFalseValue).booleanValue();

		} else {
			return search(TRUE_VALUES, trueFalseValue.toString().trim().toLowerCase()) > NOT_FOUND;
		}
	}

	/**
	 * @param objects
	 * @param object
	 * @return
	 */
	public static int search(Object[] objects, Object object) {
		if (isEmpty(objects)) {
			return NOT_FOUND;
		}

		for (int i = 0; i < objects.length; i++) {
			if (Objects.equals(objects[i], object)) {
				return i;
			}
		}

		return NOT_FOUND;
	}

	private Utils() {
		super();
	}
}
