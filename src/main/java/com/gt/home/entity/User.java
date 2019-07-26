package com.gt.home.entity;

import lombok.Data;

/**
 * @author 姜亚林
 * @date：2019/7/25 20:22
 */
@Data
public class User {
    private int id;//用户编号
    private String account;//账号
    private String password;//密码
    private String phone;//电话
    private String username;//昵称
    private String headPortrait;//头像
    private int status;//状态
}
