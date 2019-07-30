package com.gt.home.entity;

import lombok.Data;

/**
 * 房源实体类
 */
@Data
public class House {
    private Integer id; //房源编号
    private String houseAddress; //房源地址
    private Integer houseType; //房屋类型
    private String type; //类型(合租，单租)
    private String houseDescribe; //房屋描述
    private Integer orientation; //房屋朝向
    private String houseCollocate; //房屋配置
    private Integer rental; //租金
    private String houseImages; //房屋图片
    private Integer status; //状态
}
