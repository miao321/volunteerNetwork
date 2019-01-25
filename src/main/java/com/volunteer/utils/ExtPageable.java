package com.volunteer.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import net.bytebuddy.asm.Advice.This;

public class ExtPageable 
{
	private int  page  = 1;	//pageNumber
	private int  limit = 20;//pageSize
	private int limit2 = 10;//pageSize2
	private int limit3 = 15;//pageSize3
	private String sort ="id";
	private String dir ="DESC";
	
	//只用于要接收前端分页条件（所以只需要set即可）
	public void setPage(int page) {
		this.page = page;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}	
	public void setLimit2(int limit2) {
		this.limit2 = limit2;
	}
	public void setLimit3(int limit3) {
		this.limit3 = limit3;
	}
	public Pageable getPageable() {
		Sort sort = new Sort(Direction.ASC, this.sort);
		
		if(this.dir.equals("DESC")) {
			sort = new Sort(Direction.DESC, this.sort);
		}
		return new PageRequest(this.page-1, this.limit, sort);
	}
	public Pageable getPageable2() {
		Sort sort = new Sort(Direction.ASC, this.sort);		
		if(this.dir.equals("DESC")) {
			sort = new Sort(Direction.DESC, this.sort);
		}
		return new PageRequest(this.page-1, this.limit2, sort);
	}
	public Pageable getPageable3() {
		Sort sort = new Sort(Direction.ASC, this.sort);		
		if(this.dir.equals("DESC")) {
			sort = new Sort(Direction.DESC, this.sort);
		}
		return new PageRequest(this.page-1, this.limit3, sort);
	}
}
