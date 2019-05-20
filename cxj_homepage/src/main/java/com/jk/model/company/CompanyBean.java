package com.jk.model.company;

import java.io.Serializable;

public class CompanyBean implements Serializable {

    private static final long serialVersionUID = -8531149024834428383L;
    private  String  companyId;     //公司id
    private  String  companyName;    //公司名称
    private  String  companyPhone;  //公司电话
    private  String  companyPrincipal;   //负责人
    private  String  companySite;        //公司地址
    private  Integer  companyType;      //公司类型
    private  Integer  trastType;       //运输类型
    private  String  adstrativeRegion;    //行政区
    private  Integer  vip;     //是否会员  1 是  2 不是
    private  Integer  cooperation;    //是否合作  1 合作  2 不合作
    private  String  companyCircuitId;   //线路ID
    private  String  companyDataId;      //资料表ID
    private  String  companyAuthId;     //认证表Id
    private  String  transportationId;     //运输Id

    private  String  officialWebsite;     //官方网站
    private  Integer  chinaId;    //省id
    private  String  LOGO;     //LOGO
    private  String  serviceTenet;     //服务宗旨
    private  String  companyProfile;     //公司介绍
    private  String  advantageServiceIndustry;     //优势服务行业
    private  String  companyAbbreviation;     //公司简称


    private  Integer  registered_capital;    // 注册资金
    private  Integer  number_of_employees;    //员工数量
    private  Integer  terminal_distribution_center;    //终端配送中心    1： 自有
    private  Integer  number_of_trunk_transport_vehicles;    //干线运输车辆数量
    private  Integer  carrier_liability_insurance;    // 承运人责任险       1：  有    2： 没有
    private  Integer  online_order_query_tracking_system;    //网上订单查询跟踪系统       1：  有    2： 没有
    private  Integer  years_of_establishment;    //成立年限
    private  Integer  storage_area_of_departure_station ;    //始发站仓储面积
    private  Integer  number_of_self_owned_delivery_vehicles;    //自有提送货车辆数量
    private  Integer  numberGPS;    // 装有GPS车辆数量
    private  Integer  on_site_operation_process;    // 现场作业流程      1：  有    2： 没有

    private Integer Id;
    private  String  Name;

    private String  xhId;
    private Integer takeHome;
    private Integer giveHome;
    private Integer postStatus;
    private String  fcsjId;
    private String  lineId2;
    private String  priceId2;
    private String  openHour;
    private String  endHour;
    private String  lowPrice;

    private  String  lineId;
    private Integer startStation;
    private Integer endStation;

   private String  priceId;
   private   Double heavyPrice;
    private   Double maxHeavy;
    private   Double minHeavy;
    private   Integer type;
    private   Double lightPrice;
    private  Integer  ch1Id;
    private  String  ch1Name;
    private  Integer  ch1Pid;
    private  Integer  ch1ChinaStart;
    private  Integer  ch1RegionId;
    private  Integer  ch1Type;
    private  Integer  ch2Id;
    private  String  ch2Name;
    private  Integer  ch2Pid;
    private  Integer  ch2ChinaStart;
    private  Integer  ch2RegionId;
    private  Integer  ch2Type;

    private  String  departureTime;

    private Integer jiaHao;

    public Integer getJiaHao() {
        return jiaHao;
    }

