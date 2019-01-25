package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Permission;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:33:12
 *
 */

public interface IPermissionService {
	public void saveOrUpdate(Permission permission);
	public void delete(Permission permission);
	public void delete(List<Long> ids);
	public Permission findOne(Long id);
	public List<Permission> findAll();
	public List<Permission> findPermission();
	public void updateState(Long id,Integer state);
	
	//public Permission findPermission(String permissionName);
	//public void updateState(Long id,Integer state);
	//动态条件查询
	public Page<Permission> findAll(Specification<Permission> spec,Pageable pageable);


}
