package com.kopliverpool.framework.core.model;

import java.io.Serializable;


/** 
 *
 * Description: 响应前台页面信息类
 *
 * @author KOP
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014年7月21日	 KOP          1.0       1.0 Version 
 * </pre>
 */
public class ResponseResult implements Serializable{

	private static final long serialVersionUID = 1L;

	//响应码
	private String code;
	//响应信息，正确时默认为Null，主要用来记录错误信息
	private String msg;
	//具体的业务数据
	private Object data;
	
	public ResponseResult() {
		super();
	}

	public ResponseResult(String code, String msg, String data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
