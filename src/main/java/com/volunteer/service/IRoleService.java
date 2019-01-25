package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Role;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:32:19
 *
 */


public interface IRoleService {
	public void saveOrUpdate(Role role);
	public void delete(Role role);
	public void delete(List<Long> ids);
	public Role findOne(Long id);
	public List<Role> findAll();
	public List<Role> findRole();
	public void updateState(Long id,Integer state);
/*	public Role findRole(String roleName);*/
	
	
	//动态条件查询
	public Page<Role> findAll(Specification<Role> spec,Pageable pageable);


}
