package com.jk.mapper.homepage;

import com.jk.model.area.AreaBean;
import com.jk.model.sea.SeaBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomepageMapper {

    List<SeaBean> findSea(@Param("seaBean")SeaBean seaBean);

    List<AreaBean> findArea(Integer pid);
}
