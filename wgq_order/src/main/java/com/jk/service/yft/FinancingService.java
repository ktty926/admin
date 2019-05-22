package com.jk.service.yft;


import com.jk.bean.Echars;
import com.jk.bean.Order;
import com.jk.bean.Withdrawal;

import java.util.HashMap;
import java.util.List;

public interface FinancingService {
    HashMap<String, Object> findWithdrawat(Integer page, Integer limit, Integer shzt, String mindate, String maxdate, String userid);

    Double getUserMoney(String userId);

    Double getSumExamine(String userid);

    void addwithdrawal(Withdrawal withdrawal);

    void updatewith(String id);

    HashMap<String, Object> findBaoBiao(Integer page, Integer limit, String jyh, String mindate, String maxdate, Integer zt, String userid);


    Double srZc(String userid);

    Double zc(String userid);

    HashMap<String, Object> findtransport(Integer page, Integer limit,
                                          String ddh,
                                          Integer sfjs, String mindate, String maxdate, String userid);

    Double getTransportMoney(String userid);

    Order findOrderByOrderNum(String num);

    void updatetran(String id);

    List<Echars> getShouRu(String userid);

    List<Echars> getZhiChu(String userid);
}
