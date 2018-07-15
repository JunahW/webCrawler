package com.junah.webCrawler;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.junah.utils.HotWordUtils;
import com.junah.utils.RegexUtils;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

/**
 * 获取关于热词的微博
 * 
 * @author junah
 */
public class GetHotItems extends BreadthCrawler {

	final static Logger logger = Logger.getLogger(GetHotItems.class);

	private HashMap<String, ArrayList<String>> hotMap = new HashMap<>();

	public HashMap<String, ArrayList<String>> getHotMap() {
		return hotMap;
	}

	public GetHotItems(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
	}

	@Override
	public void visit(Page page, CrawlDatums links) {
		byte[] content = page.content();
		String html = new String(content);
		ArrayList<String> list = RegexUtils.getMids(html);
		String url = page.url();
		logger.info("热搜的URL：" + url);
		String hotWord = HotWordUtils.getHotWordByUrl(url);
		hotMap.put(hotWord, list);
	}
}
