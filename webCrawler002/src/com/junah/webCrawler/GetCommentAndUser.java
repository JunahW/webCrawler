package com.junah.webCrawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

import com.junah.contant.Contant;
import com.junah.domain.CommentData;
import com.junah.domain.CommentList;
import com.junah.domain.UserInfo;
import com.junah.test.UserRegexUtils_old;
import com.junah.utils.FileNameUtils;
import com.junah.utils.Json2CommentList;
import com.junah.utils.Objext2Txt;
import com.junah.utils.UserRegexUtils;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import net.sf.json.JSONObject;

/**
 * 获取评论和用户信息
 * 
 * @author junah
 *
 */
public class GetCommentAndUser extends BreadthCrawler {

	final static Logger logger = Logger.getLogger(GetCommentAndUser.class);

	private String fileName;
	private String hotWord;
	private Objext2Txt objext2Txt = new Objext2Txt();

	public GetCommentAndUser(String crawlPath, boolean autoParse, String hotWord, String fileName) {
		super(crawlPath, autoParse);
		this.hotWord = hotWord;
		this.fileName = fileName;
		/**
		 * 控制线程数量
		 */
		this.setThreads(8);
		/**
		 * 设置执行周期和连接最大时长
		 */
		getConf().setExecuteInterval(1500).setConnectTimeout(5000);

	}

	@Override
	public Page getResponse(CrawlDatum crawlDatum) throws Exception {
		@SuppressWarnings("deprecation")
		HttpRequest request = new HttpRequest(crawlDatum.getUrl());

		request.setUserAgent(Contant.GET_COMMENT_USER_USER_AGENT);

		return request.responsePage();
	}

	@Override
	public void visit(Page page, CrawlDatums links) {
		if (page.url().matches(Contant.PAGE_REGEX_1)) {
			byte[] content = page.content();
			String jsonStr = new String(content);
			CommentList commentList = Json2CommentList.json2CommentList(jsonStr);

			String url = page.url();
			int beginIndex = url.indexOf("id=") + 3;
			int endIndex = url.indexOf("&page");
			String itemId = page.url().substring(beginIndex, endIndex);
			// 存入txt
			objext2Txt.commentList2txt(commentList, hotWord, fileName, itemId);

			int pageNum = 0;
			if (commentList != null) {
				pageNum = commentList.getData().getTotal_number() / 10;
			}

			if (pageNum >= 2) {
				for (int i = 2; i <= pageNum + 1; i++) {
					// 将评论分页放入links
					links.add(page.url().substring(0, page.url().indexOf("page=") + 5) + i, "itemPage");
				}

				// TODO将用户URL加入Links
				Long userId = null;
				LinkedList<CommentData> data = commentList.getData().getData();
				for (CommentData commentData : data) {
					userId = commentData.getUser().getId();
					links.add(Contant.USER_INDEX_PRE + userId + Contant.USER_INDEX_POST + userId, "users");
				}
			}
			logger.info("评论");
		} else if (page.url().matches(Contant.PAGE_REGEX_PLUS)) {
			byte[] content = page.content();
			String jsonStr = new String(content);
			CommentList commentList = Json2CommentList.json2CommentList(jsonStr);
			// 将用户URL加入Links
			if (commentList != null && commentList.getOk() == 1) {
				Long userId = null;
				LinkedList<CommentData> data = commentList.getData().getData();
				if (data != null) {
					for (CommentData commentData : data) {
						userId = commentData.getUser().getId();
						links.add(Contant.USER_INDEX_PRE + userId + Contant.USER_INDEX_POST + userId, "users");
					}
				}
			}

			String url = page.url();
			int beginIndex = url.indexOf("id=") + 3;
			int endIndex = url.indexOf("&page");
			String itemId = page.url().substring(beginIndex, endIndex);
			// 存入txt
			objext2Txt.commentList2txt(commentList, hotWord, fileName, itemId);
			logger.info("评论:" + url.substring(endIndex + 5));
		} else if (page.url().matches(Contant.USER_REGEX)) {
			byte[] content = page.content();
			String userJsonStr = new String(content);

			// TODO
			// 这里原本是UserRegexUtils_old
			UserInfo userInfo = UserRegexUtils.getUserInfo(userJsonStr);
			userInfo.setId(page.url().substring(page.url().indexOf("lfid=") + 11));

			// 写入txt
			objext2Txt.userInfo2txt(userInfo, fileName, hotWord);
			logger.info("写入用户");
		}
	}

}
