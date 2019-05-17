package com.jk.mapper;

import com.jk.model.Withdrawal;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FinancingMapper {
    Long getCount(@Param("shzt")Integer shzt, @Param("mindate")String mindate, @Param("maxdate")String maxdate);

    List<Withdrawal> findWithdrawat(@Param("page") Integer page, @Param("limit") Integer limit, @Param("shzt")Integer shzt, @Param("mindate")String mindate, @Param("maxdate")String maxdate);
    @Select("select money from  t_user where id=#{userId}")
    Double getUserMoney(String userId);
    @Select("select sum(withdrawalmoney) from  withdrawal where examineStatus=2")
    Double getSumExamine();
}
