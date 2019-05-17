package com.jk.service.fxlService;

import com.alibaba.fastjson.JSON;
import com.jk.mapper.fxlmapper.workMapper;
import com.jk.model.fxlModel.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;

@Service
public class workServiceImpl implements workService {
    @Autowired
    private workMapper workMapper;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public HashMap<String, Object> findCompany(Company company, Integer page, Integer limit) {
        Jedis jedis = jedisPool.getResource();
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        int sum = workMapper.findCount(company.getCompanyName(), company.getCooperation());
        int start = (page - 1) * limit;

        String com = jedis.get("company");
        List<Company> list = null;
        if (com == "" || com == null) {
            list = workMapper.findCompany(start, limit, company.getCompanyName(), company.getCooperation());
            String jsonString = JSON.toJSONString(list);
            jedis.set("company", jsonString);
            jedis.expire("company", 86400);
        }
        if (list.size() > 0) {
            hashMap.put("code", 0);
            hashMap.put("count", sum);
            hashMap.put("data", list);
        } else {
            hashMap.put("code", 1);
        }
        return hashMap;
    }

    //取消合作
    @Override
    public void cancelCooperation(Integer cooperation) {
        workMapper.cancelCooperation(cooperation);
    }
}
