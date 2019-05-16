package com.jk.service.homepage;

import com.jk.model.area.AreaBean;
import com.jk.model.sea.SeaBean;

import java.util.List;

public interface HomepageService {
    List<SeaBean> findSea(SeaBean seaBean);

    List<AreaBean> findArea(Integer pid);
}
