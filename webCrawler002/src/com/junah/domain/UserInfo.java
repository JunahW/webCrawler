package com.junah.domain;

public class UserInfo {
	private String hotWord;
	private String id;
	private String name;//昵称
	private Integer sex;//1代表男 0代表女
	private String address;//所在地
	private String resume;//简介
	
	
	public String getHotWord() {
		return hotWord;
	}
	public void setHotWord(String hotWord) {
		this.hotWord = hotWord;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(String sex) {
		if("男".equals(sex)) {
			this.sex = 1;
		}
		if("女".equals(sex)) {
			this.sex = 0;
		}
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	
	
}
