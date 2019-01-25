package com.volunteer.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.type.IntegerType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.xxx.volunterNetwork.domain.Share;
/**
 * 
 * @author miao
 * @date 2018年1月20日下午4:07:01
 *
 */

public class ShareQueryDTO {
	private String author;
	private String title;
	private String content;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	private Integer state;
	//提供static的工具方法： 根据当前shareQueryDTO对象来组装动态查询条件
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public static Specification<Share> getSpecification(ShareQueryDTO shareQueryDTO){
		Specification<Share> spec = new Specification<Share>() {
			public Predicate toPredicate(Root<Share> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//1.Predicate查询条件集合
				//List<Predicate> list = new ArrayList<Predicate>();				
				//2.根据QueryDTO数据字段的值进行判断以及条件的组装
				Predicate state = cb.equal(root.get("state").as(String.class), shareQueryDTO.getState());				
				Predicate title = cb.like(root.get("title").as(String.class), "%"+shareQueryDTO.getTitle()+"%");
				Predicate author = cb.like(root.get("author").as(String.class), "%"+shareQueryDTO.getAuthor()+"%");
				Predicate content = cb.like(root.get("content").as(String.class), "%"+shareQueryDTO.getContent()+"%");				
				Predicate p = cb.or(title,author,content);
				p = cb.and(state,p);
			
				//3.Predicate查询条件集合的size创建对应的Predicate查询条件数组
				
				//4.CirteriaBuilder的and函数组装  查询条件数组
				return p;		
			}
		};		
		return spec;
	}
	public static Specification<Share> getSpecification2(ShareQueryDTO shareQueryDTO){
		Specification<Share> spec = new Specification<Share>() {
			public Predicate toPredicate(Root<Share> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate author = cb.like(root.get("author").as(String.class), "%"+shareQueryDTO.getAuthor()+"%");
				Predicate content = cb.like(root.get("content").as(String.class), "%"+shareQueryDTO.getContent()+"%");				
				Predicate title = cb.like(root.get("title").as(String.class), "%"+shareQueryDTO.getTitle()+"%");				
				Predicate p = cb.or(author,content,title);
				return p;		
			}
		};		
		return spec;
	}
}
