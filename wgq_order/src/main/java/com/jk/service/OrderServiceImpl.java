package com.jk.service;

import com.alibaba.fastjson.JSON;
import com.jk.bean.*;
import com.jk.mapper.OrderMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public HashMap<String, Object> addOrder(Order order) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (order != null){
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd");

            Date date = new Date();
            String format = sdf.format(date);
            String[] split = format.split("-");
            String str = "";
            for (String s : split) {
                str += s;
            }

            int code = (int) ((Math.random() * 9 + 1) * 100000);

            order.setOrderNumber(str+code);
            order.setOrderStatus("1");
            order.setCreateDate(date);
            orderMapper.addOrder(order);
            hashMap.put("code",0);
            hashMap.put("status","订单成功录入，等待审核！");
        }else{
            hashMap.put("code",1);
            hashMap.put("status","数据不能为空！");
        }
        return hashMap;
    }

    @Override
    public HashMap<String, Object> updateOrderStatus(String orderNum, String status) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (StringUtils.isNotEmpty(orderNum) && orderNum != null){
            Order order = orderMapper.findOrderByOrderNum(orderNum);
            if ("1".equals(status)){
                if ("1".equals(order.getOrderStatus())){
                    orderMapper.updateOrderStatus(orderNum);
                    hashMap.put("code",0);
                    hashMap.put("status","受理成功");
                }else{
                    hashMap.put("code",1);
                    hashMap.put("status","该订单已受理，请勿重复点击!");
                }
            }
            if ("2".equals(status)){
                if ("2".equals(order.getOrderStatus())){
                    orderMapper.updateOrder(orderNum);
                    hashMap.put("code",0);
                    hashMap.put("status","发货成功");
                }else{
                    hashMap.put("code",1);
                    hashMap.put("status","该订单已发货，请勿重复点击!");
                }
            }
        }
        return hashMap;
    }

    @Override
    public void addComment(String comment) {
        Jedis jedis = jedisPool.getResource();
        List<SensitiveWords> list = null;
        if (comment != null && StringUtils.isNotEmpty(comment)){
            String sensitiveWords = jedis.get("SensitiveWords");
            if (StringUtils.isNotEmpty(sensitiveWords) && sensitiveWords != null){
                list = JSON.parseArray(sensitiveWords, SensitiveWords.class);
            }else{
                list = orderMapper.findSensitiveWords();
                jedis.set("SensitiveWords", JSON.toJSONString(list));
            }
            jedis.close();
            for (SensitiveWords sensitiveWord : list) {

                if(comment.contains(sensitiveWord.getBadword())){
                    String star = "";
                    for(int i = 0;i < sensitiveWord.getBadword().length();i++){
                        star += "*";
                    }

                    comment = comment.replace(sensitiveWord.getBadword(),star);
                }


            }
            CompanyComment companyComment = new CompanyComment();
            companyComment.setCompanyCommentId(UUID.randomUUID().toString());
            companyComment.setCompanyComment(comment);
            orderMapper.addComment(companyComment);
        }
        jedis.close();
    }

    @Override
    public Order findOrderByOrderNum(String num) {
        Order order = null;
        if (org.apache.commons.lang.StringUtils.isNotEmpty(num))
        order = orderMapper.findOrderByOrderNum(num);
            return order;
    }

    @Override
    public List<AreaBean> findArea(Integer pid) {
        Jedis jedis = jedisPool.getResource();

        String area = jedis.get("area" + pid);

        List<AreaBean> list = null;

        if (org.apache.commons.lang.StringUtils.isNotEmpty(area) && area != null){
            list = JSON.parseArray(area, AreaBean.class);
        }else{
            list = orderMapper.findArea(pid);
            if (list.size() > 0 && list != null){
                String s = JSON.toJSONString(list);
                jedis.set("area"+pid, s);
            }
        }

        jedis.close();
        return list;
    }

    @Override
    public List<TreeBean> getTree() {
        Jedis jedis = jedisPool.getResource();
        String id = "0";
        String tree = jedis.get("B_tree");
        List<TreeBean> list = null;
        if (!StringUtils.isEmpty(tree)){
            list = JSON.parseArray(tree,TreeBean.class);
        }else{
            list = getNode(id);
            jedis.set("B_tree",JSON.toJSONString(list));
        }
        jedis.close();
        return list;
    }

    public List<TreeBean> getNode(String id){
        List<TreeBean> treeBeans = orderMapper.getTree(id);
        for (TreeBean treeBean : treeBeans) {
            List<TreeBean> list = getNode(treeBean.getId());
            if (list != null && list.size() > 0){
                treeBean.setSpread(true);
                treeBean.setChildren(list);
            }else{
                treeBean.setSpread(false);
            }
        }
        return treeBeans;
    }

    @Override
    public HashMap<String, Object> findOrder(Order order, int page, int limit) {

        HashMap<String, Object> hashMap = new HashMap<>();
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap.put("page", (page-1)*limit);
        hashMap.put("limit", limit);
        hashMap.put("order", order);

        long count = orderMapper.findOrderByCount(hashMap);
        List<Order> list = orderMapper.findOrder(hashMap);

        hashMap2.put("code", 0);
        hashMap2.put("msg", "");
        hashMap2.put("data", list);
        hashMap2.put("count", count);

        return hashMap2;
    }
}
