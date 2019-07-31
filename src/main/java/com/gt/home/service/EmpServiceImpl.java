package com.gt.home.service;

import com.gt.home.dao.EmpDao;
import com.gt.home.entity.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 姜亚林
 * @date：2019/7/27 11:29
 */

/**
 * 员工列表
 */
@Service
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpDao empDao;

    /**
     * 登录
     * @param map
     * @return
     */
    @Override
    public List empLogin(Map map) {
        return empDao.empLogin(map);
    }

    /**
     * 添加
     * @param map
     * @return
     */
    @Override
    public Integer addEmp(Map map) {
        return empDao.addEmp(map);
    }

    /**
     * 查询
     * @param map
     * @return
     */
    @Override
    public List queryEmp(Map map) {
        return empDao.queryEmp(map);
    }

    /**
     * 修改
     * @param map
     * @return
     */
    @Override
    public Integer updateEmp(Map map) {
        return empDao.updateEmp(map);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Integer deleteEmp(Integer id) {
        return empDao.deleteEmp(id);
    }

    /**
     * 总条数查询
     * @param map
     * @return
     */
    @Override
    public Integer queryPageCount(Map map) {
        return empDao.queryPageCount(map);
    }
}
