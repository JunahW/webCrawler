package com.junah.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成文件名：yyyyMMddHHmmss
 * 
 * @author zk
 *
 */
public class FileNameUtils {
	/**
	 * 获取文件名
	 * 
	 * @return 关于时间的文件名
	 */
	public static String getFileName() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String fileName = simpleDateFormat.format(date);
		return fileName;
	}

}
