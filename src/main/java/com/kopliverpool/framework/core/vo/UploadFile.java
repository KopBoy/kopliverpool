package com.kopliverpool.framework.core.vo;

import java.io.Serializable;


public class UploadFile implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;
	private String realName;
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
