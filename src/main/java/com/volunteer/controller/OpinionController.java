package com.volunteer.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.xxx.volunterNetwork.domain.Opinion;
import com.xxx.volunterNetwork.dto.OpinionQueryDTO;
import com.xxx.volunterNetwork.service.IOpinionService;
import com.xxx.volunterNetwork.service.IPermissionService;
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
@RequestMapping("/opinion")
public class OpinionController {
	private static final Logger logger = LoggerFactory.getLogger(OpinionController.class);
	@Autowired
	private IOpinionService opinionService;
	@Autowired
	private IPermissionService permissionService;
	
	@RequestMapping("/saveOrUpdate")
//	@RequiresPermissions("opinion/saveOrUpdate")
//	@RequiresOpinions("管理员")
	public @ResponseBody ExtAjaxResponse saveOrUpdate(Opinion opinion,HttpSession session) {
		
		/*if (opinionService.findOpinion(opinion.getOpinionName()) != null) {
			return new ExtAjaxResponse(false, "该角色已经存在不用再添加");
		}*/
		String userName = (String) session.getAttribute("studentNo");
		opinion.setAuthor(userName);
		opinion.setFbtime(new Date(System.currentTimeMillis()));
		try {
			opinionService.saveOrUpdate(opinion);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}
	
	@RequestMapping("/delete")
	//@RequiresPermissions("log/delete")
	@RequiresRoles("超级管理员")
	@SysControllerLog(module="意见反馈",methods="删除意见")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long id) {
		Opinion opinion = opinionService.findOne(id);
		try {			
			opinionService.delete(opinion);			
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}		
	}
	@RequestMapping("/deleteOpinions")
//	@RequiresPermissions("opinion/deleteOpinions")
	@RequiresRoles("超级管理员")
	@SysControllerLog(module="意见反馈",methods="批量删除意见")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long[] ids) {
		try {
			List<Long> idsLists = Arrays.asList(ids);
			if (idsLists != null) {
				opinionService.delete(idsLists);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}
	}	
	@RequestMapping("/findOne")
	@SysControllerLog(module="意见反馈",methods="查看意见信息")
	public @ResponseBody Opinion findOne(@RequestParam Long id) {
		Opinion opinion = opinionService.findOne(id);
		return opinion;
	}	
	
	@RequestMapping("/findAll")
	public @ResponseBody ExtJsonResult<Opinion> findAll(HttpSession session){
		List<Opinion> opinionLists = opinionService.findAll();
		session.setAttribute("opinionLists", opinionLists);
		System.out.println("opinionLists:"+opinionLists);
		return new ExtJsonResult<Opinion>(opinionLists);
	}
	@RequestMapping("/findPage")
	@SysControllerLog(module="意见反馈",methods="查看意见反馈列表")
	public String findPage(@RequestParam(value="query",required=false, defaultValue="") String query,HttpSession session,OpinionQueryDTO opinionQueryDTO,ExtPageable extPageable){
		opinionQueryDTO.setAuthor(query);
		opinionQueryDTO.setContent(query);
		opinionQueryDTO.setTitle(query);
		Page<Opinion> page = opinionService.findAll(opinionQueryDTO.getSpecification(opinionQueryDTO), extPageable.getPageable());
		session.setAttribute("opinionLists", page.getContent());//内容
		System.out.println("page.getContent()===="+page.getContent());
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/blog/opinion";
		
	}
	/*
	@RequestMapping("/findOpinionPermission")
	public @ResponseBody ExtJsonResult<OpinionPermissionQueryDTO> findOpinionPermission(){
		List<OpinionPermissionQueryDTO> findOpinionPermission = opinionPermissionService.findOpinionPermission();
		return new ExtJsonResult<OpinionPermissionQueryDTO>(findOpinionPermission);
	}
	@RequestMapping("/findPermission")
	public @ResponseBody ExtJsonResult<OpinionPermissionQueryDTO> findPermission(){
		List<OpinionPermissionQueryDTO> findPermission = opinionPermissionService.findPermission();
		return new ExtJsonResult<OpinionPermissionQueryDTO>(findPermission);
	}*/
	
	@RequestMapping("disableOpinion")
	@SysControllerLog(module="意见反馈",methods="禁用意见")
	public @ResponseBody Opinion disableOpinion(@RequestParam Long id) {
		Opinion opinion = opinionService.findOne(id);
		if (opinion.getState() != null) {
			opinionService.updateState(id, 0);
		}
		return opinion;
	}
	@RequestMapping("enableOpinion")
	@SysControllerLog(module="意见反馈",methods="启用意见")
	public @ResponseBody Opinion enableOpinion(@RequestParam Long id) {
		Opinion opinion = opinionService.findOne(id);
		if (opinion.getState() != null) {
			opinionService.updateState(id, 1);
		}
		return opinion;
	}

}
