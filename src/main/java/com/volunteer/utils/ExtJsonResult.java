package com.volunteer.utils;

import java.util.List;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:59:39
 * 
 * @param <T>
 */
public class ExtJsonResult<T> {
	private List<T> lists;
	public ExtJsonResult(List<T> lists) {		
		this.lists = lists;
	}
	public List<T> getLists() {
		return lists;
	}
}
