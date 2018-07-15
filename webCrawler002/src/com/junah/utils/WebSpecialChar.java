package com.junah.utils;

/**
 * 将URL中的特殊字符，如“ ”转换为“%” “#”转换为“%23”
 * 
 * @author zk
 *
 */
public class WebSpecialChar {
	public static String transferSpecialChar(String goal, String split, String change) {
		String[] splits = goal.split(split);
		String kString = "";
		for (int i = 0; i < splits.length; i++) {
			kString = kString + splits[i] + change;
		}
		return kString;
	}

	public static String transferChar(String status) {
		if (status.contains(" ")) {
			status = WebSpecialChar.transferSpecialChar(status, " ", "%20");
		}
		if (status.contains("#")) {
			status = WebSpecialChar.transferSpecialChar(status, "#", "%23");
		}
		return status;

	}

}
