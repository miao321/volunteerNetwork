package com.volunteer.shiro;


import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.xxx.volunterNetwork.domain.Role;
import com.xxx.volunterNetwork.domain.User;
import com.xxx.volunterNetwork.service.ILoginService;
import com.xxx.volunterNetwork.service.IRoleService;
import com.xxx.volunterNetwork.service.IUserService;

/**
 * 
 * @author miao
 * @date 2018年1月20日上午10:33:52
 *
 */

public class MyRealm extends AuthorizingRealm{
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserService userService;
	public String getName() {
		return "myRealm";
	}
	
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		//获取输入的的身份信息
		String userName = (String) token.getPrincipal();
		//从数据库中去的传入的信息		
		//模拟差不到这个用户
		User user = loginService.findUser(userName);	
		if(user == null) {
			return null;
		}
		String password = user.getPassword();	
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, password, this.getName());
		return simpleAuthenticationInfo;
	}
	@SuppressWarnings("unchecked")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {		
		String userName = (String) principal.fromRealm(getName()).iterator().next();		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		if(userName != null) {
			Set<Role> role = userService.getRoleByUserName(userName);
			for (Role role2 : role) {
				info.addRole(role2.getRoleName());
			}
			List<String> permissions = userService.getPermissionsByUserName(userName);
			if (permissions != null && !permissions.isEmpty()) {
				for(String each : permissions) {
					info.addStringPermission(each);
					
					System.out.println(each);
				}
			}
		}
		System.out.println("++++++++:"+info);
		return info;
	}		
}
