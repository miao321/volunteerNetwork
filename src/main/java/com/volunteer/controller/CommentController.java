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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.volunterNetwork.anno.SysControllerLog;
import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.domain.Comment;
import com.xxx.volunterNetwork.domain.Module;
import com.xxx.volunterNetwork.dto.CommentQueryDTO;
import com.xxx.volunterNetwork.dto.ModuleQueryDTO;
import com.xxx.volunterNetwork.service.ICommentService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtPageable;
import com.xxx.volunterNetwork.util.ResultMap;
@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private ICommentService commentService;
	
	@RequestMapping("/saveOrUpdate")
	public @ResponseBody ExtAjaxResponse saveOrUpdate(Comment comment,HttpSession session) {		
		Long userId = (Long) session.getAttribute("userId");
		System.out.println("userId:"+userId);
		Long shareId = (Long) session.getAttribute("shareId");
		System.out.println("shareId:"+shareId);
		comment.setUserId(userId);
		comment.setParentId(shareId);
		comment.setRespTime(new Date(System.currentTimeMillis()));
		try {		
			commentService.saveOrUpdate(comment);
			return new ExtAjaxResponse(true, "添加数据成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "添加数据失败");
		}	
	}
	@RequestMapping("/saveActi")
	
	public @ResponseBody ExtAjaxResponse saveOr(@RequestParam String content,HttpSession session) {		
		Comment comment = new Comment();
		Long userId = (Long) session.getAttribute("userId");		
		Long actiId = (Long) session.getAttribute("actiId");
		comment.setContent(content);
		comment.setUserId(userId);
		comment.setParentId(actiId);
		comment.setRespTime(new Date(System.currentTimeMillis()));
		try {			
			commentService.saveOrUpdate(comment);
			return new ExtAjaxResponse(true, "添加数据成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "添加数据失败");
		}	
	}
	@RequestMapping("/save")
	public @ResponseBody ExtAjaxResponse save(Comment comment,HttpSession session) {		
		Long userId = (Long) session.getAttribute("userId");	
		comment.setUserId(userId);
		comment.setRespTime(new Date(System.currentTimeMillis()));
		try {		
			commentService.saveOrUpdate(comment);
			return new ExtAjaxResponse(true, "添加数据成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "添加数据失败");
		}	
	}
	@RequestMapping("/update")
	public @ResponseBody ExtAjaxResponse update(Comment comment) {
		/*if (collegeService.findCollege(college.getCollegeName()) != null) {
			return new ExtAjaxResponse(false, "该部门已经存在 不用再添加");
		}*/
		Comment b = commentService.findOne(comment.getId());
		
		try {
			commentService.saveOrUpdate(b);
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}	
	}	
	@RequestMapping("/delete")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long id) {
		try {
			Comment comment = commentService.findOne(id);
			if (comment != null) {
				commentService.delete(comment);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}		
	}
	@RequestMapping("/deletecomments")
	public @ResponseBody ExtAjaxResponse delete(@RequestParam Long[] ids) {
		try {
			List<Long> idsLists = Arrays.asList(ids);
			if (idsLists != null) {
				commentService.delete(idsLists);
			}
			return new ExtAjaxResponse(true, "操作成功");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "操作失败");
		}
	}	
	@RequestMapping("/findOne")
	public @ResponseBody Comment findOne(@RequestParam Long id,HttpSession session) {
		Comment comment = commentService.findOne(id);
		session.setAttribute("comment", comment);
		return comment;
	}	
	@RequestMapping("/findAll")
	public @ResponseBody List<Comment> findAll(){
		List<Comment>  commentLists = commentService.findAll();
		return  commentLists;		
	}	
	@RequestMapping("/findPage")
	@SysControllerLog(module="模块管理",methods="查找所有数据并分页排序")
	public String findPage(HttpSession session,CommentQueryDTO commentQueryDTO,ExtPageable extPageable){
		Page<Comment> page = commentService.findAll(commentQueryDTO.getSpecification(commentQueryDTO), extPageable.getPageable());
		session.setAttribute("commentLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/comment/seeComment";	
	}
	
	@RequestMapping("disablecomment")
	public @ResponseBody Comment disableComment(@RequestParam Long id) {
		Comment comment = commentService.findOne(id);
		if (comment.getState() != null) {
			commentService.updateState(id, 0);
		}
		return comment;
	}
	@RequestMapping("enablecomment")
	public @ResponseBody Comment enableComment(@RequestParam Long id) {
		Comment comment = commentService.findOne(id);
		if (comment.getState() != null) {
			commentService.updateState(id, 1);
		}
		return comment;
	}
}
