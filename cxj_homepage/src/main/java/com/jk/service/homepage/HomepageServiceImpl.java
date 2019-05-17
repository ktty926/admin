package com.jk.service.homepage;

import com.jk.mapper.homepage.HomepageMapper;
import com.jk.model.area.AreaBean;
import com.jk.model.sea.SeaBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomepageServiceImpl implements HomepageService {
    @Autowired
    HomepageMapper homepageMapper;

    @Override
    public List<SeaBean> findSea(SeaBean seaBean) {
        return homepageMapper.findSea(seaBean);
    }

    @Override
    public List<AreaBean> findArea(Integer pid) {
        return homepageMapper.findArea(pid);
    }
}
