package com.junah.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.junah.domain.CommentData;
import com.junah.domain.CommentHotData;
import com.junah.domain.CommentList;
import com.junah.domain.User;
import com.junah.domain.UserInfo;
import com.junah.webCrawler.GetHotWords;

import net.sf.json.JSONObject;

public class Json2CommentList {

	final static Logger logger = Logger.getLogger(GetHotWords.class);

	public static CommentList json2CommentList(String jsonStr) {
		CommentList commentList = null;
		HashMap<String, Object> map = new HashMap<>();
		map.put("hot_data", CommentHotData.class);
		map.put("data", CommentData.class);
		map.put("user", User.class);

		JSONObject json = JSONObject.fromObject(jsonStr);
		try {
			commentList = (CommentList) JSONObject.toBean(json, CommentList.class, map);
		} catch (Exception e) {
			logger.debug("commentList转换异常" + e.getStackTrace());
		}
		if (null != commentList) {
			logger.info("获取json数据成功");
		}
		if (commentList != null && commentList.getOk() == 1) {
			return commentList;
		}
		return null;
	}

	public static UserInfo userInfo2Json(String userJsonStr) {

		return null;
	}

	// 将UserInfo用户写入文档

	public static void userInfo2Txt(UserInfo userInfo, String fileName) {
		if (userInfo != null) {
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("H:/webHotUserInfo/" + fileName + "UserInfoList.txt", true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String UserInfoString = userInfo.getId() + "\t" + userInfo.getName() + "\t" + userInfo.getSex() + "\t"
					+ userInfo.getAddress() + "\t" + userInfo.getResume() + "\n";
			try {
				fileWriter.write(UserInfoString);
				fileWriter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
