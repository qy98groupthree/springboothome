package com.gt.home.service;

import java.util.List;
import java.util.Map;

public interface HouseService {

    //房屋查询
    List queryHouse(Map map);
    //总条数查询
    int queryPageCount(Map map);
}
