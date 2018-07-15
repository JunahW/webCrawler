package com.junah.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import com.junah.contant.Contant;
import com.junah.utils.FileNameUtils;
import com.junah.webCrawler.GetCommentAndUser;
import com.junah.webCrawler.GetHotItems;
import com.junah.webCrawler.GetHotWords;

/**
 * 微博爬虫客户端
 * 
 * @author zk
 *
 */
public class WebClient {

	public static void main(String[] args) {
		String fileName = FileNameUtils.getFileName();
		String userFileName = fileName + "UserInfoList.txt";
		fileName = fileName + "HotList.txt";
		GetHotItems getHotItems = new GetHotItems("junah01", false);
		GetHotWords mc = new GetHotWords("junah02", true, 1, 1);
		try {
			mc.start(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ArrayList<String> list = mc.getHotList();

		for (String seed : list) {
			getHotItems.addSeed(Contant.HOT_WORD_LIST + seed);
		}
		getHotItems.setThreads(1);
		try {
			getHotItems.start(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HashMap<String, ArrayList<String>> itemMap = getHotItems.getHotMap();

		Set<String> hotWords = itemMap.keySet();
		ArrayList<String> midList = new ArrayList<>();

		for (String hotWord : hotWords) {
			GetCommentAndUser gcu = new GetCommentAndUser("junah03", false, hotWord, fileName);

			midList = itemMap.get(hotWord);
			for (String mid : midList) {
				gcu.addSeed(Contant.MID_ITEM + mid + "&page=1");
			}
			try {
				gcu.start(3);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/**
		 * 将文件写入hadoop
		 */
		Configuration conf = new Configuration();
		FileSystem fs = null;
		try {
			try {
				fs = FileSystem.get(new URI(Contant.HDFS_HOST_URL), conf, "zk");
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 评论写入hdfs
			Path path = new Path(Contant.HDFS_COMMENT_URL + fileName);
			FSDataOutputStream os = fs.create(path);
			FileInputStream is = new FileInputStream(Contant.COMMENT_FOLDER + fileName);
			IOUtils.copy(is, os);

			is = null;
			os = null;

			// 用户信息写入hdfs
			path = new Path(Contant.HDFS_USER_URL + userFileName);
			os = fs.create(path);
			is = new FileInputStream(Contant.USER_FOLDER + userFileName);

			IOUtils.copy(is, os);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
