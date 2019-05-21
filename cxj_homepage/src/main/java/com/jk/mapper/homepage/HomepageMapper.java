package com.jk.mapper.homepage;

import com.jk.model.area.AreaBean;
import com.jk.model.company.CompanyBean;
import com.jk.model.invoice.InvoicBeane;
import com.jk.model.line.LineBean;
import com.jk.model.sea.SeaBean;
import com.jk.model.tejia.TejiaBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

public interface HomepageMapper {

    List<SeaBean> findSea(@Param("seaBean")SeaBean seaBean);
@Select("select * from  china  where chinaStart=1 and pid=#{value}")
    List<AreaBean> findArea(Integer pid);

    int findCount();

    List<CompanyBean> findLogistics(@Param("start") int start,@Param("limit") Integer limit);

    List<CompanyBean> findShouYe(@Param("companyBean")CompanyBean companyBean);

    List<CompanyBean> findTransportation();
    @Select("select * from  china  where chinaStart=1 and regionId=#{value}")
    List<AreaBean> findRegionId(Integer regionId);

    List<LineBean> findLine();

    List<InvoicBeane> findInvoice();

    List<TejiaBean> findTejia(@Param("tejiaBean")TejiaBean tejiaBean);
}
