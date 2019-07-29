package com.gt.home.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface HouseDao {

    //房屋查询
    List queryHouse(Map map);
    //总条数查询
    int queryPageCount(Map map);
}
