package com.volunteer.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.volunterNetwork.anno.SysControllerLog;
import com.xxx.volunterNetwork.domain.Enroll;
import com.xxx.volunterNetwork.domain.User;
import com.xxx.volunterNetwork.dto.EnrollQueryDTO;
import com.xxx.volunterNetwork.service.IEnrollService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtPageable;
import com.xxx.volunterNetwork.util.ResultMap;



@Controller
@RequestMapping("/enroll")
public class EnrollController {
	private static final Logger logger = LoggerFactory.getLogger(EnrollController.class);
	@Autowired
	private IEnrollService enrollService;
	
	String sPath = System.getProperty("evan.webapp");
	@RequestMapping("/saveOrUpdate")
	public @ResponseBody ExtAjaxResponse saveOrUpdate(Enroll enroll) {
		
		/*if (enrollService.findEnroll(enroll.getEnrollName()) != null) {
			return new ExtAjaxResponse(false, "该模块已经存在不用再添加");
		}*/
		try {
			enrollService.saveOrUpdate(enroll);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}
	@RequestMapping("/update")
	public @ResponseBody ExtAjaxResponse update(Enroll enroll) {
		/*if (collegeService.findCollege(college.getCollegeName()) != null) {
			return new ExtAjaxResponse(false, "该部门已经存在 不用再添加");
		}*/
		Enroll m = enrollService.findOne(enroll.getId());
		
		try {
			enrollService.saveOrUpdate(m);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}	
	@RequestMapping("/delete")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long id) {
		try {
			Enroll enroll = enrollService.findOne(id);
			if (enroll != null) {
				enrollService.delete(enroll);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}		
	}
	@RequestMapping("/deleteEnrolls")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long[] ids) {
		try {
			List<Long> idsLists = Arrays.asList(ids);
			if (idsLists != null) {
				enrollService.delete(idsLists);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}
	}	
	@RequestMapping("/findOne")
	public @ResponseBody Enroll findOne(@RequestParam Long id,HttpSession session) {
		Enroll enroll = enrollService.findOne(id);
		session.setAttribute("enroll", enroll);
		return enroll;
	}	
	@RequestMapping("/findAll")
	public @ResponseBody List<Enroll> findAll(){
		List<Enroll> enrollLists = enrollService.findAll();
		return enrollLists;		
	}	
	/*@RequestMapping("/findPage")
	@SysControllerLog(module="模块管理",methods="查找所有数据并分页排序")
	public @ResponseBody ExtAjaxResponse findPage(HttpSession session,EnrollQueryDTO enrollQueryDTO,ExtPageable extPageable){
		Page<Enroll> page = enrollService.findAll(enrollQueryDTO.getSpecification(enrollQueryDTO), extPageable.getPageable());
		System.out.println("enrollLists:+++"+page.getContent());
		session.setAttribute("enrollLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return new ExtAjaxResponse(true, "操作成功");
	}*/
	@RequestMapping("/findPage")
	@SysControllerLog(module="模块管理",methods="查找所有数据并分页排序")
	public String findPage(@RequestParam(value="query",required=false, defaultValue="") String query,HttpSession session,EnrollQueryDTO enrollQueryDTO,ExtPageable extPageable){
		enrollQueryDTO.setUserName(query);
		enrollQueryDTO.setCollege(query);
		enrollQueryDTO.setStudentNo(query);
		enrollQueryDTO.setHdName(query);
		Page<Enroll> page = enrollService.findAll(enrollQueryDTO.getSpecification3(enrollQueryDTO), extPageable.getPageable());
		session.setAttribute("enrollLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/system/enroll";
	}
	
	@RequestMapping("disableEnroll")
	public @ResponseBody Enroll disableEnroll(@RequestParam Long id) {
		Enroll enroll = enrollService.findOne(id);
		if (enroll.getState() != null) {
			enrollService.updateState(id, 0);
		}
		return enroll;
	}
	@RequestMapping("enableEnroll")
	public @ResponseBody Enroll enableEnroll(@RequestParam Long id) {
		Enroll enroll = enrollService.findOne(id);
		if (enroll.getState() != null) {
			enrollService.updateState(id, 1);
		}
		return enroll;
	}
}
