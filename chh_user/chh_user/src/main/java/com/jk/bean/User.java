package com.jk.bean;

import lombok.Data;

@Data
public class User {
    private String id;
    private String phoneNumber;//手机号
    private String password;//密码
    private Integer regTypeId;//注册类型
    private String referrer;//推荐人
    private String companyName;//公司名称
    private String companyId;//公司ID
    private String qqNumber;//关联的QQ
    private String memberId;//推荐人会员id
    private Integer sex;//性别  1:男 、  2：女
    private Integer type;//大类型 1:发货方/  2:承运公司
    private String phoneMember;//会员号
    private String name;//真实姓名

}
