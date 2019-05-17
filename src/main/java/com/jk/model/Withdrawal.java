package com.jk.model;

import lombok.Data;

import java.util.Date;

@Data
public class Withdrawal {
    private String withdrawalid;

    private Date applyDate ;
    private Double withdrawalMoney;
    private String withdrawalBank;
    private Integer examineStatus;

}
