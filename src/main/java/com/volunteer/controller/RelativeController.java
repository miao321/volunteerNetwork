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
public class RelativeController {
	@Autowired
	private IImgService imgService;
	@Autowired
	private IActiService actiService;
	@Autowired
	private IEnrollService enrollService;
	@RequestMapping("/relative")
	public String index(HttpSession session) {
		List<Img> imgs = imgService.findImg();
		/*List<Acti> actis = actiService.findActi();
		List<Acti> actis2 = actiService.findActi2();
		List<Acti> actis3 = actiService.findActi3();
		List<Acti> actis4 = actiService.findActi4();
		List<Acti> actis5 = actiService.findActi5();
		List<Acti> actis6 = actiService.findActi6();
		List<Acti> actis7 = actiService.findActi7();
		List<Acti> actis8 = actiService.findActi8();*/
		List<Acti> actis9 = actiService.findActi9();		
		List<Enroll> enrolls = enrollService.findEnroll();
		
		session.setAttribute("imgLists", imgs);
		/*session.setAttribute("actiLists", actis);
		session.setAttribute("actiLists2", actis2);
		session.setAttribute("actiLists3", actis3);
		session.setAttribute("actiLists4", actis4);
		session.setAttribute("actiLists5", actis5);
		session.setAttribute("actiLists6", actis6);
		session.setAttribute("actiLists7", actis7);
		session.setAttribute("actiLists8", actis8);*/
		session.setAttribute("actiLists9", actis9);
		session.setAttribute("enrollLists", enrolls);
		return "/WEB-INF/pages/front/relative";
	}
	/*@RequestMapping("/detail")
	public String findOne(@RequestParam Long id,HttpSession session) {
		Acti acti = actiService.findOne(id);
		session.setAttribute("acti", acti);
		return "/WEB-INF/pages/front/detail";
	}		
	@RequestMapping("/pageDetail")
	public String pageDetail(HttpSession session) {
		List<Acti> actis = actiService.findActi();
		System.out.println("actis:++"+actis);
		session.setAttribute("actiLists", actis);
		return "/WEB-INF/pages/front/volunterPage";
	}	*/
}
