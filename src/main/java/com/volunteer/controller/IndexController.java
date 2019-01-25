package com.volunteer.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.domain.Borad;
import com.xxx.volunterNetwork.domain.Enroll;
import com.xxx.volunterNetwork.domain.Img;
import com.xxx.volunterNetwork.domain.Organization;
import com.xxx.volunterNetwork.domain.Share;
import com.xxx.volunterNetwork.domain.User;
import com.xxx.volunterNetwork.dto.ActiQueryDTO;
import com.xxx.volunterNetwork.dto.CommentQueryDTO;
import com.xxx.volunterNetwork.dto.EnrollQueryDTO;
import com.xxx.volunterNetwork.dto.OrganizationQueryDTO;
import com.xxx.volunterNetwork.service.IActiService;
import com.xxx.volunterNetwork.service.IBoradService;
import com.xxx.volunterNetwork.service.ICommentService;
import com.xxx.volunterNetwork.service.IEnrollService;
import com.xxx.volunterNetwork.service.IImgService;
import com.xxx.volunterNetwork.service.IOrganizationService;
import com.xxx.volunterNetwork.service.IUserService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtPageable;

@Controller
public class IndexController {
	@Autowired
	private IImgService imgService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IActiService actiService;
	@Autowired
	private IEnrollService enrollService;
	@Autowired
	private IBoradService boradService;
	@Autowired
	private IOrganizationService organizationService;
	@Autowired
	private ICommentService commentService;
	
