package com.junah.test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.junah.domain.UserInfo;

import net.sf.json.JSONObject;

public class UserRegexUtils_old {
	/**
	 * description 获取文本中的mid
	 * 
	 * @param conten
	 * @return midList
	 */
	public static  UserInfo getUserInfo(String content){
		JSONObject jsonObject=JSONObject.fromObject(content);
		content=jsonObject.toString();
		//Pattern pUser = Pattern.compile("\"item_name\"\\s*:\\s*(\"\\s*(昵称|性别|所在地|简介)\\s*\"),\"item_content\":(\"[^\"]+\")");
		Pattern pUser = Pattern.compile("(?<=\"性别\").*?\"item_content\":\"([^\"]+)");
        Matcher mUser=pUser.matcher(content);
        ArrayList<String > uList=new ArrayList<>();
        while (mUser.find()){//如果找到 开始替换
        	String[] split = mUser.group(0).split(",");
        	if(split.length == 2){
        		uList.add(split[1].substring(16, split[1].lastIndexOf("\"")));
        	}
        }
        UserInfo userInfo=new UserInfo();
        if (uList.size()==4) {
        	for (int i = 0; i <=3; i++) {
        		if(i==0) {
        			userInfo.setName(uList.get(0));
        		}else if (i==1) {
        			userInfo.setSex(uList.get(1));
        		}else if (i==2) {
        			userInfo.setAddress(uList.get(2));
        		}else if (i==3) {
        			userInfo.setResume(uList.get(3));
        		}
        	}
		}
		return userInfo;
	}

}
