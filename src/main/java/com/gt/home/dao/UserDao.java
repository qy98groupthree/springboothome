package com.gt.home.dao;

import com.gt.home.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 姜亚林
 * @date：2019/7/25 20:17
 */
@Repository
public interface UserDao {
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
