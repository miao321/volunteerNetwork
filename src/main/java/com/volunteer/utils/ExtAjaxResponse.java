package com.volunteer.utils;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:59:31
 *
 */

public class ExtAjaxResponse 
{
	private boolean success= true;
	private String msg= "";
	
	public ExtAjaxResponse() {}
	
	public ExtAjaxResponse(boolean success) {
		this.success = success;
	}
	
	public ExtAjaxResponse(boolean success,String msg) {
		this.success = success;
		this.msg = msg;
	}

	
	public boolean isSuccess() {
		return success;
	}
	public String getMsg() {
		return msg;
	}

	
	
}
