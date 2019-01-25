package com.volunteer.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午11:18:14
 *
 */


public class ShiroExceptionResolver implements HandlerExceptionResolver {

   
	

	//@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// TODO Auto-generated method stub
		 if(ex instanceof UnauthorizedException) {
			 ModelAndView view = new ModelAndView(new MappingJackson2JsonView());  
		        view.addObject("success", false);  
		        view.addObject("msg", "您无权限,请跟超级管理员申请权限");  
		        return view;  
	            
	        }
	        return null;
		
	}

}
