package com.jk.service;

import java.util.HashMap;

public interface FinancingService {
    HashMap<String, Object> findWithdrawat(Integer page,Integer limit,Integer shzt,String mindate,String maxdate);

    Double getUserMoney(String userId);

    Double getSumExamine();
}
