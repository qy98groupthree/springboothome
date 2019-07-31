package com.gt.home.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 员工
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp {
        private  Integer id;//id
        private  Integer empId;//员工id
        private  String empName;//姓名
        private  String empPhone;//电话
        private Integer empAccount;//账号
        private  Integer empPassword;//密码
        private  String empEmail;//邮箱
        private  Integer empQq;//qq
        private  String empHeadportait;//头像
        private  String singature;//签名
        private  Integer empStaus;//状态
}
