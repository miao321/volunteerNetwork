package com.volunteer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Module;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:19
 *
 */

@Repository
public interface ModuleRepository extends PagingAndSortingRepository<Module, Long>,JpaSpecificationExecutor<Module> {
	/**
	 * 查询根节点
	 */
	/*@Query("from Module m where m.parentNode.id = null")
	public List<Module> findParentNodes();
	*//**
	 * 根据父节点ID查询出子节点, 父节点为null的时候被data jpa过滤掉。所以无法传null
	 *//*
	@Query("from Module m where m.parentNode.id = ?1")
	public List<Module> findChildNodes(Long parentId);//null
	
	@Query("from Module module where module.moduleName =?1")
	public Module findModule(String moduleName);*/
	
	@Modifying
	@Query("update Module module set module.state = ?2 where module.id = ?1")
	public void updateState(Long id,Integer state);
}
