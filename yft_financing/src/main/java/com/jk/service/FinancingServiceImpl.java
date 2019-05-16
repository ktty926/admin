package com.jk.service;

import com.jk.model.Withdrawal;
import com.jk.mapper.FinancingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FinancingServiceImpl implements FinancingService{
    @Autowired
    private FinancingMapper mapper;
    @Override
    public HashMap<String, Object> findWithdrawat(Integer page,Integer limit,Integer shzt,String mindate,String maxdate) {
        Long count=mapper.getCount(shzt,mindate,maxdate);
        List<Withdrawal> list=mapper.findWithdrawat(page,limit,shzt,mindate,maxdate);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("count",count);
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @Override
    public Double getUserMoney(String userId) {

        return mapper.getUserMoney(userId);
    }

    @Override
    public Double getSumExamine() {

        return mapper.getSumExamine();
    }
}
