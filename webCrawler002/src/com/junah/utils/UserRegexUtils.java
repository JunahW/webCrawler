package com.junah.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.junah.domain.UserInfo;

import net.sf.json.JSONObject;

/**
 * 用户信息正则匹配工具类
 * 
 * @author zk
 *
 */
public class UserRegexUtils {
	/**
	 * description 获取文本中的
	 * 
	 * @param conten
	 * @return midList
	 */
	public static UserInfo getUserInfo(String content) {
		// System.out.println(content);
		JSONObject jsonObject = JSONObject.fromObject(content);
		content = jsonObject.toString();
		System.out.println(content);
		UserInfo userInfo = new UserInfo();
		String[] regex = { "昵称", "性别", "简介" };
		for (int i = 0; i < regex.length; i++) {
			String find = getRegex(regex[i], content, null);
			if (i == 0) {
				userInfo.setName(find);
			} else if (i == 1) {
				userInfo.setSex(find);
			} else if (i == 2) {
				userInfo.setResume(find);
			}
		}
		// 省份
		userInfo.setAddress(getProvinceRegex(content, "其他"));

		return userInfo;
	}

	/**
	 * 获取用户信息的相应字段
	 * 
	 * @param index
	 * @param content
	 * @param default_str
	 * @return
	 */
	private static String getRegex(String index, String content, String default_str) {
		String find = "";
		// Pattern pUser = Pattern
		// .compile("\"item_name\"\\s*:\\s*\"\\s*(" + index +
		// ")\\s*\",\"item_content\":\"[^\"]+\"");
		Pattern pUser = Pattern.compile("(?<=\"" + index + "\").*?\"item_content\":\"([^\"]+)");
		Matcher mUser = pUser.matcher(content);
		while (mUser.find()) {// 如果找到 开始替换
			String[] split = mUser.group(0).split(":");
			if (split.length < 1) {
				find = "";
			} else {
				find = split[1].substring(1);
			}
		}
		if (find == "") {
			find = default_str;
		}
		return find;
	}

	/**
	 * 查找用户所在的省份
	 * 
	 * @param content
	 * @param default_str
	 * @return
	 */
	private static String getProvinceRegex(String content, String default_str) {
		String find = "";
		// Pattern pUser = Pattern
		// .compile("\"item_name\"\\s*:\\s*\"\\s*(" + index +
		// ")\\s*\",\"item_content\":\"[^\"]+\"");
		// Pattern pUser =
		// Pattern.compile("(?<=\"所在地\").*?\"item_content\":\"([^\\s]+)\"");
		Pattern pUser = Pattern.compile("(?<=\"所在地\").*?\"item_content\":\"([^\"]+)");
		Matcher mUser = pUser.matcher(content);
		while (mUser.find()) {// 如果找到 开始替换
			String[] split = mUser.group(0).split(":");
			if (split.length < 1) {
				find = "";
			} else {
				find = split[1].substring(1).split(" ")[0];// 获取用户所在的省，切去市
			}
		}
		if (find == "") {
			find = default_str;
		}
		return find;
	}

//	public static void main(String[] args) {
//		String content = "{\"ok\":1,\"data\":{\"cardlistInfo\":{\"v_p\":\"42\",\"cardlist_title\":\"\",\"desc\":\"\",\"show_style\":1,\"can_shared\":0,\"containerid\":\"2302832110775114_-_INFO\",\"cardlist_menus\":[{\"type\":\"button_menus_refresh\",\"name\":\"\\u5237\\u65b0\"},{\"type\":\"gohome\",\"name\":\"\\u8fd4\\u56de\\u9996\\u9875\",\"params\":{\"scheme\":\"sinaweibo:\\/\\/gotohome\"}}],\"page\":null},\"cards\":[{\"card_type\":11,\"card_group\":[{\"card_type\":42,\"display_arrow\":0,\"desc\":\"\\u8d26\\u53f7\\u4fe1\\u606f\"},{\"card_type\":41,\"item_name\":\"\\u6635\\u79f0\",\"item_content\":\"\\u6770\\u4ed4_Y\"},{\"card_type\":41,\"item_name\":\"\\u7b80\\u4ecb\",\"item_content\":\"\\u6682\\u65e0\\u7b80\\u4ecb\"}]},{\"card_type\":11,\"card_group\":[{\"card_type\":42,\"display_arrow\":0,\"desc\":\"\\u4e2a\\u4eba\\u4fe1\\u606f\"},{\"card_type\":41,\"item_name\":\"\\u6027\\u522b\",\"item_content\":\"\\u7537\"},{\"card_type\":41,\"item_name\":\"\\u6240\\u5728\\u5730\",\"item_content\":\"\\u6e56\\u5317 \\u6b66\\u6c49\"}]}],\"showAppTips\":0,\"scheme\":\"sinaweibo:\\/\\/cardlist?containerid=2302832110775114_-_INFO&extparam=&luicode=10000011&lfid=2302832110775114&from=1110005030\"}}";
//		getUserInfo(content);
//	}

}
