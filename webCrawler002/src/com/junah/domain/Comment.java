package com.junah.domain;

import java.io.Serializable;
import java.util.LinkedList;

public class Comment implements Serializable {
	private LinkedList<CommentData> data;
	private int total_number;
	private int max;
	private LinkedList<CommentHotData> hot_data;
	private int hot_total_number;
	public LinkedList<CommentData> getData() {
		return data;
	}
	public void setData(LinkedList<CommentData> data) {
		this.data = data;
	}
	public int getTotal_number() {
		return total_number;
	}
	public void setTotal_number(int total_number) {
		this.total_number = total_number;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public LinkedList<CommentHotData> getHot_data() {
		return hot_data;
	}
	public void setHot_data(LinkedList<CommentHotData> hot_data) {
		this.hot_data = hot_data;
	}
	public int getHot_total_number() {
		return hot_total_number;
	}
	public void setHot_total_number(int hot_total_number) {
		this.hot_total_number = hot_total_number;
	}
	public Comment() {
		super();
	}
	@Override
	public String toString() {
		return "Comment [data=" + data + ", total_number=" + total_number + ", max=" + max + ", hot_data=" + hot_data
				+ ", hot_total_number=" + hot_total_number + "]";
	}
	
}
