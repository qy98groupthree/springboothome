package com.gt.home.utils;
import com.gt.home.entity.User;
import com.gt.home.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MyShiroRealm extends AuthorizingRealm implements Serializable{

	@Autowired
	private UserService userService;

	/**
	 * 登录认证
	 * 1、authenticationInfo：获取认证消息，如果数据库中没有，返回null，如果得到正确的用户名和密码
	 * 2、AuthenticationInfo  可用simpleAuthenticationInfo实现类，封装获取到的正确的账号和密码
	 * 返回正确类型的对象
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取username
		String account = (String)token.getPrincipal();
		Session session = SecurityUtils.getSubject().getSession();
		Map map = new HashMap();
		map.put("account",account);
		map.put("start",0);
		map.put("pageSize",100);
		//通过account查询
		List<Map> users = userService.queryUser(map);
		Map user =null;
		if(users!=null&&users.size()>0){
			user = users.get(0);
		}
		if(user==null) {
			throw  new UnknownAccountException(); //账号不存在异常
		}
		/*if (0==user.getEnable()) {
			throw new LockedAccountException(); // 帐号锁定
		}*/
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user,//用户名
				user.get("password"),//密码
				"MyRealm"//realm name
		);
		//设置盐
		//authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
		//当验证信息都通过后，把用户信息放在session里

		session.setAttribute("user",user);

		//session.setAttribute("userSessionId",user.getId());
		return authenticationInfo;
	}

	/**
	 * 授权
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		//全局通过SecurityUtils.getSubject().getPrincipal()获取用户信息
		User userInfo = (User) SecurityUtils.getSubject().getPrincipal();
		//通过用户名查出来角色
		//List<String> strings = userInfoService.selectRole(userInfo.getUserName());
		// 权限信息对象info,用来存放查出的用户的所有的角色（role）
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		/*for(String role:strings){
			//角色名称
			System.out.println(role);
			info.addStringPermission(role);
		}*/
		//返回角色名称
		return info;
	}
}