    public void setJiaHao(Integer jiaHao) {
        this.jiaHao = jiaHao;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getLineId2() {
        return lineId2;
    }

    public void setLineId2(String lineId2) {
        this.lineId2 = lineId2;
    }

    public String getPriceId2() {
        return priceId2;
    }

    public void setPriceId2(String priceId2) {
        this.priceId2 = priceId2;
    }

    public Integer getCh1Id() {
        return ch1Id;
    }

    public void setCh1Id(Integer ch1Id) {
        this.ch1Id = ch1Id;
    }

    public String getCh1Name() {
        return ch1Name;
    }

    public void setCh1Name(String ch1Name) {
        this.ch1Name = ch1Name;
    }

    public Integer getCh1Pid() {
        return ch1Pid;
    }

    public void setCh1Pid(Integer ch1Pid) {
        this.ch1Pid = ch1Pid;
    }

    public Integer getCh1ChinaStart() {
        return ch1ChinaStart;
    }

    public void setCh1ChinaStart(Integer ch1ChinaStart) {
        this.ch1ChinaStart = ch1ChinaStart;
    }

    public Integer getCh1RegionId() {
        return ch1RegionId;
    }

    public void setCh1RegionId(Integer ch1RegionId) {
        this.ch1RegionId = ch1RegionId;
    }

    public Integer getCh1Type() {
        return ch1Type;
    }

    public void setCh1Type(Integer ch1Type) {
        this.ch1Type = ch1Type;
    }

    public Integer getCh2Id() {
        return ch2Id;
    }

    public void setCh2Id(Integer ch2Id) {
        this.ch2Id = ch2Id;
    }

    public String getCh2Name() {
        return ch2Name;
    }

    public void setCh2Name(String ch2Name) {
        this.ch2Name = ch2Name;
    }

    public Integer getCh2Pid() {
        return ch2Pid;
    }

    public void setCh2Pid(Integer ch2Pid) {
        this.ch2Pid = ch2Pid;
    }

    public Integer getCh2ChinaStart() {
        return ch2ChinaStart;
    }

    public void setCh2ChinaStart(Integer ch2ChinaStart) {
        this.ch2ChinaStart = ch2ChinaStart;
    }

    public Integer getCh2RegionId() {
        return ch2RegionId;
    }

    public void setCh2RegionId(Integer ch2RegionId) {
        this.ch2RegionId = ch2RegionId;
    }

    public Integer getCh2Type() {
        return ch2Type;
    }

    public void setCh2Type(Integer ch2Type) {
        this.ch2Type = ch2Type;
    }

    public String getXhId() {
        return xhId;
    }

    public void setXhId(String xhId) {
        this.xhId = xhId;
    }

    public Integer getTakeHome() {
        return takeHome;
    }

    public void setTakeHome(Integer takeHome) {
        this.takeHome = takeHome;
    }

    public Integer getGiveHome() {
        return giveHome;
    }

    public void setGiveHome(Integer giveHome) {
        this.giveHome = giveHome;
    }

    public Integer getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(Integer postStatus) {
        this.postStatus = postStatus;
    }

    public String getFcsjId() {
        return fcsjId;
    }

    public void setFcsjId(String fcsjId) {
        this.fcsjId = fcsjId;
    }


    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public String getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public Integer getStartStation() {
        return startStation;
    }

    public void setStartStation(Integer startStation) {
        this.startStation = startStation;
    }

    public Integer getEndStation() {
        return endStation;
    }

    public void setEndStation(Integer endStation) {
        this.endStation = endStation;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyPrincipal() {
        return companyPrincipal;
    }

    public void setCompanyPrincipal(String companyPrincipal) {
        this.companyPrincipal = companyPrincipal;
    }

    public String getCompanySite() {
        return companySite;
    }

    public void setCompanySite(String companySite) {
        this.companySite = companySite;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public Integer getTrastType() {
        return trastType;
    }

    public void setTrastType(Integer trastType) {
        this.trastType = trastType;
    }

    public String getAdstrativeRegion() {
        return adstrativeRegion;
    }

    public void setAdstrativeRegion(String adstrativeRegion) {
        this.adstrativeRegion = adstrativeRegion;
    }

    public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    public Integer getCooperation() {
        return cooperation;
    }

    public void setCooperation(Integer cooperation) {
        this.cooperation = cooperation;
    }

    public String getCompanyCircuitId() {
        return companyCircuitId;
    }

    public void setCompanyCircuitId(String companyCircuitId) {
        this.companyCircuitId = companyCircuitId;
    }

    public String getCompanyDataId() {
        return companyDataId;
    }

    public void setCompanyDataId(String companyDataId) {
        this.companyDataId = companyDataId;
    }

    public String getCompanyAuthId() {
        return companyAuthId;
    }

    public void setCompanyAuthId(String companyAuthId) {
        this.companyAuthId = companyAuthId;
    }

    public String getOfficialWebsite() {
        return officialWebsite;
    }

    public void setOfficialWebsite(String officialWebsite) {
        this.officialWebsite = officialWebsite;
    }

    public Integer getChinaId() {
        return chinaId;
    }

    public void setChinaId(Integer chinaId) {
        this.chinaId = chinaId;
    }

    public String getLOGO() {
        return LOGO;
    }

    public void setLOGO(String LOGO) {
        this.LOGO = LOGO;
    }

    public String getServiceTenet() {
        return serviceTenet;
    }

    public void setServiceTenet(String serviceTenet) {
        this.serviceTenet = serviceTenet;
    }

    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile;
    }

    public String getAdvantageServiceIndustry() {
        return advantageServiceIndustry;
    }

    public void setAdvantageServiceIndustry(String advantageServiceIndustry) {
        this.advantageServiceIndustry = advantageServiceIndustry;
    }

    public String getCompanyAbbreviation() {
        return companyAbbreviation;
    }

    public void setCompanyAbbreviation(String companyAbbreviation) {
        this.companyAbbreviation = companyAbbreviation;
    }

    public Integer getRegistered_capital() {
        return registered_capital;
    }

    public void setRegistered_capital(Integer registered_capital) {
        this.registered_capital = registered_capital;
    }

    public Integer getNumber_of_employees() {
        return number_of_employees;
    }

    public void setNumber_of_employees(Integer number_of_employees) {
        this.number_of_employees = number_of_employees;
    }

    public Integer getTerminal_distribution_center() {
        return terminal_distribution_center;
    }

    public void setTerminal_distribution_center(Integer terminal_distribution_center) {
        this.terminal_distribution_center = terminal_distribution_center;
    }

    public Integer getNumber_of_trunk_transport_vehicles() {
        return number_of_trunk_transport_vehicles;
    }

    public void setNumber_of_trunk_transport_vehicles(Integer number_of_trunk_transport_vehicles) {
        this.number_of_trunk_transport_vehicles = number_of_trunk_transport_vehicles;
    }

    public Integer getCarrier_liability_insurance() {
        return carrier_liability_insurance;
    }

    public void setCarrier_liability_insurance(Integer carrier_liability_insurance) {
        this.carrier_liability_insurance = carrier_liability_insurance;
    }

    public Integer getOnline_order_query_tracking_system() {
        return online_order_query_tracking_system;
    }

    public void setOnline_order_query_tracking_system(Integer online_order_query_tracking_system) {
        this.online_order_query_tracking_system = online_order_query_tracking_system;
    }

    public Integer getYears_of_establishment() {
        return years_of_establishment;
    }

    public void setYears_of_establishment(Integer years_of_establishment) {
        this.years_of_establishment = years_of_establishment;
    }

    public Integer getStorage_area_of_departure_station() {
        return storage_area_of_departure_station;
    }

    public void setStorage_area_of_departure_station(Integer storage_area_of_departure_station) {
        this.storage_area_of_departure_station = storage_area_of_departure_station;
    }

    public Integer getNumber_of_self_owned_delivery_vehicles() {
        return number_of_self_owned_delivery_vehicles;
    }

    public void setNumber_of_self_owned_delivery_vehicles(Integer number_of_self_owned_delivery_vehicles) {
        this.number_of_self_owned_delivery_vehicles = number_of_self_owned_delivery_vehicles;
    }

    public Integer getNumberGPS() {
        return numberGPS;
    }

    public void setNumberGPS(Integer numberGPS) {
        this.numberGPS = numberGPS;
    }

    public Integer getOn_site_operation_process() {
        return on_site_operation_process;
    }

    public void setOn_site_operation_process(Integer on_site_operation_process) {
        this.on_site_operation_process = on_site_operation_process;
    }

    public String getTransportationId() {
        return transportationId;
    }

    public void setTransportationId(String transportationId) {
        this.transportationId = transportationId;
    }



    public Double getHeavyPrice() {
        return heavyPrice;
    }

    public void setHeavyPrice(Double heavyPrice) {
        this.heavyPrice = heavyPrice;
    }

    public Double getMaxHeavy() {
        return maxHeavy;
    }

    public void setMaxHeavy(Double maxHeavy) {
        this.maxHeavy = maxHeavy;
    }

    public Double getMinHeavy() {
        return minHeavy;
    }

    public void setMinHeavy(Double minHeavy) {
        this.minHeavy = minHeavy;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getLightPrice() {
        return lightPrice;
    }

    public void setLightPrice(Double lightPrice) {
        this.lightPrice = lightPrice;
    }
}
