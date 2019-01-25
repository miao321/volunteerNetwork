package com.volunteer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Permission;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:34:55
 *
 */

@Repository
public interface PermissionRepository extends PagingAndSortingRepository<Permission, Long>,JpaSpecificationExecutor<Permission> {
	/**
	 * 查询根节点
	 */
/*	@Query("from Permission o where o.parentName.id = null")
	public List<Permission> findParentNodes();
	*//**
	 * 根据父节点ID查询出子节点, 父节点为null的时候被data jpa过滤掉。所以无法传null
	 *//*
	@Query("from Permission o where o.parentName.id = ?1")
	public List<Permission> findChildNodes(Long parentId);//null
	*/
	/*@Query("from Permission permission where permission.permissionName =?1")
	public Permission findPermission(String permissionName);
*/	/*@Modifying
	@Query("update Permission permission set permission.state = ?2 where permission.id = ?1")
	public void updateState(Long id,Integer state);*/
	@Modifying
	@Query("update Permission permission set permission.state = ?2 where permission.id = ?1")
	public void updateState(Long id,Integer state);
	@Query("from Permission o where o.state = 1")
	public List<Permission> findPermission();

}
