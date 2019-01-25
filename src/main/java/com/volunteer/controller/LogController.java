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
import com.xxx.volunterNetwork.domain.Log;
import com.xxx.volunterNetwork.dto.LogQueryDTO;
import com.xxx.volunterNetwork.service.ILogService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtJsonResult;
import com.xxx.volunterNetwork.util.ExtPageable;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午11:03:05
 *
 */

@Controller
@RequestMapping("/log")
public class LogController {
	private static final Logger logger = LoggerFactory.getLogger(LogController.class);
	@Autowired
	private ILogService logService;
	@RequestMapping("/delete")
	@RequiresPermissions("log/delete")
	@RequiresRoles("超级管理员")
	@SysControllerLog(module="日志管理",methods="删除日志")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long id) {		
		try {
			Log log = logService.findOne(id);
			if (log != null) {
				logService.delete(log);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}		
	}
	@RequestMapping("/deleteLogs")
	@RequiresPermissions("log/deleteLogs")
	@RequiresRoles("超级管理员")
	@SysControllerLog(module="日志管理",methods="批量删除日志")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long[] ids) {
		try {
			List<Long> idsLists = Arrays.asList(ids);
			if (idsLists != null) {
				logService.delete(idsLists);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}
	}	
	@RequestMapping("/findPage")
	public String findPage(HttpSession session,LogQueryDTO logQueryDTO,ExtPageable extPageable){
		Page<Log> page = logService.findAll(logQueryDTO.getSpecification(logQueryDTO), extPageable.getPageable());
		session.setAttribute("logLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/system/log";
	}
}
