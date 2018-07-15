package com.junah.domain;

import java.io.Serializable;

public class User implements Serializable{
	private long id;
	private String screen_name;
	private String profile_image_url;
	private boolean verified;
	private int mbtype;
	private String profile_url;
	private String remark;
	private boolean following;
	private boolean follow_me;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public String getProfile_image_url() {
		return profile_image_url;
	}
	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public int getMbtype() {
		return mbtype;
	}
	public void setMbtype(int mbtype) {
		this.mbtype = mbtype;
	}
	public String getProfile_url() {
		return profile_url;
	}
	public void setProfile_url(String profile_url) {
		this.profile_url = profile_url;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean isFollowing() {
		return following;
	}
	public void setFollowing(boolean following) {
		this.following = following;
	}
	public boolean isFollow_me() {
		return follow_me;
	}
	public void setFollow_me(boolean follow_me) {
		this.follow_me = follow_me;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", screen_name=" + screen_name + ", profile_image_url=" + profile_image_url
				+ ", verified=" + verified + ", mbtype=" + mbtype + ", profile_url=" + profile_url + ", remark="
				+ remark + ", following=" + following + ", follow_me=" + follow_me + "]";
	}
	
	
	

}
