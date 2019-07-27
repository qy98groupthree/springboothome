package com.gt.home.controller;

import com.gt.home.service.UserService;
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
 * @date：2019/7/25 20:45
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * 添加用户
     * @param map
     * @return
     */
    @RequestMapping("/addUser")
    public int addUser(@RequestBody  Map map){
        return userService.addUser(map);
    }

    /**
     * 查询用户列表
     * @param map
     * @return
     */
    @RequestMapping("/queryUser")
    public Object queryUser(@RequestBody Map map){
        Map mapResult = new HashMap();
        mapResult.put("userList",userService.queryUser(map));
        mapResult.put("total",userService.queryPageCount(map));
        return mapResult;
    }

    /**
     * 修改用户信息
     * @param map
     * @return
     */
    @RequestMapping("/updateUser")
    public Object updateUser(@RequestBody Map map){
        return userService.updateUser(map);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/deleteUser")
    public Object deleteUser(Integer id){
        return userService.deleteUser(id);
    }
}
