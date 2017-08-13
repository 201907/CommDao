package com.shenyang.util;

public class StringUtils {
	public static String firstCharUpper(String str){
		return str.substring(0,1).toUpperCase()+str.substring(1);
	}
	public static String firstCharLower(String str){
		return str.substring(0,1).toLowerCase()+str.substring(1);
	}
	public static String getMethodName2ColumnName(String str){
		return firstCharLower(str.replaceAll("^get", ""));
	}
	public static String setMethodName2ColumnName(String str){
		return firstCharLower(str.replaceAll("^set", ""));
	}
	public static void main(String[] args) {
		System.out.println(setMethodName2ColumnName("setName"));
	}
}
