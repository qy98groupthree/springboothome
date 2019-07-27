package com.gt.home.service;

import com.gt.home.dao.EmpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 姜亚林
 * @date：2019/7/27 11:29
 */
@Service
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpDao empDao;

    @Override
    public List empLogin(Map map) {
        return empDao.empLogin(map);
    }
}
