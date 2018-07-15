package com.junah.domain;

import java.io.Serializable;

public class CommentData implements Serializable{
	private Long id;
	private String created_at;
	private String source;
	private User user;
	private String text;
	private Long reply_id;
	private String reply_text;
	private int like_counts;
	private boolean liked;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getReply_id() {
		return reply_id;
	}
	public void setReply_id(Long reply_id) {
		this.reply_id = reply_id;
	}
	public String getReply_text() {
		return reply_text;
	}
	public void setReply_text(String reply_text) {
		this.reply_text = reply_text;
	}
	public int getLike_counts() {
		return like_counts;
	}
	public void setLike_counts(int like_counts) {
		this.like_counts = like_counts;
	}
	public boolean isLiked() {
		return liked;
	}
	public void setLiked(boolean liked) {
		this.liked = liked;
	}
	public CommentData() {
		super();
	}
	@Override
	public String toString() {
		return "CommentData [id=" + id + ", creacted_at=" + created_at + ", source=" + source + ", user=" + user
				+ ", text=" + text + ", reply_id=" + reply_id + ", reply_text=" + reply_text + ", like_counts="
				+ like_counts + ", liked=" + liked + "]";
	}
	
}
