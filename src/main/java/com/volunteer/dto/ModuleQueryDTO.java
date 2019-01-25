package com.volunteer.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Module;
/**
 * 
 * @author miao
 * @date 2018年1月20日下午4:07:01
 *
 */
public class ModuleQueryDTO {	
	private String moduleName;
	private String parentName;
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	//提供static的工具方法： 根据当前moduleQueryDTO对象来组装动态查询条件
	public static Specification<Module> getSpecification(ModuleQueryDTO moduleQueryDTO){
		Specification<Module> spec = new Specification<Module>() {
			public Predicate toPredicate(Root<Module> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate moduleName = cb.like(root.get("moduleName").as(String.class), "%"+moduleQueryDTO.getModuleName()+"%");
				Predicate parentName = cb.like(root.get("parentName").as(String.class), "%"+moduleQueryDTO.getParentName()+"%");				
				Predicate p = cb.or(moduleName,parentName);
				return p;		
			}
		};		
		return spec;
	}
}
