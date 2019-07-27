package com.gt.home.service;

import java.util.List;
import java.util.Map;

/**
 * @author 姜亚林
 * @date：2019/7/25 20:39
 */
public interface UserService {
    //用户添加
    int addUser(Map map);
    //用户查询
    List queryUser(Map map);
    //修改用户信息
    int updateUser(Map map);
    //删除用户
    int deleteUser(Integer id);
    //总条数查询
    int queryPageCount(Map map);
}
