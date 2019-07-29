package com.gt.home.service;

import com.gt.home.dao.HouseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseDao houseDao;

    @Override
    public List queryHouse(Map map) {
        return houseDao.queryHouse(map);
    }

    @Override
    public int queryPageCount(Map map) {
        return houseDao.queryPageCount(map);
    }
}
