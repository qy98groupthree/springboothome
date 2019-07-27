package com.gt.home.controller;

import com.gt.home.service.EmpService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 姜亚林
 * @date：2019/7/27 11:31
 */
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
    /**
     * 登陆方法
     * @param map
     * @return
     */
    @RequestMapping("/login")
    public Object login(@RequestBody Map map){
        String account= map.get("account")+"";
        String password = map.get("password")+"";
        Subject subject = SecurityUtils.getSubject();
        String msg="";
        if(account!=null&&!"".equals(account)) {
            UsernamePasswordToken token = new UsernamePasswordToken(account,password);
            try {
                subject.login(token);
                msg="suc";
            } catch (AuthenticationException exception) {
                //清除session
                token.clear();
                //自定义信息

                if (UnknownAccountException.class.getName().equals(exception+"")) {
                    msg = "账号或密码错误";
                } else if (IncorrectCredentialsException.class.getName().equals(exception.getClass().getName())) {
                    msg = "账号或密码错误";
                } else if (LockedAccountException.class.getName().equals(exception.getClass().getName()) ){
                    msg = " 您的账号已经被锁定 无法登入系统";
                } else {
                    msg = "账号或密码错误";
                }
            }
        }
        Map mapTmp = new HashMap();
        mapTmp.put("msg",msg);
        return mapTmp;
    }

}
