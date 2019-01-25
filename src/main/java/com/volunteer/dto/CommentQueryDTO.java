package com.volunteer.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Comment;

/**
 * 
 * @author miao
 * @date 2018年1月20日下午4:07:01
 *
 */
public class CommentQueryDTO {
	private Long id;
	private String userName;
	private String img;
	private String content;
	private Date respTime;	
	private String studentNo;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRespTime() {
		return respTime;
	}

	public void setRespTime(Date respTime) {
		this.respTime = respTime;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	//提供static的工具方法： 根据当前commentQueryDTO对象来组装动态查询条件
	public static Specification<Comment> getSpecification(CommentQueryDTO commentQueryDTO){
		Specification<Comment> spec = new Specification<Comment>() {
			public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//1.Predicate查询条件集合
				List<Predicate> list = new ArrayList<Predicate>();				
				//2.根据QueryDTO数据字段的值进行判断以及条件的组装				
				//3.Predicate查询条件集合的size创建对应的Predicate查询条件数组
				Predicate[] p = new Predicate[list.size()];
				//4.CirteriaBuilder的and函数组装  查询条件数组
				return cb.and(list.toArray(p));				
			}
		};		
		return spec;
	}
}
