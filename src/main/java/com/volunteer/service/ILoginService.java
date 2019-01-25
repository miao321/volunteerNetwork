package com.volunteer.service;


import com.xxx.volunterNetwork.domain.User;
/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:31:41
 *
 */
public interface ILoginService {
	//登录注销、修改密码
	public User login(String userName,String password);
	public void changePassword(Long id,String password,String comfirPassword);
	public User logout();
	public boolean isLogined();
	public User findUser(String userName); 

}
