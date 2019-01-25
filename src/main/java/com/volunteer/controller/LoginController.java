package com.volunteer.controller;



import java.security.NoSuchAlgorithmException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xxx.volunterNetwork.anno.SysControllerLog;
import com.xxx.volunterNetwork.domain.Role;
import com.xxx.volunterNetwork.domain.User;
import com.xxx.volunterNetwork.service.ILoginService;
import com.xxx.volunterNetwork.service.IUserService;
import com.xxx.volunterNetwork.util.ExtAjaxResponse;
import com.xxx.volunterNetwork.util.ExtResponse;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午11:03:31
 *
 */


@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private ILoginService loginService;
	@Autowired
	private IUserService userService;
	@RequestMapping(value="/login")
	@SysControllerLog(module="系统登录",methods="登录系统")
	public String login(HttpServletRequest request,HttpSession session) throws Exception {		
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {			
                session.setAttribute("result", "账号不存在");
			} else if (IncorrectCredentialsException.class.getName().equals(
					exceptionClassName)) {				
				session.setAttribute("result2", "用户名/密码错误");
			} else{
				//最终在异常处理器生成未知错误			
				session.setAttribute("result3", "未知错误");
			}
		}
		return "login";
	}
    @RequestMapping(value="backstage")
    @SysControllerLog(module="系统登录",methods="登录系统")
    public String backstage(HttpSession session)throws Exception{	
		//从shiro的session中取User
		Subject subject = SecurityUtils.getSubject();
		
		//取身份信息
		String userName =  (String) subject.getPrincipal();
		Set<Role> roles = userService.getRoleByUserName(userName);
		session.setAttribute("userName", userName);
		
		User user = loginService.findUser(userName);
		session.setAttribute("user", user);
		session.setAttribute("userName", user.getUserName());
		session.setAttribute("userId", user.getId());
		session.setAttribute("password", user.getPassword());
		session.setAttribute("studentNo", user.getStudentNo());
		/*if (user.getState() == 0) {
			session.setAttribute("result4", "该用户已被禁用，请联系管理员");
			//return "redirect:/login.jsp"; 
		}*/
		boolean root = false;
		for(Role role : roles) {
			if (role.getRoleName().equals("管理员") || role.getRoleName().equals("超级管理员")) {
				root = true;
				break;
			}
		}
		return root ? "WEB-INF/pages/backstage" : "WEB-INF/pages/volunterNetwork";
		
	}	
	//退出系统	
	@RequestMapping(value="/logout",method=RequestMethod.GET) 
	@SysControllerLog(module="退出系统",methods="退出系统")
    public String logout(RedirectAttributes attr,HttpSession session){  
        //使用权限管理工具进行用户的退出，注销登录  
		 //session.invalidate();
        SecurityUtils.getSubject().logout(); 
       
        //addMessage(attr, "您已安全退出");  
        return "redirect:/login.jsp";  
    }  
	//判断是否已经登录   ??有点问题
	@RequestMapping("/isLogined")
	public @ResponseBody ExtAjaxResponse isLogined() {
		try {
			loginService.isLogined();
			return new ExtAjaxResponse(true, "用户已登录");
		} catch (Exception e) {
			return new ExtAjaxResponse(false, "");
		}
	}
}