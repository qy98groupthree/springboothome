package com.gt.home.dao;

import com.gt.home.entity.Emp;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 姜亚林
 * @date：2019/7/27 11:28
 */

/**
 * 员工
 */
@Repository
public interface EmpDao {
    //登录
    List empLogin(Map map);
    //员工添加
    Integer addEmp(Map map);
    //员工查询
    List queryEmp(Map map);
    //修改员工信息
    Integer updateEmp(Map map);
    //删除员工
    Integer deleteEmp(Integer id);
    //总条数查询
    Integer queryPageCount(Map map);
}
