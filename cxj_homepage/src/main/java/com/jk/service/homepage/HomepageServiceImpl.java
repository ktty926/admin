package com.jk.service.homepage;

import com.jk.mapper.homepage.HomepageMapper;
import com.jk.model.area.AreaBean;
import com.jk.model.company.CompanyBean;
import com.jk.model.sea.SeaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class HomepageServiceImpl implements HomepageService {
    @Autowired
    HomepageMapper homepageMapper;
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public List<SeaBean> findSea(SeaBean seaBean) {
        return homepageMapper.findSea(seaBean);
    }

    @Override
    public List<AreaBean> findArea(Integer pid) {
        return homepageMapper.findArea(pid);
    }

    @Override
    public HashMap<String, Object> findLogistics(Integer page, Integer limit) {
        HashMap<String, Object> hashMap = new HashMap();
        int count = homepageMapper.findCount();
        int start = (page - 1) * limit;
       String key ="findLogistics"+page+limit;
        List<CompanyBean> list =new ArrayList<>();
        if(!redisTemplate.hasKey(key)){
            list = homepageMapper.findLogistics(start, limit);
            for (int i=0;i<list.size();i++){
                redisTemplate.opsForList().leftPush(key,list.get(i));
            }
            System.out.println("走数据库");
        }else{
            for (int i=0;i<redisTemplate.opsForList().size(key);i++){
                CompanyBean ff = (CompanyBean) redisTemplate.opsForList().leftPop(key);
                list.add(ff);
                redisTemplate.opsForList().rightPush(key,ff);
            }
            System.out.println("走缓存");
        }
        if (list.size() > 0) {
            hashMap.put("code", 0);
            hashMap.put("count", count);
            hashMap.put("data", list);
        } else {
            hashMap.put("code", 1);
        }
        return hashMap;
    }

    @Override
    public List<CompanyBean> findShouYe(CompanyBean companyBean) {
        List<CompanyBean> shouYe = homepageMapper.findShouYe(companyBean);

        return shouYe;
    }

    @Override
    public List<CompanyBean> findTransportation() {
        return homepageMapper.findTransportation();
    }

    @Override
    public List<AreaBean> findRegionId(Integer regionId) {
        return homepageMapper.findRegionId(regionId);
    }
}