	//@RequestParam String hdlx,
	@RequestMapping("/volunterNetwork")
	public String index(HttpSession session,EnrollQueryDTO enrollQueryDTO,ExtPageable extPageable) {
		//从shiro的session中取User
		Subject subject = SecurityUtils.getSubject();
		//取身份信息
		String userName =  (String) subject.getPrincipal();
		User user = userService.findUser(userName);
		session.setAttribute("userName", userName);
		if(user != null) {
			session.setAttribute("studentNo", user.getStudentNo());
			session.setAttribute("userId", user.getId());
		}
		
		List<Img> imgs = imgService.findImg2();
		
		List<Acti> actis = actiService.findActi();
		List<Acti> actis2 = actiService.findActi2();
		List<Acti> actis3 = actiService.findActi3();
		List<Acti> actis4 = actiService.findActi4();
		List<Acti> actis5 = actiService.findActi5();
		List<Acti> actis6 = actiService.findActi6();
		List<Acti> actis7 = actiService.findActi7();
		List<Acti> actis8 = actiService.findActi8();
		List<Acti> actis9 = actiService.findActi9();
		List<Borad> borads = boradService.findInfo();
		List<Borad> borads2 = boradService.findCulture();
		List<Borad> borads3 = boradService.findMessage();
		
		Page<Enroll> page = enrollService.findAll(enrollQueryDTO.getSpecification(enrollQueryDTO), extPageable.getPageable3());
		
	
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		//List<Enroll> enrolls = enrollService.findEnroll();
		
		
		List<Organization> volunter = organizationService.findVolunter();
		List<EnrollQueryDTO> enrolls2 = organizationService.findEnroll();
		List<OrganizationQueryDTO> organization = organizationService.findNum();		
		Integer enroll = organizationService.findDuration();
		
		session.setAttribute("imgLists", imgs);
		session.setAttribute("actiLists", actis);
		session.setAttribute("actiLists2", actis2);
		session.setAttribute("actiLists3", actis3);
		session.setAttribute("actiLists4", actis4);
		session.setAttribute("actiLists5", actis5);
		session.setAttribute("actiLists6", actis6);
		session.setAttribute("actiLists7", actis7);
		session.setAttribute("actiLists8", actis8);
		session.setAttribute("actiLists9", actis9);
		session.setAttribute("boradLists", borads);
		session.setAttribute("boradLists2", borads2);
		session.setAttribute("boradLists3", borads3);
		session.setAttribute("enrollLists", page.getContent());
		session.setAttribute("volunterLists", volunter);
		session.setAttribute("enroll2Lists", enrolls2);
		session.setAttribute("organization", organization);
		session.setAttribute("enroll", enroll);
		return "/WEB-INF/pages/front/index";
	}
	@RequestMapping("/baoming/saveOrUpdate")
	public @ResponseBody ExtAjaxResponse saveOrUpdate(@RequestParam Long id,Enroll enroll,HttpSession session) {		
		Acti acti = actiService.findOne(id);
		String userName = (String) session.getAttribute("userName");
		if(userName == null) {
			return new ExtAjaxResponse(false, "请先登录!");
		}
		Enroll enroll2 = enrollService.findUser(userName);
		if (enroll2 != null) {
			return new ExtAjaxResponse(false, "你已报名，乖，静静的等通知！");
		}
		User user = userService.findUser(userName);
   	 	enroll.setUserName(user.getUserName());
		enroll.setStudentNo(user.getStudentNo());
		enroll.setSex(user.getSex());
		enroll.setPhone(user.getPhone());
		enroll.setCollege(user.getCollege());
		//enroll.setMajor(user.getMajor());
		enroll.setIdCard(user.getIdCard());
		enroll.setImg(acti.getImg());
		enroll.setHdName(acti.getTitle());
		enroll.setHdType(acti.getHdlx());
		enroll.setHdTime(acti.getBeginTime());
		enroll.setState(0);
		enroll.setOrganization(acti.getFbzz());
		enroll.setActiId(acti.getId());
		enroll.setRemark("恭喜你成功报名此次活动，请注意查看手机消息。");
		enroll.setDuration(acti.getDuration());
		try {		
			enrollService.saveOrUpdate(enroll);
			return new ExtAjaxResponse(true, "报名成功，请等待信息。");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "添加数据失败");
		}	
	}
	@RequestMapping("/organization")
	public String organization(HttpSession session) {
		//List<Organization> organizationLists = organizationService.findAll();
		//List<OrganizationQueryDTO> organization = organizationService.findOrganization1();
		//session.setAttribute("organization", organization);
		//organizationLists.add(e)
		List<OrganizationQueryDTO> organizationLists = organizationService.findOrganization();
		session.setAttribute("organizationLists", organizationLists);
		return "WEB-INF/pages/front/organization";
	}
	@RequestMapping("/xjorganization")
	public String xjorganization(@RequestParam Long id,HttpSession session) {
		Organization organization = organizationService.findOne(id);
		session.setAttribute("organization", organization);
		return "/WEB-INF/pages/front/xjorganization";
	}	
	@RequestMapping(value="/sort",method=RequestMethod.GET)
	public String sort(@RequestParam String hdlx,@RequestParam(value="query",required=false, defaultValue="") String query,HttpSession session,ActiQueryDTO actiQueryDTO,ExtPageable extPageable) {					
		session.setAttribute("hdlx", hdlx);
		actiQueryDTO.setQuery(query);
		actiQueryDTO.setHdlx(hdlx);  
		actiQueryDTO.setTitle(query);
		actiQueryDTO.setFbzz(query); 
		actiQueryDTO.setXxdz(query);
		Page<Acti> page = actiService.findAll(actiQueryDTO.getSpecification2(actiQueryDTO), extPageable.getPageable2());
		session.setAttribute("actiLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/front/sort";
	}
	@RequestMapping("/detail")
	public String findOne(@RequestParam Long id,HttpSession session) {
		Acti acti = actiService.findOne(id);
		session.setAttribute("actiId", id);
		session.setAttribute("acti", acti);		
		List<CommentQueryDTO> commentList = commentService.findComment2(acti.getId());
		session.setAttribute("commentList", commentList);
		return "/WEB-INF/pages/front/detail";
	}		
	@RequestMapping(value="/pageDetail",method=RequestMethod.GET)
	public String pageDetail(@RequestParam(value="query",required=false, defaultValue="") String query,HttpSession session,ActiQueryDTO actiQueryDTO,ExtPageable extPageable) {
		actiQueryDTO.setQuery(query);		
		actiQueryDTO.setTitle(query);
		actiQueryDTO.setFbzz(query); 
		actiQueryDTO.setXxdz(query);
		Page<Acti> page = actiService.findAll(actiQueryDTO.getSpecification3(actiQueryDTO), extPageable.getPageable2());
		session.setAttribute("actiLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数		
		return "/WEB-INF/pages/front/volunterPage";
	}
	@RequestMapping(value="/duration",method=RequestMethod.GET)
	public String duration(@RequestParam(value="query",required=false, defaultValue="") String query,HttpSession session,ActiQueryDTO actiQueryDTO,ExtPageable extPageable) {
		actiQueryDTO.setQuery(query);		
		actiQueryDTO.setTitle(query);
		actiQueryDTO.setFbzz(query); 
		actiQueryDTO.setXxdz(query);
		Page<Acti> page = actiService.findAll(actiQueryDTO.getSpecification3(actiQueryDTO), extPageable.getPageable2());
		session.setAttribute("actiLists", page.getContent());//内容
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/front/duration";
	}
	@RequestMapping("/durationDetail")
	public String durationDetail(@RequestParam Long id,HttpSession session,EnrollQueryDTO enrollQueryDTO,ExtPageable extPageable) {
		Acti acti = actiService.findOne(id);
		enrollQueryDTO.setActiId(id);
		Page<Enroll> page = enrollService.findAll(enrollQueryDTO.getSpecification(enrollQueryDTO), extPageable.getPageable());
		//List<Enroll> enrollLists = enrollService.findEnroll(id);
		session.setAttribute("acti", acti);		
		session.setAttribute("enrollLists", page.getContent());
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/front/durationDetail";
	}	
	@RequestMapping("/durationDetail2")
	public String durationDetail2(HttpSession session,EnrollQueryDTO enrollQueryDTO,ExtPageable extPageable) {
		
		Page<Enroll> page = enrollService.findAll(enrollQueryDTO.getSpecification(enrollQueryDTO), extPageable.getPageable());	
		session.setAttribute("enrollLists", page.getContent());
		session.setAttribute("pageNumber", page.getNumber());//当前页
		session.setAttribute("pageSize", page.getSize());//当前页条数
		session.setAttribute("pageTotalPages", page.getTotalPages());//共几页
		session.setAttribute("pageTotalElements", page.getTotalElements());//总条数
		return "/WEB-INF/pages/front/durationDetail";
	}	
	@RequestMapping("addZan")
	public @ResponseBody ExtAjaxResponse addZan(@RequestParam Long id) {
		Acti acti = actiService.findOne(id);
		if(acti.getZan() == null) {
			acti.setZan(1);
		}else {
			acti.setZan(acti.getZan()+1);
		}
		actiService.saveOrUpdate(acti);
		return new ExtAjaxResponse(true, "操作成功");
	} 
	@RequestMapping("addAttention")
	public @ResponseBody ExtAjaxResponse addAttention(@RequestParam Long id,HttpSession session) {
		String userName = (String) session.getAttribute("userName");
		Acti acti = actiService.findOne(id);
		if (acti.getAttention() == null) {
			acti.setAttention(1);
		}else {
			acti.setAttention(acti.getAttention()+1);
		}
		acti.setAttentionName(userName);
		actiService.saveOrUpdate(acti);
		return new ExtAjaxResponse(true, "操作成功");
	}
	
	@RequestMapping("/voluntaryInfo")
	public String voluntaryInfo(@RequestParam Long id,HttpSession session) {
		Borad borad = boradService.findOne(id);
		List<Borad> borads = boradService.findInfo();
		session.setAttribute("boradLists", borads);
		session.setAttribute("borad", borad);
		return "/WEB-INF/pages/borad/voluntaryInfo";
	}
	@RequestMapping("/voluntaryCulture")
	public String voluntaryCulture(@RequestParam Long id,HttpSession session) {
		Borad borad1 = boradService.findOne(id);
		List<Borad> borads2 = boradService.findCulture();
		session.setAttribute("borad1", borad1);
		session.setAttribute("boradLists2", borads2);
		return "/WEB-INF/pages/borad/voluntaryCulture";
	}
	@RequestMapping("/messageBlog")
	public String messageBlog(@RequestParam Long id,HttpSession session) {
		Borad borad2 = boradService.findOne(id);
		List<Borad> borads3 = boradService.findMessage();
		session.setAttribute("borad2", borad2);
		session.setAttribute("boradLists3", borads3);
		return "/WEB-INF/pages/borad/messageBlog";
	}
	
}
