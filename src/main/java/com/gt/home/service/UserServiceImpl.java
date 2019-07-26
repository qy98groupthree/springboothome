package com.gt.home.service;

import com.gt.home.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 姜亚林
 * @date：2019/7/25 20:39
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    /**
     * 添加用户
     * @return
     */
    @Override
    public int addUser(Map map) {
        return userDao.addUser(map);
    }

    /**
     * 查询用户列表
     * @param map
     * @return
     */
    @Override
    public List queryUser(Map map) {
        return userDao.queryUser(map);
    }

    /**
     * 修改用户信息
     * @param map
     * @return
     */
    @Override
    public int updateUser(Map map) {
        return userDao.updateUser(map);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public int deleteUser(Integer id) {
        return userDao.deleteUser(id);
    }

    /**
     * 总条数查询
     * @param map
     * @return
     */
    @Override
    public int queryPageCount(Map map) {
        return userDao.queryPageCount(map);
    }
}
