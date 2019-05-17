package com.jk.model.fxlModel;

import lombok.Data;

@Data
public class Company {
    private String companyId;//公司id
    private String companyName;//公司名称
    private String companyPhone;//公司电话
    private String companyPrincipal;//负责人
    private String companySite;//公司地址；
    private Integer companyType;//公司类型;
    private Integer trastType;//运输类型；
    private String adstrativeRegion;//行政区
    private Integer vip;//是否会员  1 是  2 不是
    private Integer cooperation;//是否合作  1 合作  2 不合作
    private String companyCircuitId;//线路ID
    private Integer companyDataId;//资料表ID
    private Integer companyAuthId;//认证表Id
}
