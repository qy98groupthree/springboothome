package com.gt.home.controller;

import com.gt.home.service.EmpService;
import com.gt.home.utils.FtpUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Map;

/**
 * 员工列表
 */
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;
    @Autowired
    private FtpUtil ftpUtil;
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

    /**
     * 员工添加
     * @param map
     * @return
     */
    @RequestMapping("/addEmp")
    public Integer addEmp(@RequestBody Map map){
        return empService.addEmp(map);
    }

    /**
     * 员工查询
     * @param map
     * @return
     */
    @RequestMapping("/queryEmp")
    public Object queryEmp(@RequestBody Map map){
        System.out.println("11111111111111111111111111111111111111111111111");
        System.out.println(map);
        Map mapResult = new HashMap();
        mapResult.put("empList",empService.queryEmp(map));
        mapResult.put("total",empService.queryPageCount(map));

        System.out.println(mapResult);
        return mapResult;
    }

    /**
     * 员工修改
     * @param map
     * @return
     */
    @RequestMapping("/updateEmp")
    public Object updateEmp(@RequestBody Map map){
        return empService.updateEmp(map);
    }

    /**
     * 员工删除
     * @param id
     * @return
     */
    @RequestMapping("/deleteEmp")
    public Object deleteEmp(Integer id){
        return empService.deleteEmp(id);
    }

    /**
     * 文件上传
     * @param headPortrait
     * @return
     */
    @RequestMapping("/uploadHeadPicA")
    public Object uploadHeadPic(@RequestParam MultipartFile headPortrait){
        String originalFilename = headPortrait.getOriginalFilename();
        String newFileName = ftpUtil.upLoad(headPortrait);
        Map map = new HashMap();
        map.put("originalFilename",originalFilename);
        map.put("newFileName",newFileName);
        return map;
    }
}
