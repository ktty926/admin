package com.jk.mapper.yft;

import com.jk.bean.Bill;
import com.jk.bean.Order;
import com.jk.bean.Transport;
import com.jk.bean.Withdrawal;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
@Repository
public interface FinancingMapper {
    Long getCount(@Param("shzt")Integer shzt,
                  @Param("mindate")String mindate, @Param("maxdate")String maxdate
    ,@Param("userid")String userid);

    List<Withdrawal> findWithdrawat(@Param("page") Integer page, @Param("limit") Integer limit, @Param("shzt")Integer shzt, @Param("mindate")String mindate, @Param("maxdate")String maxdate
            , @Param("userid")String userid);

    @Select("select money from  t_user where id=#{userId}")
    Double getUserMoney(@Param("userId")String userId);

    @Select("select sum(withdrawalmoney) from  withdrawal where examineStatus=2 and userid=#{userid}")
    Double getSumExamine(@Param("userid")String userid);

    void addwithdrawal(Withdrawal withdrawal);

    @Update("update Withdrawal set examineStatus=1 where withdrawalid=#{id}")
    void updatewith(@Param("id") String id);

    @Select("select * from withdrawal where withdrawalid=#{id}")
    Withdrawal findwithbyid(@Param("id") String id);

    void addBill(Bill bill);

    @Update("update t_user set money=money-#{je} where id=#{userid}")
    void updatemoney(@Param("userid") String userid,@Param("je") Double je);


    Long getBBCount(@Param("jyh") String jyh,@Param("mindate")String mindate,@Param("maxdate")String maxdate,@Param("billzt") Integer zt,@Param("userid")String userid);

    List<Bill> findBaoBiao(@Param("page")Integer page,@Param("limit")Integer limit,
                           @Param("jyh") String jyh,@Param("mindate") String mindate,
                           @Param("maxdate") String maxdate,@Param("billzt")Integer zt,@Param("userid")String userid);

    Double srZc(@Param("userid")String userid);

    Double zc(@Param("userid")String userid);


    Long getfindtransport(@Param("ddh")String ddh, @Param("sfjs")Integer sfjs,@Param("mindate")String mindate,@Param("maxdate")String maxdate,@Param("userid")String userid);

    List<Transport> findtransport(@Param("page") Integer page, @Param("limit")Integer limit,
                                  @Param("ddh")String ddh,
                                  @Param("sfjs")Integer sfjs, @Param("mindate")String mindate, @Param("maxdate")String maxdate,
                                  @Param("userid")String userid);
    @Select("select sum(settMoney) from transport where settStatus =2 and userid=#{userid}")
    Double getTransportMoney(@Param("userid")String userid);

    @Select("SELECT * FROM accept_order where order_number = #{num}")
    Order findOrderByOrderNum(@Param("num") String num);

    @Update("update transport set settStatus=1 where transportid=#{id}")
    void updatetran(@Param("id") String id);

    @Select("select * from transport where transportid=#{id}")
    Transport getTranById(@Param("id") String id);

    List<Transport> getShouRu(@Param("userid")String userid);

    List<Withdrawal> getZhiChu(@Param("userid")String userid);

    @Update("update  t_user set money=money+#{money} where id=#{userid}")
    void updateusermoney(@Param("userid")String userid,@Param("money")Double money);
}
