package com.jk.service.yft;

import com.jk.mapper.yft.FinancingMapper;
import com.jk.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FinancingServiceImpl implements FinancingService{
    @Autowired
    private FinancingMapper mapper;
    @Override
    public HashMap<String, Object> findWithdrawat(Integer page,Integer limit,Integer shzt,String mindate,String maxdate,String userid) {
        Long count=mapper.getCount(shzt,mindate,maxdate,userid);
        List<Withdrawal> list=mapper.findWithdrawat(page,limit,shzt,mindate,maxdate,userid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("count",count);
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @Override
    public Double getUserMoney(String userId) {

        return mapper.getUserMoney(userId);
    }

    @Override
    public Double getSumExamine(String userid) {

        return mapper.getSumExamine(userid);
    }

    @Override
    public void addwithdrawal(Withdrawal withdrawal) {
        mapper.addwithdrawal(withdrawal);
    }

    @Override
    public void updatewith(String id) {
        mapper.updatewith(id);
        String userid="1";
        Withdrawal with=mapper.findwithbyid(id);
        mapper.updatemoney(userid,with.getWithdrawalMoney());
        Bill bill = new Bill();
        bill.setTransactionMoney(with.getWithdrawalMoney());
        bill.setTransactionNumber(UUID.randomUUID().toString());
        bill.setBillId(UUID.randomUUID().toString());
        bill.setTransactionType("提现");
        bill.setBeizhu("这是支出");
        bill.setTransactionDate(new Date());
        bill.setBillType(2);
        bill.setUserid(with.getUserid());
        mapper.addBill(bill);
    }

    @Override
    public HashMap<String, Object> findBaoBiao(Integer page, Integer limit,String jyh,String mindate,String maxdate,Integer zt,String userid) {
        Long count=mapper.getBBCount(jyh,mindate,maxdate,zt,userid);

        List<Bill> list=mapper.findBaoBiao(page,limit,jyh,mindate,maxdate,zt,userid);

        HashMap<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("count",count);
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @Override
    public Double srZc(String userid) {

        return mapper.srZc(userid);
    }

    @Override
    public Double zc(String userid) {

        return mapper.zc(userid);
    }

    @Override
    public HashMap<String, Object> findtransport(Integer page,Integer limit,
                                                 String ddh,
                                                 Integer sfjs,String mindate,String maxdate,String userid) {
        Long count=mapper.getfindtransport(ddh,sfjs,mindate,maxdate,userid);

        List<Transport> list=mapper.findtransport(page,limit,ddh,sfjs,mindate,maxdate,userid);

        HashMap<String, Object> map = new HashMap<>();
        map.put("data",list);
        map.put("count",count);
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @Override
    public Double getTransportMoney(String userid) {
        return mapper.getTransportMoney(userid);
    }

    @Override
    public Order findOrderByOrderNum(String num) {
        return mapper.findOrderByOrderNum(num);
    }

    @Override
    public void updatetran(String id) {
        mapper.updatetran(id);
        Transport Transport =mapper.getTranById(id);
        mapper.updateusermoney(Transport.getUserid(),Transport.getSettMoney());
        Bill bill = new Bill();
        bill.setTransactionMoney(Transport.getSettMoney());
        bill.setBeizhu("这是收入");
        bill.setBillId(UUID.randomUUID().toString());
        bill.setBillType(1);
        bill.setTransactionType("运费");
        bill.setTransactionNumber(UUID.randomUUID().toString());
        bill.setTransactionDate(new Date());
        bill.setUserid(Transport.getUserid());
        mapper.addBill(bill);
    }

    @Override
    public List<Echars> getShouRu(String userid) {
        List<Transport> shouRu = mapper.getShouRu(userid);
        List<Echars> list=new ArrayList<>();
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0;i<shouRu.size();i++){
            Echars echars = new Echars();
            echars.setGroup(shouRu.get(i).getEchars());
            echars.setName(sim.format(shouRu.get(i).getOrderDate()));
            echars.setValue(shouRu.get(i).getSettMoney());
            list.add(echars);
        }
        return list;
    }

    @Override
    public List<Echars> getZhiChu(String userid) {
        List<Withdrawal> zhiChu = mapper.getZhiChu(userid);
        List<Echars> list=new ArrayList<>();
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        for (int i=0;i<zhiChu.size();i++){
            Echars echars = new Echars();
            echars.setGroup(zhiChu.get(i).getEchars());
            echars.setName(sim.format(zhiChu.get(i).getApplyDate()));
            echars.setValue(zhiChu.get(i).getWithdrawalMoney());
            list.add(echars);
        }
        return list;
    }


}
