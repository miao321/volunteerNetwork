package com.volunteer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Module;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:32:12
 *
 */

public interface IModuleService {
	public void saveOrUpdate(Module module);
	public void delete(Module module);
	public void delete(List<Long> ids);
	public Module findOne(Long id);
	public List<Module> findAll();
	public void updateState(Long id,Integer state);
	
	/*public Module findModule(String moduleName);*/
	//动态条件查询
	public Page<Module> findAll(Specification<Module> spec,Pageable pageable);


}
