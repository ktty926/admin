package com.jk.service.homepage;

import com.jk.model.area.AreaBean;
import com.jk.model.company.CompanyBean;
import com.jk.model.sea.SeaBean;

import java.util.HashMap;
import java.util.List;

public interface HomepageService {
    List<SeaBean> findSea(SeaBean seaBean);

    List<AreaBean> findArea(Integer pid);

    HashMap<String, Object> findLogistics(Integer page, Integer limit);

    List<CompanyBean> findShouYe(CompanyBean companyBean);

    List<CompanyBean> findTransportation();

    List<AreaBean> findRegionId(Integer regionId);
}
