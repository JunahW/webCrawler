package com.junah.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import org.apache.log4j.Logger;
import com.junah.contant.Contant;
import com.junah.domain.CommentData;
import com.junah.domain.CommentHotData;
import com.junah.domain.CommentList;
import com.junah.domain.UserInfo;

/**
 * 将对象写入文件
 * 
 * @author zk
 *
 */
public class Objext2Txt {
	final static Logger logger = Logger.getLogger(Objext2Txt.class);

	private FileWriter fileWriter = null;
	private FileWriter fileWriterUser = null;

	/**
	 * 将每页的评论写到文件中
	 * 
	 * @param commentList
	 * @param hot_word
	 * @param fileName
	 * @param itemId
	 */
	public void commentList2txt(CommentList commentList, String hot_word, String fileName, String itemId) {

		if (commentList != null && commentList.getOk() == 1) {
			LinkedList<CommentData> data = commentList.getData().getData();
			LinkedList<CommentHotData> hot_data = commentList.getData().getHot_data();
			String result = FileNameUtils.getFileName() + "\t" + hot_word + "\t" + itemId + "\t";

			try {
				synchronized (fileName) {
					String com = "";

					if (hot_data == null || hot_data.size() <= 0) {

					} else {

						for (CommentHotData chd : hot_data) {
							com += result + chd.getId() + "\t" + "is_hot" + "\t" + chd.getCreated_at() + "\t"
									+ chd.getSource() + "\t" + chd.getText() + "\t" + chd.getLike_counts() + "\t"
									+ chd.isLiked() + "\t" + chd.getReply_id() + "\t" + chd.getReply_text() + "\t"
									+ chd.getUser().getId() + "\t" + chd.getUser().getScreen_name() + "\t"
									+ chd.getUser().getProfile_url() + "\n";
						}
					}
					// 遍历热评
					for (CommentData chd : data) {

						// 日期 热搜 文章id 评论ID 是否热评 评论时间 手机品牌 评论 点赞 对博主是否点赞 回复ID 点评的话 用户ID 用户昵称 用户主页URI
						com += result + chd.getId() + "\t" + "no_hot" + "\t" + chd.getCreated_at() + "\t"
								+ chd.getSource() + "\t" + chd.getText() + "\t" + chd.getLike_counts() + "\t"
								+ chd.isLiked() + "\t" + chd.getReply_id() + "\t" + chd.getReply_text() + "\t"
								+ chd.getUser().getId() + "\t" + chd.getUser().getScreen_name() + "\t"
								+ chd.getUser().getProfile_url() + "\n";

					}
					try {
						fileWriter = new FileWriter(Contant.COMMENT_FOLDER + fileName, true);
					} catch (IOException e) {
						logger.error("fileWriter创建异常" + e.getMessage());
					}
					fileWriter.write(com);
					fileWriter.flush();

				}
			} catch (Exception e) {
				logger.error("评论写入文件异常" + e.getMessage());
			} finally {

				try {
					fileWriter.close();
				} catch (IOException e) {
					logger.error("fileWriter关闭异常" + e.getMessage());
				}
			}

		}

	}

	/**
	 * 将用户写入文件
	 * 
	 * @param userInfo
	 * @param fileName
	 * @param hotWord
	 */
	public void userInfo2txt(UserInfo userInfo, String fileName, String hotWord) {

		String uString = userInfo.getId() + "\t" + userInfo.getName() + "\t" + userInfo.getSex() + "\t"
				+ userInfo.getAddress() + "\t" + userInfo.getResume() + "\t" + hotWord + "\n";
		synchronized (fileName) {
			try {
				fileWriterUser = new FileWriter(Contant.USER_FOLDER + fileName.substring(0, 14) + "UserInfoList.txt",
						true);
				fileWriterUser.write(uString);
				fileWriterUser.flush();
			} catch (IOException e) {
				logger.error("用户信息写入文件异常" + e.getMessage());
			} finally {
				try {
					fileWriter.close();
				} catch (IOException e) {
					logger.error("fileWriter关闭异常" + e.getMessage());
				}
			}
		}
	}
}
