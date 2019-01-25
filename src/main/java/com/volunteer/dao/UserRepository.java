package com.volunteer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.xxx.volunterNetwork.domain.Role;
import com.xxx.volunterNetwork.domain.User;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:35:42
 *
 */
@Repository 
public interface UserRepository extends PagingAndSortingRepository<User, Long>,JpaSpecificationExecutor<User> {
	@Query("from User user where user.userName =?1")
	public User findUser(String userName);
	@Query("from User user where user.userName = ?1 and user.password = ?2")
	public User findUser(String userName,String password);
	@Modifying
	@Query("update User user set user.password = ?2 where user.id = ?1")
	public void updateUser(Long id,String comfirPassword);
	@Modifying
	@Query("update User user set user.state = ?2 where user.id = ?1")
	public void updateState(Long id,Integer state);
	@Query(value="select r.roleName from t_user as u left join t_role_t_user as ru on u.id=ru.users_id left join t_role as r on r.id=ru.roles_id where userName = ?1 and u.state =1",nativeQuery=true)
	public Role getRoleByUserName(String userName);
	@Query(value="select r.roleName,p.url from t_role as r left join t_permission_t_role as pr on r.id=pr.role_id left join t_permission as p on p.id=pr.permission_id where userName = ?1 and u.state =1",nativeQuery=true)
	public List<Object[]> getPermissionsByUserName(String userName);
	
	/*@Query(value="delete from t_role_t_user where users_id = ?1 ",nativeQuery=true)
	public void jianRole(Long id);*/
	
	/*
	@Query(value="select u.userName,u.deptName,r.roleName,u.userNo,u.password,u.userNickName,"
			+ "u.sex,u.birthday,u.age,u.nativePlace,u.nation,u.culture,u.college,u.body,u.marriage, "
			+ "u.idCord,u.phone,u.familyPhone,u.email,u.userAccount,u.remark from t_user as u left join t_user_role as ur on u.id=ur.userId left join t_role as r on r.id=ur.roleId",nativeQuery=true)
	public List<Object[]> findUserRole();
	
	@Query(value="select u.userName,u.deptName,r.roleName,u.userNo,u.password,u.userNickName,"
			+ "u.sex,u.birthday,u.age,u.nativePlace,u.nation,u.culture,u.college,u.body,u.marriage, "
			+ "u.idCord,u.phone,u.familyPhone,u.email,u.userAccount,u.remark from t_user as u left join t_user_role as ur on u.id=ur.userId left join t_role as r on r.id=ur.roleId",nativeQuery=true)
	public List<Object[]> findUserRole2();
	
	*/
	
}
