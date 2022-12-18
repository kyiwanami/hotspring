package com.hotspring;

public class Util {
	
	public static class BoolUtil {

		/* Integerをbooleanに変換する.*/
		public static boolean getFlg(Integer value) {
			
			return value == 1;
		}
		
		/* booleanをIntegerに変換する.*/
		public static Integer getFlgValue(Boolean flag) {
			return flag ? 1 : 0;
		}
		
	}
	
}
