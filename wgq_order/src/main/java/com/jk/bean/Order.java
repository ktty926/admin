package com.jk.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private String orderId;	//varchar(100)	主键ID

    private String departure;	//varchar(100)	出发地

    private String arrivals;	//varchar(100)	到达地

    private String sender;	//varchar(100)	发货人

    private String senderPhoneNumber;//varchar(100)	发货人手机号

    private String weight;	//varchar(100)	重量

    private String volume;	//varchar(100)	体积

    private String goodsName;	//varchar(100)	货物名称

    private String departureProvinceCity;	//varchar(100)	出发地省市

    private String arrivalsProvinceCity;//	varchar(100)	到达地省市

    private String homeDelivery;//	varchar(100)	送货上门 1：是   2：否

    private String manualPicking;//	varchar(100)	上门提货 1：是   2：否

    private String paymentMethod;//	int(100)	1:发货人支付（现付） 2:收货人支付（到付）

    private String otherInstructions;//	varchar(100)	其他信息

    private String receiverHumanName;//	varchar(100)	收货方联系人

    private String consigneeMobileNumber;//	varchar(100)	收货人手机号

    private String userId;//	varchar(100)	用户ID

    private String provinceCityAreaId;//	varchar(100)	省市区id

    private Date createTime;//	date	下单时间

    private String orderStatus;//	varchar(100)	订单状态

    private String orderNumber;//	varchar(100)	订单号

    private String goodsCount;//	int(100)	货物数量

    private String typeOfPackaging;//	varchar(100)	包装类型

    private Date startCreateTime; //下单时间区间查询  开始时间

    private Date endCreateTime; //下单时间区间查询  结束时间

    private String status;  //根据状态查询

}