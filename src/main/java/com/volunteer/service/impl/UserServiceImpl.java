package com.volunteer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.volunterNetwork.dao.UserRepository;
import com.xxx.volunterNetwork.domain.Permission;
import com.xxx.volunterNetwork.domain.Role;
import com.xxx.volunterNetwork.domain.User;
import com.xxx.volunterNetwork.service.IUserService;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:45:24
 *
 */

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserRepository userRepository;
	

	//@SysLog(module="用户管理",methods="保存或者更新数据")
	public void saveOrUpdate(User user) {
		userRepository.save(user);		
	}
	
	//@SysLog(module="用户管理",methods="删除一条数据")
	public void delete(User user) {
		userRepository.delete(user);	
	}
	//@SysLog(module="用户管理",methods="删除多条数据")
	public void delete(List<Long> ids) {
		List<User> users = (List<User>) userRepository.findAll(ids);
		if (users != null) {
			userRepository.delete(users);
		}		
	}
	//@SysLog(module="用户管理",methods="查找一条数据")
	@Transactional(readOnly = true)
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}
	//@SysLog(module="用户管理",methods="查找多条数据")
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}
	//@SysLog(module="用户管理",methods="查找多条数据并分页排序")
	public Page<User> findAll(Specification<User> spec, Pageable pageable) {
		return userRepository.findAll(spec, pageable);
	}
	//@SysLog(module="用户管理",methods="修改个人信息")
	/*public List<UserRoleQueryDTO> findUserRole() {
		List<UserRoleQueryDTO>  list = new ArrayList<UserRoleQueryDTO>();
		List<Object[]> userRole = userRepository.findUserRole();
		for(Object[] obj : userRole) {
			UserRoleQueryDTO dto = new UserRoleQueryDTO();			
			//dto.setRoleId(Long.parseLong(obj[0].toString()));
			dto.setUserName(obj[0]+"");
			dto.setDeptName(obj[1]+"");
			dto.setRoleName(obj[2]+"");
			dto.setUserNo(obj[3]+"");
			dto.setPassword(obj[4]+"");
			dto.setUserNickName(obj[5]+"");
			dto.setSex(obj[6]+"");
			if (dto.getBirthday() == null) {
				return null;
			}else {
				dto.setBirthday(DateUtil.StringToHMS(obj[8]+""));
			}
			if (dto.getAge() == null) {
				return null;
			}else {
				dto.setAge(Integer.valueOf(obj[9]+""));
			}			
			//dto.setBirthday(DateUtil.StringToHMS(obj[7]+""));
			//dto.setAge(Integer.valueOf(obj[8]+""));
			dto.setNativePlace(obj[9]+"");
			dto.setNation(obj[10]+"");
			dto.setCulture(obj[11]+"");
			dto.setCollege(obj[12]+"");
			dto.setBody(obj[13]+"");
			dto.setMarriage(obj[14]+"");
			dto.setIdCord(obj[15]+"");
			dto.setPhone(obj[16]+"");
			dto.setFamilyPhone(obj[17]+"");
			dto.setEmail(obj[18]+"");
			dto.setUserAccount(obj[19]+"");
			dto.setRemark(obj[20]+"");
			list.add(dto);
		}
		return list;
	}
	public List<UserRoleDTO> findUserRole2() {
		List<UserRoleDTO>  list = new ArrayList<UserRoleDTO>();
		List<Object[]> userRole = userRepository.findUserRole();
		List<User> user  =  (List<User>) userRepository.findAll();
		System.out.println("user++++:"+user);
		for(Object[] obj : userRole) {
			UserRoleDTO dto = new UserRoleDTO();			
			dto.setUserName(obj[0]+"");
			dto.setDeptName(obj[1]+"");
			dto.setRoleName(obj[2]+"");
			dto.setUserNo(obj[3]+"");
			dto.setPassword(obj[4]+"");
			dto.setUserNickName(obj[5]+"");
			dto.setSex(obj[6]+"");
			if (dto.getBirthday() == null) {
				return null;
			}else {
				dto.setBirthday(DateUtil.StringToHMS(obj[8]+""));
			}
			if (dto.getAge() == null) {
				return null;
			}else {
				dto.setAge(Integer.valueOf(obj[9]+""));
			}			
			//dto.setBirthday(DateUtil.StringToHMS(obj[7]+""));
			//dto.setAge(Integer.valueOf(obj[8]+""));
			dto.setNativePlace(obj[9]+"");
			dto.setNation(obj[10]+"");
			dto.setCulture(obj[11]+"");
			dto.setCollege(obj[12]+"");
			dto.setBody(obj[13]+"");
			dto.setMarriage(obj[14]+"");
			dto.setIdCord(obj[15]+"");
			dto.setPhone(obj[16]+"");
			dto.setFamilyPhone(obj[17]+"");
			dto.setEmail(obj[18]+"");
			dto.setUserAccount(obj[19]+"");
			dto.setRemark(obj[20]+"");
			list.add(dto);
		}
		return list;
	}
	*/
	
	
	@Override
	public Set<Role> getRoleByUserName(String userName) {
		User user = userRepository.findUser(userName);
		if (user == null) {
			return null;
		}
		Set<Role> roles = user.getRoles();		
		return roles;
	}
	
	@Override
	public List<String> getPermissionsByUserName(String userName) {
		User user = userRepository.findUser(userName);
		if (user == null) {
			return null;
		}
		List<String> permissionLists = new ArrayList<String>();
		for(Role role : user.getRoles()) {
			System.out.println("userRole:===="+role);
			//Role role2 = role.getRoleName();
			
			for(Permission permission : role.getPermission()) {
				permissionLists.add(permission.getUrl());
			}
		}
		System.out.println("permissionLists:==="+permissionLists);
		return permissionLists;
	}
	
	
	@Override
	public List<User> find(String hql, Class<User> class1, String[] strings) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User findUser(String userName) {
		return userRepository.findUser(userName);
	}
	@Override
	public void updateState(Long id, Integer state) {
		userRepository.updateState(id, state);
		
	}

	/*@Override
	public void jianRole(Long id) {
		userRepository.jianRole(id);
		
	}*/
	
}
