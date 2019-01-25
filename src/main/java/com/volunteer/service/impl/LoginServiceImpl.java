package com.volunteer.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xxx.volunterNetwork.anno.SysLog;
import com.xxx.volunterNetwork.dao.UserRepository;
import com.xxx.volunterNetwork.domain.User;
import com.xxx.volunterNetwork.service.ILoginService;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:43:13
 *
 */

@Service
@Transactional
public class LoginServiceImpl implements ILoginService {
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);	
	@Autowired
	private UserRepository userRepository;
	@SysLog(module="系统登录",methods="登录")
	public User login(String userName, String password) {
		return userRepository.findUser(userName);		
	}
	@SysLog(module="用户管理",methods="修改密码")
	public void changePassword(Long id, String password, String comfirPassword) {
//		System.out.println(id);
//		System.out.println(comfirPassword);
		 userRepository.updateUser(id, comfirPassword);
	}
	@SysLog(module="用户管理",methods="退出系统")
	public User logout() {
		return null;
	}	
	public boolean isLogined() {
		/*Object userName = session.getAttribute(LOGIN_KEY);
		return userName != null&&!("".equals(userName));*/
		return true;
	}
	@SysLog(module="用户管理",methods="查找用户")
	public User findUser(String userName) {
		
		return userRepository.findUser(userName);
	}	
}
