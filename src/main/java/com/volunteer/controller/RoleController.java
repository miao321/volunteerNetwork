package com.volunteer.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.volunterNetwork.anno.SysControllerLog;
import com.xxx.volunterNetwork.domain.Permission;
import com.xxx.volunterNetwork.domain.Role;
import com.xxx.volunterNetwork.dto.PermissionQueryDTO;
import com.xxx.volunterNetwork.dto.RoleQueryDTO;
import com.xxx.volunterNetwork.service.IPermissionService;
import com.xxx.volunterNetwork.service.IRoleService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtJsonResult;
import com.xxx.volunterNetwork.util.ExtPageable;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午11:09:18
 *
 */

@Controller
@RequestMapping("/role")
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IPermissionService permissionService;
	
	@RequestMapping("/saveOrUpdate")
//	@RequiresPermissions("role/saveOrUpdate")
//	@RequiresRoles("管理员")
	@SysControllerLog(module="角色管理",methods="添加角色")
	public @ResponseBody ExtAjaxResponse saveOrUpdate(Role role) {
		
		/*if (roleService.findRole(role.getRoleName()) != null) {
			return new ExtAjaxResponse(false, "该角色已经存在不用再添加");
		}*/
		try {
			roleService.saveOrUpdate(role);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}
	@RequestMapping("/savePermission")
	@SysControllerLog(module="角色管理",methods="添加权限")
	public @ResponseBody ExtAjaxResponse savePermission(PermissionQueryDTO permissionQueryDTO,@RequestParam Long permissionId,@RequestParam Long roleId) {
		Role role = roleService.findOne(roleId);
		System.out.println(role+"+++++++");
		Permission permission = permissionService.findOne(permissionId);		
		permission.getRole().add(role);
		Role role2 = new Role();
		role2.getPermission().add(permission);
		//role.getPermission().add(permission);
		try {
			permissionService.saveOrUpdate(permission);	
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}	
	@RequestMapping("/update")
	@SysControllerLog(module="角色管理",methods="修改角信息")
	public @ResponseBody ExtAjaxResponse update(Role role) {
		/*if (collegeService.findCollege(college.getCollegeName()) != null) {
			return new ExtAjaxResponse(false, "该部门已经存在 不用再添加");
		}*/	
		Role r = roleService.findOne(role.getId());
		r.setRoleName(role.getRoleName());
		r.setOrderNo(role.getOrderNo());
		r.setRemark(role.getRemark());
		r.setCreateBy(role.getCreateBy());
		r.setCreateCollege(role.getCreateCollege());
		r.setCreateTime(role.getCreateTime());
		r.setUpdateBy(role.getUpdateBy());
		r.setUpdateTime(role.getUpdateTime());
		r.setState(role.getState());
		try {
			roleService.saveOrUpdate(r);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}	
	@RequestMapping("/delete")
//	@RequiresPermissions("role/delete")
//	@RequiresRoles("管理员")
	@SysControllerLog(module="角色管理",methods="删除角色")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long id) {
		try {
			Role role = roleService.findOne(id);
			
			if (role != null) {
				if (role.getUsers() != null&&role.getPermission() != null) {
					role.setUsers(null);
					role.setPermission(null);
				}
				if (role.getPermission() != null) {
					role.setPermission(null);
				}
				
				roleService.delete(role);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}		
	}
	@RequestMapping("/deleteRoles")
//	@RequiresPermissions("role/deleteRoles")
//	@RequiresRoles("管理员")
	@SysControllerLog(module="角色管理",methods="批量删除角色")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long[] ids) {
		try {
			List<Long> idsLists = Arrays.asList(ids);
			for(Long id : idsLists) {
				roleService.findOne(id).setUsers(null);
				roleService.findOne(id).setPermission(null);
			}
			if (idsLists != null) {
				roleService.delete(idsLists);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}
	}	
	@RequestMapping("/findOne")
	@SysControllerLog(module="角色管理",methods="查看角色信息")
	public @ResponseBody Role findOne(@RequestParam Long id) {
		Role role = roleService.findOne(id);
		return role;
	}	
	
	@RequestMapping("/findAll")
	public @ResponseBody ExtJsonResult<Role> findAll(HttpSession session){
		List<Role> roleLists = roleService.findRole();
		session.setAttribute("roleLists", roleLists);
		System.out.println("roleLists:"+roleLists);
		return new ExtJsonResult<Role>(roleLists);
	}
	@RequestMapping("/findPage")
	@SysControllerLog(module="角色管理",methods="查看角色管理列表")
	public String findPage(@RequestParam(value="query",required=false, defaultValue="") String query,HttpSession session,RoleQueryDTO roleQueryDTO,ExtPageable extPageable){
		roleQueryDTO.setRoleName(query);
		roleQueryDTO.setRemark(query);
		Page<Role> page = roleService.findAll(roleQueryDTO.getSpecification(roleQueryDTO), extPageable.getPageable());
		session.setAttribute("roleLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/system/role";
		
	}
	/*
	@RequestMapping("/findRolePermission")
	public @ResponseBody ExtJsonResult<RolePermissionQueryDTO> findRolePermission(){
		List<RolePermissionQueryDTO> findRolePermission = rolePermissionService.findRolePermission();
		return new ExtJsonResult<RolePermissionQueryDTO>(findRolePermission);
	}
	@RequestMapping("/findPermission")
	public @ResponseBody ExtJsonResult<RolePermissionQueryDTO> findPermission(){
		List<RolePermissionQueryDTO> findPermission = rolePermissionService.findPermission();
		return new ExtJsonResult<RolePermissionQueryDTO>(findPermission);
	}*/
	
	@RequestMapping("disableRole")
	@SysControllerLog(module="角色管理",methods="禁用角色")
	public @ResponseBody Role disableRole(@RequestParam Long id) {
		Role role = roleService.findOne(id);
		if (role.getState() != null) {
			roleService.updateState(id, 0);
		}
		return role;
	}
	@RequestMapping("enableRole")
	@SysControllerLog(module="角色管理",methods="启用角色")
	public @ResponseBody Role enableRole(@RequestParam Long id) {
		Role role = roleService.findOne(id);
		if (role.getState() != null) {
			roleService.updateState(id, 1);
		}
		return role;
	}

}
