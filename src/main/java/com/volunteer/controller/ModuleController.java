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
import com.xxx.volunterNetwork.domain.Organization;
import com.xxx.volunterNetwork.domain.Module;
import com.xxx.volunterNetwork.domain.Permission;
import com.xxx.volunterNetwork.dto.ModuleQueryDTO;
import com.xxx.volunterNetwork.service.IModuleService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtPageable;



@Controller
@RequestMapping("/module")
public class ModuleController {
	private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);
	@Autowired
	private IModuleService moduleService;
	String sPath = System.getProperty("evan.webapp");
	@RequestMapping("/saveOrUpdate")
	public @ResponseBody ExtAjaxResponse saveOrUpdate(Module module) {
		
		/*if (moduleService.findModule(module.getModuleName()) != null) {
			return new ExtAjaxResponse(false, "该模块已经存在不用再添加");
		}*/
		try {
			moduleService.saveOrUpdate(module);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}
	@RequestMapping("/update")
	public @ResponseBody ExtAjaxResponse update(Module module) {
		/*if (collegeService.findCollege(college.getCollegeName()) != null) {
			return new ExtAjaxResponse(false, "该部门已经存在 不用再添加");
		}*/
		Module m = moduleService.findOne(module.getId());
		
		try {
			moduleService.saveOrUpdate(m);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}	
	@RequestMapping("/delete")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long id) {
		try {
			Module module = moduleService.findOne(id);
			if (module != null) {
				moduleService.delete(module);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}		
	}
	@RequestMapping("/deleteModules")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long[] ids) {
		try {
			List<Long> idsLists = Arrays.asList(ids);
			if (idsLists != null) {
				moduleService.delete(idsLists);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}
	}	
	@RequestMapping("/findOne")
	public @ResponseBody Module findOne(@RequestParam Long id,HttpSession session) {
		Module module = moduleService.findOne(id);
		session.setAttribute("module", module);
		return module;
	}	
	@RequestMapping("/findAll")
	public @ResponseBody List<Module> findAll(){
		List<Module> moduleLists = moduleService.findAll();
		return moduleLists;		
	}	
	@RequestMapping("/findPage")
	@SysControllerLog(module="模块管理",methods="查找所有数据并分页排序")
	public String findPage(@RequestParam(value="query",required=false, defaultValue="") String query,HttpSession session,ModuleQueryDTO moduleQueryDTO,ExtPageable extPageable){
		moduleQueryDTO.setModuleName(query);
		moduleQueryDTO.setParentName(query);
		Page<Module> page = moduleService.findAll(moduleQueryDTO.getSpecification(moduleQueryDTO), extPageable.getPageable());
		session.setAttribute("moduleLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/system/module";	
	}

	@RequestMapping("disableModule")
	public @ResponseBody Module disableModule(@RequestParam Long id) {
		Module module = moduleService.findOne(id);
		if (module.getState() != null) {
			moduleService.updateState(id, 0);
		}
		return module;
	}
	@RequestMapping("enableModule")
	public @ResponseBody Module enableModule(@RequestParam Long id) {
		Module module = moduleService.findOne(id);
		if (module.getState() != null) {
			moduleService.updateState(id, 1);
		}
		return module;
	}

}
