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
import com.xxx.volunterNetwork.dao.RoleRepository;
import com.xxx.volunterNetwork.domain.Role;
import com.xxx.volunterNetwork.service.IRoleService;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:43:54
 *
 */

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
	@Autowired
	private RoleRepository roleRepository;
	@SysLog(module="角色管理",methods="保存或者更新数据")
	public void saveOrUpdate(Role role) {
		roleRepository.save(role);
	}
	@SysLog(module="角色管理",methods="删除一条数据")
	public void delete(Role role) {
		roleRepository.delete(role);
	}
	@SysLog(module="角色管理",methods="删除多条数据")
	public void delete(List<Long> ids) {
		List<Role> roleLists = (List<Role>) roleRepository.findAll(ids);
		if(roleLists != null) {
			roleRepository.delete(roleLists);
		}			
	}
	@SysLog(module="角色管理",methods="查找一条数据")
	public Role findOne(Long id) {		
		return roleRepository.findOne(id);
	}
	@SysLog(module="角色管理",methods="查找所有数据")
	public List<Role> findAll() {
		return (List<Role>) roleRepository.findAll();
	}
	@SysLog(module="角色管理",methods="查找所有数据并分页排序")
	public Page<Role> findAll(Specification<Role> spec, Pageable pageable) {
		return roleRepository.findAll(spec, pageable);
	}
/*	@Override
	public Role findRole(String roleName) {
		return roleRepository.findRole(roleName);
	}*/
	@Override
	public void updateState(Long id, Integer state) {
		roleRepository.updateState(id, state);
		
	}
	@Override
	public List<Role> findRole() {
		return roleRepository.findRole();
	}

}
