package com.volunteer.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.volunterNetwork.anno.SysControllerLog;
import com.xxx.volunterNetwork.domain.Borad;
import com.xxx.volunterNetwork.domain.Share;
import com.xxx.volunterNetwork.domain.User;
import com.xxx.volunterNetwork.dto.BoradQueryDTO;
import com.xxx.volunterNetwork.dto.CommentQueryDTO;
import com.xxx.volunterNetwork.dto.ShareQueryDTO;
import com.xxx.volunterNetwork.service.IActiService;
import com.xxx.volunterNetwork.service.IBoradService;
import com.xxx.volunterNetwork.service.ICommentService;
import com.xxx.volunterNetwork.service.IEnrollService;
import com.xxx.volunterNetwork.service.IImgService;
import com.xxx.volunterNetwork.service.IShareService;
import com.xxx.volunterNetwork.service.IUserService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtPageable;

@Controller
public class BlogController {
	@Autowired
	private IImgService imgService;
	@Autowired
	private IActiService actiService;
	@Autowired
	private IEnrollService enrollService;
	@Autowired
	private IShareService shareService;
	@Autowired 
	private ICommentService commentService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IBoradService boradService;
	
	@RequestMapping("/saveOrUpdate")
	public @ResponseBody ExtAjaxResponse saveOrUpdate(Share share,HttpSession session) {		
		String userName = (String) session.getAttribute("userName");
		if(userName == null) {
			return new ExtAjaxResponse(false, "请先登录!");
		}
		String author = (String) session.getAttribute("studentNo");
		Long userId = (Long) session.getAttribute("userId");
		User user = userService.findOne(userId);
		share.setState(0);
		share.setAuthor(author);
		share.setImg(user.getImg());
		share.setFbtime(new Date(System.currentTimeMillis()));
		
		try {		
			shareService.saveOrUpdate(share);
			return new ExtAjaxResponse(true, "发帖成功，等待审核。");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "添加数据失败");
		}	
	}
	@RequestMapping("/blog")
	public String index(@RequestParam(value="query",required=false, defaultValue="") String query,HttpSession session,ShareQueryDTO shareQueryDTO,ExtPageable extPageable) {
		shareQueryDTO.setAuthor(query);
		shareQueryDTO.setContent(query);
		shareQueryDTO.setTitle(query);
		shareQueryDTO.setState(1);
		Page<Share> page = shareService.findAll(shareQueryDTO.getSpecification(shareQueryDTO), extPageable.getPageable());
		List<Share> shareLists = shareService.findShare();
		session.setAttribute("shareLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		
		return "/WEB-INF/pages/blog/blog";
	}
	@RequestMapping("/blogDetail")
	public String findOne(@RequestParam Long id,HttpSession session) {
		Share share = shareService.findOne(id);		
		session.setAttribute("share", share);
		session.setAttribute("shareId", share.getId());
		List<CommentQueryDTO> commentList = commentService.findComment(share.getId());
		session.setAttribute("commentList", commentList);
		return "/WEB-INF/pages/blog/blogDetail";
	}
	@RequestMapping("/boradPage")
	@SysControllerLog(module="模块管理",methods="查找所有数据并分页排序")
	public String findPage(HttpSession session,BoradQueryDTO boradQueryDTO,ExtPageable extPageable){
		Page<Borad> page = boradService.findAll(boradQueryDTO.getSpecification(boradQueryDTO), extPageable.getPageable());
		System.out.println("actiLists:+++"+page.getContent());
		session.setAttribute("boradLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/borad/boradPage";	
	}
	
}
