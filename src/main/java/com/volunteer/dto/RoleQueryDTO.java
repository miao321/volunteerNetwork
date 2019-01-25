package com.volunteer.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Role;
/**
 * 
 * @author miao
 * @date 2018年1月20日下午4:07:01
 *
 */
public class RoleQueryDTO {	
	private Long id;
	private Long roleId;
	private Long permissionId;
	private String permissionName;
	private String roleName;
	private String remark;

	public void setId(Long id) {
		this.id = id;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
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
	public String getPermissionName() {
		return permissionName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	//提供static的工具方法： 根据当前roleQueryDTO对象来组装动态查询条件
	public static Specification<Role> getSpecification(RoleQueryDTO roleQueryDTO){
		Specification<Role> spec = new Specification<Role>() {
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate roleName = cb.like(root.get("roleName").as(String.class), "%"+roleQueryDTO.getRoleName()+"%");
				Predicate remark = cb.like(root.get("remark").as(String.class), "%"+roleQueryDTO.getRemark()+"%");				
				Predicate p = cb.or(roleName,remark);
				return p;			
			}
		};		
		return spec;
	}
}
