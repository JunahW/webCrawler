package com.junah.test;

public class MyTest {

	public static void main(String[] args) {
		String url = "https://m.weibo.cn/api/container/getIndex?" 
				+ "containerid=2302836541331855_-_INFO&title="
				+ "%25E5%259F%25BA%25E6%259C%25AC%25E8%25B5%2584%25E6%2596%2599"
				+ "&luicode=10000011&lfid=2302836541331855";
		System.out.println(url.matches("https://m\\.weibo\\.cn/api/container/getIndex\\?" 
				+ "containerid=\\d{16}_-_INFO&title="
				+ "%25E5%259F%25BA%25E6%259C%25AC%25E8%25B5%2584%25E6%2596%2599"
				+ "&luicode=10000011&lfid=\\d{16}"));
	}
	// https://m.weibo.cn/api/comments/show?id=\\d\\{16\\}&page=1
}
