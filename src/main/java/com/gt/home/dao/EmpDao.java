package com.gt.home.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 姜亚林
 * @date：2019/7/27 11:28
 */
@Repository
public interface EmpDao {
    //登录
    List empLogin(Map map);
}
