package com.volunteer.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.xxx.volunterNetwork.domain.Role;
import com.xxx.volunterNetwork.domain.User;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:34:31
 *
 */
public interface IUserService {
	public void saveOrUpdate(User user);

	public void delete(User user);
	public void delete(List<Long> ids);
	public User findOne(Long id);
	public List<User> findAll();
	public User findUser(String userName); 
	public Set<Role> getRoleByUserName(String userName);
	public List<String> getPermissionsByUserName(String userName);
	//动态条件查询
	public Page<User> findAll(Specification<User> spec,Pageable pageable);
	//public List<UserRoleQueryDTO> findUserRole();
	//public List<UserRoleDTO> findUserRole2();
	public List<User> find(String hql, Class<User> class1, String[] strings);
	public void updateState(Long id,Integer state);
	/*public void jianRole(Long id);*/
}
