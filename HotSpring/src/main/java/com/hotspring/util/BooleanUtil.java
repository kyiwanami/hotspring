package com.hotspring.util;

public class BooleanUtil {

	public static String BoolToStr(Boolean bool) {

		if (bool == null) {
			return null;
		}

		if (bool) {
			return "1";
		} else
			return "0";
	}
}
