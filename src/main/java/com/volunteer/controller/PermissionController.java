package com.volunteer.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.xxx.volunterNetwork.domain.User;
import com.xxx.volunterNetwork.dto.PermissionQueryDTO;
import com.xxx.volunterNetwork.service.IPermissionService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtJsonResult;
import com.xxx.volunterNetwork.util.ExtPageable;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午11:03:19
 *
 */

@Controller
@RequestMapping("/permission")
public class PermissionController {
	private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
	@Autowired
	private IPermissionService permissionService;
	@RequestMapping("/saveOrUpdate")
	@SysControllerLog(module="权限管理",methods="添加权限")
	public @ResponseBody ExtAjaxResponse saveOrUpdate(Permission permission) {
		/*if (permissionService.findPermission(permission.getPermissionName()) != null) {
			return new ExtAjaxResponse(false, "该部门已经存在 不用再添加");
		}*/
		try {
			permissionService.saveOrUpdate(permission);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}
	@RequestMapping("/update")
	@SysControllerLog(module="权限管理",methods="修改权限信息")
	public @ResponseBody ExtAjaxResponse update(Permission permission) {
		/*if (permissionService.findPermission(permission.getPermissionName()) != null) {
			return new ExtAjaxResponse(false, "该部门已经存在 不用再添加");
		}*/
		Permission p = permissionService.findOne(permission.getId());
		p.setUrl(permission.getUrl());
		p.setToken(permission.getToken());
		p.setDescription(permission.getDescription());
		p.setState(permission.getState());
		try {
			permissionService.saveOrUpdate(p);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}	
	@RequestMapping("/delete")
	@SysControllerLog(module="权限管理",methods="删除单条权限")
	public String delete(@RequestParam Long id) {
		try {
			Permission permission = permissionService.findOne(id);
			permission.setRole(null);
			if (permission != null) {
				permissionService.delete(permission);
			}
			return "/WEB-INF/pages/system/permission";
		} catch (Exception e) {
			return "/WEB-INF/pages/system/permission";
		}		
	}
	@RequestMapping("/deletePermissions")
	@SysControllerLog(module="权限管理",methods="批量删除权限")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long ids[]) {
		try {
			List<Long> idsLists = Arrays.asList(ids);
			for(Long id : idsLists) {
				permissionService.findOne(id).setRole(null);
			}
			if (idsLists != null) {
				permissionService.delete(idsLists);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}
	}	
	@RequestMapping("/findOne")
	@SysControllerLog(module="权限管理",methods="查看权限信息")
	public @ResponseBody Permission findOne(@RequestParam Long id,HttpSession session) {		
		Permission permission = permissionService.findOne(id);
		session.setAttribute("permission", permission);
		return permission;
	}	
	@RequestMapping("/findAll")
	public String findAll(HttpSession session){
		List<Permission> permissionLists = permissionService.findPermission();
		System.out.println("permission:"+permissionLists);
		session.setAttribute("permissionLists", permissionLists);
		return "/WEB-INF/pages/system/permission";
	}
	@RequestMapping("/findAll2")
	public @ResponseBody ExtJsonResult<Permission> findAll2(HttpSession session){
		List<Permission> permissionLists = permissionService.findPermission();
		System.out.println("permission:"+permissionLists);
		session.setAttribute("permissionLists", permissionLists);
		return new ExtJsonResult<Permission>(permissionLists);
	}
	@RequestMapping("/findPage")
	@SysControllerLog(module="权限管理",methods="查找多条数据并分页排序")
	public String findPage(@RequestParam(value="query",required=false, defaultValue="") String query,HttpSession session,PermissionQueryDTO permissionQueryDTO,ExtPageable extPageable){
		permissionQueryDTO.setUrl(query);
		permissionQueryDTO.setDescription(query);
		Page<Permission> page = permissionService.findAll(permissionQueryDTO.getSpecification(permissionQueryDTO), extPageable.getPageable());	
		session.setAttribute("permissionLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/system/permission";
	}
	@RequestMapping("addPermission")
	public String addPermission() {
		return "/WEB-INF/pages/system/addPermission";
	}
	@RequestMapping("editPermission")
	public String editPermission() {
		return "/WEB-INF/pages/system/editPermission";
	}
	/*@RequestMapping("disablePermission")
	public @ResponseBody Permission disablePermission(@RequestParam Long id) {
		Permission permission = permissionService.findOne(id);
		if (permission.getState() != null) {
			permissionService.updateState(id, 0);
		}
		return permission;
	}
	@RequestMapping("enablePermission")
	public @ResponseBody Permission enablePermission(@RequestParam Long id) {
		Permission permission = permissionService.findOne(id);
		if (permission.getState() != null) {
			permissionService.updateState(id, 1);
		}
		return permission;
	}*/
	
	@RequestMapping("disablePermission")
	@SysControllerLog(module="权限管理",methods="禁用权限")
	public @ResponseBody Permission disablePermission(@RequestParam Long id) {
		Permission permission = permissionService.findOne(id);
		if (permission.getState() != null) {
			permissionService.updateState(id, 0);
		}
		return permission;
	}
	@RequestMapping("enablePermission")
	@SysControllerLog(module="权限管理",methods="启用权限")
	public @ResponseBody Permission enablePermission(@RequestParam Long id) {
		Permission permission = permissionService.findOne(id);
		if (permission.getState() != null) {
			permissionService.updateState(id, 1);
		}
		return permission;
	}

}
