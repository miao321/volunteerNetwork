package com.volunteer.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Opinion;
/**
 * 
 * @author miao
 * @date 2018年1月20日下午4:07:01
 *
 */
public class OpinionQueryDTO {	
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
	//提供static的工具方法： 根据当前opinionQueryDTO对象来组装动态查询条件
	public static Specification<Opinion> getSpecification(OpinionQueryDTO opinionQueryDTO){
		Specification<Opinion> spec = new Specification<Opinion>() {
			public Predicate toPredicate(Root<Opinion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate author = cb.like(root.get("author").as(String.class), "%"+opinionQueryDTO.getAuthor()+"%");
				Predicate content = cb.like(root.get("content").as(String.class), "%"+opinionQueryDTO.getContent()+"%");				
				Predicate title = cb.like(root.get("title").as(String.class), "%"+opinionQueryDTO.getTitle()+"%");				
				Predicate p = cb.or(author,content,title);
				return p;					
			}
		};		
		return spec;
	}
}
