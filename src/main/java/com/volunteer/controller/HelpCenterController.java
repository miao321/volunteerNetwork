package com.volunteer.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xxx.volunterNetwork.domain.Acti;
import com.xxx.volunterNetwork.domain.Enroll;
import com.xxx.volunterNetwork.domain.Img;
import com.xxx.volunterNetwork.service.IActiService;
import com.xxx.volunterNetwork.service.IEnrollService;
import com.xxx.volunterNetwork.service.IImgService;

@Controller
public class HelpCenterController {
	@Autowired
	private IImgService imgService;
	@Autowired
	private IActiService actiService;
	@Autowired
	private IEnrollService enrollService;
	@RequestMapping("/helpCenter")
	public String index(HttpSession session) {
		List<Img> imgs = imgService.findImg();		
		List<Acti> actis9 = actiService.findActi9();		
		List<Enroll> enrolls = enrollService.findEnroll();
		
		session.setAttribute("imgLists", imgs);	
		session.setAttribute("actiLists9", actis9);
		session.setAttribute("enrollLists", enrolls);
		return "/WEB-INF/pages/front/helpCenter";
	}
		
	
}
