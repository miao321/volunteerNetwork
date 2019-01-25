package com.volunteer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Role;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:26
 *
 */


@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>,JpaSpecificationExecutor<Role> {
/*	@Query(value="select p.url,r.roleName,r.remark,r.orderNo,r.createModule,r.createBy,r.createTime,r.updateBy,r.updateTime from t_role as r left join t_permission_t_role as pr on r.id=pr.role_id left join t_permission as p on p.id=pr.permission_id",nativeQuery=true)
	public List<Object[]> findRolePermission();
	
	@Query(value="select r.id,p.url from t_permission as p left join t_permission_t_role as pr on p.id=pr.permission_id left join t_role as r on r.id=pr.role_id",nativeQuery=true)
	public List<Object[]> findPermission();
	*/
	@Query("from Role role where role.state = 1")
	public List<Role> findRole();
	@Modifying
	@Query("update Role role set role.state = ?2 where role.id = ?1")
	public void updateState(Long id,Integer state);
}
