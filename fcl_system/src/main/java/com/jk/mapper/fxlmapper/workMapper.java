package com.jk.mapper.fxlmapper;

import com.jk.model.fxlModel.Company;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface workMapper {
    int findCount(@Param("companyName") String companyName,@Param("cooperation") Integer cooperation);

    List<Company> findCompany(@Param("start") int start,@Param("limit") Integer limit,@Param("companyName") String companyName,@Param("cooperation") Integer cooperation);

    @Update("UPDATE t_company SET cooperation = 2 where companyId = #{value}")
    void cancelCooperation(Integer cooperation);
}
