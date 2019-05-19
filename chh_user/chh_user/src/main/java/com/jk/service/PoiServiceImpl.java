package com.jk.service;

import com.jk.bean.PhoneCount;
import com.jk.dao.PoiMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoiServiceImpl implements PoiService{
    @Autowired
    private PoiMapper poiMapper;
    @Autowired
    private MongoTemplate  mongoTemplate;
/**
 * @Author chh
 * @Description //TODO    从mongo查询之后返回  做导出
 * @Date 17:56 2019/5/17
 * @Param 
 * @return 
 **/
    @Override
    public List<PhoneCount> findUserListByIds(String ids) {
        Query query = new Query();
        String[] split = ids.split(",");
        if(!StringUtils.isEmpty(ids)){
            query.addCriteria(Criteria.where("id").in(split));
        }
        List<PhoneCount> phoneCounts = mongoTemplate.find(query, PhoneCount.class);
        return phoneCounts;
    }
/**
 * @Author chh
 * @Description //TODO         导入  批量新增
 * @Date 17:56 2019/5/17
 * @Param 
 * @return 
 **/
    @Override
    public void savePoi(PhoneCount phoneCount) {
        List<PhoneCount> phoneCounts = phoneCount.getPhoneCounts();
        for (PhoneCount  phonecount:phoneCounts) {
              mongoTemplate.insert(phonecount);
        }
    }
    








    
    
}
