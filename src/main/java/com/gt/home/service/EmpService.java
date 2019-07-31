package com.gt.home.service;

import com.gt.home.entity.Emp;

import java.util.List;
import java.util.Map;

/**
 * @author 姜亚林
 * @date：2019/7/27 11:29
 */
public interface EmpService {
    //登录
    List empLogin(Map map);
    //用户添加
    Integer addEmp(Map map);
    //用户查询
    List queryEmp(Map map);
    //修改用户信息
    Integer updateEmp(Map map);
    //删除用户
    Integer deleteEmp(Integer id);
    //总条数查询
    Integer queryPageCount(Map map);
}
