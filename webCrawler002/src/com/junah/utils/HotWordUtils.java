package com.junah.utils;

/**
 * 热搜词工具类
 * 
 * @author zk
 *
 */
public class HotWordUtils {
	/**
	 * 从带HTML标签的字符串中获取热搜词
	 * 
	 * @param content
	 * @return
	 */
	public static String hotWord(String content) {
		int endIndex = content.indexOf('<');
		if (endIndex == -1) {
			endIndex = content.length() - 1;
		}
		content = content.substring(0, endIndex);

		// 将空格以及#代替为URI的对应编码
		content = WebSpecialChar.transferChar(content);
		return content;

	}

	/**
	 * 从URL中获取热搜词
	 * 
	 * @param content
	 * @return
	 */
	public static String getHotWordByUrl(String url) {

		int starIndex = url.indexOf("type%3D1%26q%3D") + 15;
		String hotWord = url.substring(starIndex);
		return hotWord;
	}

}
