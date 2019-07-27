package com.gt.home.utils;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * className:ShiroConfig
 * discriptoin:
 * author:
 * createTime:2018-11-29 13:17
 */
@Configuration
public class ShiroConfig {

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//拦截器.
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		// 配置不会被拦截的链接 顺序判断
		//放开static resources
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		//验证token
		filterChainDefinitionMap.put("/checkToken", "anon");
		//放开登录注册以及首页
		filterChainDefinitionMap.put("/login", "anon");

		//未登录放开首页贷款信息
	/*	filterChainDefinitionMap.put("/indexInformation","anon");
		filterChainDefinitionMap.put("/indexInformationDiYa","anon");
		//未登录放开常见问题
		filterChainDefinitionMap.put("/toproblem","anon");
		//前台公告放开
		filterChainDefinitionMap.put("/getNoteceListMap","anon");
		filterChainDefinitionMap.put("/toNoticelist","anon");
		//未登录放开贷款页信息
		filterChainDefinitionMap.put("/toInvest", "anon");
		filterChainDefinitionMap.put("/toBorrow", "anon");
		filterChainDefinitionMap.put("/toDetail", "anon");
		filterChainDefinitionMap.put("/invest", "anon");
		filterChainDefinitionMap.put("/investment", "anon");
		filterChainDefinitionMap.put("/investmentAmount", "anon");
		filterChainDefinitionMap.put("/investmentInformation", "anon");
		filterChainDefinitionMap.put("/user/**", "anon");
		filterChainDefinitionMap.put("/record/**", "anon");
		filterChainDefinitionMap.put("/getSessoinUserInfo", "anon");
		//未登录放开锦囊
		filterChainDefinitionMap.put("/tojinNang","anon");
		filterChainDefinitionMap.put("/getJinNangListMap","anon");
		filterChainDefinitionMap.put("/getJinNangXinXi","anon");
		filterChainDefinitionMap.put("/getCommentList","anon");*/
		//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		//<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");
		// 如果不设置默认会自动寻找Web工程根目录下的"/login"页面
		shiroFilterFactoryBean.setLoginUrl("/abackstage/login.html");
		//shiroFilterFactoryBean.setLoginUrl("/afront/user/login.html");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/abackstage/emp/login.html");

		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;

	}

	@Bean
	public MyShiroRealm myShiroRealm(){
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}


	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}



}
