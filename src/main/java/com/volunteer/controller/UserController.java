package com.volunteer.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.volunterNetwork.anno.SysControllerLog;
import com.xxx.volunterNetwork.domain.Organization;
import com.xxx.volunterNetwork.domain.Role;
import com.xxx.volunterNetwork.domain.User;
import com.xxx.volunterNetwork.dto.UserQueryDTO;
import com.xxx.volunterNetwork.service.ILoginService;
import com.xxx.volunterNetwork.service.IRoleService;
import com.xxx.volunterNetwork.service.IUserService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtPageable;



@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService userService;
	@Autowired 
	private IRoleService roleService;
	@Autowired
	private ILoginService loginService;
	@RequestMapping("/saveOrUpdate")
//	@RequiresPermissions("user/saveOrUpdate")
//	@RequiresRoles("管理员")
	@SysControllerLog(module="用户管理",methods="添加用户")
	public @ResponseBody ExtAjaxResponse saveOrUpdate(User user) {
		/*User user2 = loginService.findUser(user.getStudentNo());
		if (user2 != null) {
			return new ExtAjaxResponse(false, "用户名已经存在");
		}*/
		
		try {		
			//Object clientPassword = new Md5Hash(new String(user.getPassword()), user.getUserName(),2);//			
			//String md5Password = clientPassword.toString();
			userService.saveOrUpdate(user);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}
	@RequestMapping("/save")
//	@RequiresPermissions("user/save")
//	@RequiresRoles("管理员")
	@SysControllerLog(module="用户管理",methods="修改用户信息")
	public @ResponseBody ExtAjaxResponse save(User user,@RequestParam Long id) {
		try {
			//user.setPassword(EncryptUtils.encript(user.getPassword()));
			userService.saveOrUpdate(user);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}	
	@RequestMapping("/saveRole")
	@SysControllerLog(module="用户管理",methods="添加角色")
	public @ResponseBody ExtAjaxResponse saveRole(UserQueryDTO userQueryDTO,@RequestParam Long roleId,@RequestParam Long userId) {
		//Role role = roleService.findOne(roleId);
		
		/*if (userQueryDTO.getRoleId() != null) {
			return new ExtAjaxResponse(false, "该用户已经拥有此角色");
		}*/
		Role role = roleService.findOne(roleId);
		User user = userService.findOne(userId);	
		user.getRoles().add(role);
		role.getUsers().add(user);
		try {
			userService.saveOrUpdate(user);	
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}	
	@RequestMapping("/update")
	@SysControllerLog(module="用户管理",methods="修改用户信息")
	public @ResponseBody ExtAjaxResponse update(User user) {
		User u = userService.findOne(user.getId());
		u.setUserName(user.getUserName());
		u.setStudentNo(user.getStudentNo());
		u.setPassword(user.getPassword());
		u.setInTake(user.getInTake());
		u.setBirthday(user.getBirthday());
		u.setSex(user.getSex());
		u.setPhone(user.getPhone());
		u.setEmail(user.getEmail());
		u.setAncestor(user.getAncestor());
		u.setPoliticalStatus(user.getPoliticalStatus());
		u.setRemark(user.getRemark());
		u.setCreateBy(user.getCreateBy());
		u.setCreateCollege(user.getCreateCollege());
		u.setCreateTime(user.getCreateTime());
		u.setUpdateBy(user.getUpdateBy());
		u.setUpdateTime(user.getUpdateTime());
		u.setState(user.getState());		
		try {
			userService.saveOrUpdate(u);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}	
//	@RequiresPermissions("user/delete")
//	@RequiresRoles("管理员")
	@RequestMapping("/delete")
	@SysControllerLog(module="用户管理",methods="删除用户")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long id) {
		
		try {
			User user = userService.findOne(id);
			user.setRoles(null);
			//userService.delete(user.getRoles());
			if (user != null) {
				userService.delete(user);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}		
	}
	@RequestMapping("/deleteUsers")
//	@RequiresPermissions("user/deleteUsers")
//	@RequiresRoles("管理员")
	@SysControllerLog(module="用户管理",methods="批量删除用户")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long[] ids) {
		try {
			List<Long> idsLists = Arrays.asList(ids);
			for(Long id : idsLists) {
				User user = userService.findOne(id);
				user.setRoles(null);
			}
			if (idsLists != null) {
				userService.delete(idsLists);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}
	}	
	@RequestMapping("/jianRole")
	public @ResponseBody ExtAjaxResponse jianRole(@RequestParam Long id) {
		/*try {*/
		User user = userService.findOne(id);
		user.setRoles(null);
		userService.saveOrUpdate(user);
			return new ExtAjaxResponse(true, "操作成功");
		/*} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}*/		
	}	
	@RequestMapping("/findOne")
	@SysControllerLog(module="用户管理",methods="查看用户信息")
	public @ResponseBody User findOne(@RequestParam Long id,HttpSession session) {
		User user = userService.findOne(id);
		System.out.println("user:"+user);
		session.setAttribute("user", user);
		return user;
	}	
	@RequestMapping("/findAll")
	public @ResponseBody List<User> findAll(){
		List<User> userLists = userService.findAll();
		return userLists;		
	}	
	@RequestMapping("/findPage")
	public String findPage(@RequestParam(value="query",required=false, defaultValue="") String query,HttpSession session,UserQueryDTO userQueryDTO,ExtPageable extPageable){
		userQueryDTO.setUserName(query);
		userQueryDTO.setStudentNo(query);
		Page<User> page = userService.findAll(userQueryDTO.getSpecification(userQueryDTO), extPageable.getPageable());
		session.setAttribute("userLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/system/user";	
	}
	@RequestMapping("/findUserPage")
	@SysControllerLog(module="用户管理",methods="查看用户列表")
	public String findUserPage(@RequestParam(value="query",required=false, defaultValue="") String query,HttpSession session,UserQueryDTO userQueryDTO,ExtPageable extPageable){
		userQueryDTO.setUserName(query);
		userQueryDTO.setStudentNo(query);
		Page<User> page = userService.findAll(userQueryDTO.getSpecification(userQueryDTO), extPageable.getPageable());
		System.out.println("page.getContent():===="+page.getContent());
		session.setAttribute("userLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/system/userDetail";	
	}
/*
	@RequestMapping("/findUserRole")
	public @ResponseBody ExtJsonResult<UserRoleQueryDTO> findUserRole(){
		List<UserRoleQueryDTO> findUserRole = userService.findUserRole();
		return new ExtJsonResult<UserRoleQueryDTO>(findUserRole);
		//return new ExtAjaxResponse(true, "操作成功");
	}
	
	@RequestMapping("/findUserRole2")
	public @ResponseBody ExtJsonResult<UserRoleDTO> findUserRole2(){
		List<UserRoleDTO> findUserRole = userService.findUserRole2();
		return new ExtJsonResult<UserRoleDTO>(findUserRole);
		//return new ExtAjaxResponse(true, "操作成功");
	}
	*/
	@RequestMapping("/refuse")
	public @ResponseBody ExtAjaxResponse refuse() {
		return new ExtAjaxResponse(false, "您无权限,请跟管理员申请权限");
	}
	
	@RequestMapping("disableUser")
	@SysControllerLog(module="用户管理",methods="禁用用户")
	public @ResponseBody User disableUser(@RequestParam Long id) {
		User user = userService.findOne(id);
		if (user.getState() != null) {
			userService.updateState(id, 0);
		}
		return user;
	}
	@RequestMapping("enableUser")
	@SysControllerLog(module="用户管理",methods="启用用户")
	public @ResponseBody User enableUser(@RequestParam Long id) {
		User user = userService.findOne(id);
		if (user.getState() != null) {
			userService.updateState(id, 1);
		}
		return user;
	}
	@RequestMapping("changeMessage")
	@SysControllerLog(module="用户管理",methods="修改信息")
	public String changeMessage(HttpSession session) {
		Long id = (Long) session.getAttribute("userId");
		User user = userService.findOne(id);
		session.setAttribute("user", user);
		return "/WEB-INF/pages/system/changeMessage";
	}
}
