package com.junah.contant;

/**
 * 常量
 * 
 * @author junah
 *
 */
public interface Contant {
	// 首页的正则匹配
	String PAGE_REGEX_1 = "https://m\\.weibo\\.cn/api/comments/show\\?id=\\d{16}&page=1";
	// 非首页的正则匹配
	String PAGE_REGEX_PLUS = "https://m\\.weibo\\.cn/api/comments/show\\?id=\\d{16}&page=\\d{1,5}";
	// 用户URL的正则匹配式
	String USER_REGEX = "https://m\\.weibo\\.cn/api/container/getIndex\\?containerid=\\d{16}_-_INFO&title="
			+ "%25E5%259F%25BA%25E6%259C%25AC%25E8%25B5%2584%25E6%2596%2599&luicode=10000011&lfid=\\d{16}";

	// 评论文件所储存的路径
	String COMMENT_FOLDER = "D:\\webHot\\";
	// 用户信息文件所存储的文件
	String USER_FOLDER = "D:\\webHotUserInfo\\";
	/**
	 * 根据实际修改文件所在路径
	 */
	// 用户主页URL的前缀
	String USER_INDEX_PRE = "https://m.weibo.cn/api/container/getIndex?containerid=230283";
	// 用户首页URL的后缀
	String USER_INDEX_POST = "_-_INFO&title=%25E5%259F%25BA%25E6%259C%25AC%25E8%25B5%2584%2"
			+ "5E6%2596%2599&luicode=10000011&lfid=230283";
	// 微博热搜榜
	String HOT_WORD_LIST = "https://m.weibo.cn/api/container/getIndex?containerid=100103type%3D1%26q%3D";
	// 关于热词的微博路径
	String MID_ITEM = "https://m.weibo.cn/api/comments/show?id=";

	// cookie值
	String COOKIE = "__guid=67447191.267720829151853220.1522155899869.6543";

	// USER_AGENT
	String USER_AGENT = "(Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 "
			+ "Mobile Safari/537.36";
	// USER_AGENT
	String GET_COMMENT_USER_USER_AGENT = "(Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 "
			+ "Mobile Safari/537.36";

	// 浏览器的accept
	String ACCEPT = "application/json, text/javascript, */*; q=0.01";
	// 微博热搜榜URL
	String WEB_HOT_LIST_URL = "http://s.weibo.com/top/summary?cate=realtimehot";
	// hdfs的URL
	String HDFS_HOST_URL = "hdfs://192.168.163.38:9000/";
	// 评论信息文件所放在的HDFS路径
	String HDFS_COMMENT_URL = "hdfs://192.168.163.38:9000/webHot/";
	// =用户信息文件所放在的路径
	String HDFS_USER_URL = "hdfs://192.168.163.38:9000/webHotUserInfo/";

}
