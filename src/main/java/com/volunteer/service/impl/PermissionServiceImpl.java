package com.volunteer.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.volunterNetwork.anno.SysLog;
import com.xxx.volunterNetwork.dao.PermissionRepository;
import com.xxx.volunterNetwork.domain.Permission;
import com.xxx.volunterNetwork.service.IPermissionService;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:42:03
 *
 */



@Service
@Transactional
public class PermissionServiceImpl implements IPermissionService {
	private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
	@Autowired
	private PermissionRepository permissionRepository;
	@SysLog(module="部门管理",methods="保存数据或者更新数据")
	public void saveOrUpdate(Permission permission) {
		permissionRepository.save(permission);
	}
	@SysLog(module="部门管理",methods="删除数据")
	public void delete(Permission permission) {
		permissionRepository.delete(permission);
	}
	@SysLog(module="部门管理",methods="删除多条数据")
	public void delete(List<Long> ids) {
		List<Permission> permissionLists = (List<Permission>) permissionRepository.findAll(ids);
		if (permissionLists != null) {
			permissionRepository.delete(permissionLists);
		}			
	}
	@SysLog(module="部门管理",methods="查找一条数据")
	public Permission findOne(Long id) {
		return permissionRepository.findOne(id);
	}
	@SysLog(module="部门管理",methods="查找所有数据")
	public List<Permission> findAll() {
		return (List<Permission>) permissionRepository.findAll();
	}
	@SysLog(module="部门管理",methods="查找所有数据并分页排序")
	public Page<Permission> findAll(Specification<Permission> spec, Pageable pageable) {
		return permissionRepository.findAll(spec, pageable);
	}
	/*@Override
	public Permission findPermission(String permissionName) {
		return permissionRepository.findPermission(permissionName);
	}*/
	/*@Override
	public void updateState(Long id, Integer state) {
		permissionRepository.updateState(id, state);
		
	}
*/
	@Override
	public void updateState(Long id, Integer state) {
		permissionRepository.updateState(id, state);
		
	}
	@Override
	public List<Permission> findPermission() {
		return permissionRepository.findPermission();
	}
}
