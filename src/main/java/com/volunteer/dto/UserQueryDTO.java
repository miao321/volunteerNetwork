package com.volunteer.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.User;
/**
 * 
 * @author miao
 * @date 2018年1月20日下午4:07:01
 *
 */
public class UserQueryDTO {	
	private Long id;
	private Long userId;
	private Long roleId;
	private String roleName;
	private String userName;
	private String studentNo;
	public void setId(Long id) {
		this.id = id;
	}	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public Long getId() {
		return id;
	}	
	public Long getUserId() {
		return userId;
	}
	public Long getRoleId() {
		return roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public String getUserName() {
		return userName;
	}
	public String getStudentNo() {
		return studentNo;
	}
	//提供static的工具方法： 根据当前userQueryDTO对象来组装动态查询条件
	public static Specification<User> getSpecification(UserQueryDTO userQueryDTO){
		Specification<User> spec = new Specification<User>() {
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate userName = cb.like(root.get("userName").as(String.class), "%"+userQueryDTO.getUserName()+"%");
				Predicate studentNo = cb.like(root.get("studentNo").as(String.class), "%"+userQueryDTO.getStudentNo()+"%");				
				Predicate p = cb.or(userName,studentNo);
				return p;			
			}
		};		
		return spec;
	}
}
