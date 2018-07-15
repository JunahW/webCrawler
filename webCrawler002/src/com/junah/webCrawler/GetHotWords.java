package com.junah.webCrawler;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import com.junah.contant.Contant;
import com.junah.utils.RegexUtils;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.Requester;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 获取每日热词list
 * 
 * @author junah
 *
 */
public class GetHotWords extends BreadthCrawler {

	final static Logger logger = Logger.getLogger(GetHotWords.class);

	private ArrayList<String> hotList = new ArrayList<>();

	public ArrayList<String> getHotList() {
		return hotList;
	}

	public GetHotWords(String crawlPath, boolean autoParse, int threadNum, int topN) {
		super(crawlPath, autoParse);
		this.setRequester(new Requester() {
			@Override
			public Page getResponse(CrawlDatum crawlDatum) throws Exception {
				@SuppressWarnings("deprecation")
				HttpRequest request = new HttpRequest(crawlDatum.getUrl());
				request.setUserAgent(Contant.USER_AGENT);
				// "User-Agent",
				request.setCookie(Contant.COOKIE);

				request.setHeader("Accept", Contant.ACCEPT);
				return request.responsePage();
			}
		});

		this.addSeed(Contant.WEB_HOT_LIST_URL);
	}

	@Override
	public void visit(Page page, CrawlDatums links) {
		Document document = page.doc();
		String content = document.toString();
		hotList = RegexUtils.html2List(content);
	}
}
