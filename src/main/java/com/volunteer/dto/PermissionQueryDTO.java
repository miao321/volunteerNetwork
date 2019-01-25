package com.volunteer.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Permission;
/**
 * 
 * @author miao
 * @date 2018年1月20日下午4:07:01
 *
 */
public class PermissionQueryDTO {	
	private Long id;
	private Long roleId;
	private Long permissionId;
	private String roleName;
	private String url;
	private String description;
	public void setId(Long id) {
		this.id = id;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getId() {
		return id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public Long getPermissionId() {
		return permissionId;
	}
	public String getRoleName() {
		return roleName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	//提供static的工具方法： 根据当前permissionQueryDTO对象来组装动态查询条件
	public static Specification<Permission> getSpecification(PermissionQueryDTO permissionQueryDTO){
		Specification<Permission> spec = new Specification<Permission>() {
			public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate url = cb.like(root.get("url").as(String.class), "%"+permissionQueryDTO.getUrl()+"%");
				Predicate description = cb.like(root.get("description").as(String.class), "%"+permissionQueryDTO.getDescription()+"%");				
				Predicate p = cb.or(url,description);
				return p;				
			}
		};		
		return spec;
	}
}
