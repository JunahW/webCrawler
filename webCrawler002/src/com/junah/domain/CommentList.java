package com.junah.domain;

import java.io.Serializable;

public class CommentList implements Serializable{
	private Integer ok;
	private String msg;
	private Comment data;
	
	public Integer getOk() {
		return ok;
	}
	public void setOk(Integer ok) {
		this.ok = ok;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Comment getData() {
		return data;
	}
	public void setData(Comment data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "CommentList [ok=" + ok + ", msg=" + msg + ", data=" + data + "]";
	}
	public CommentList() {
		super();
	}
	

}
