package com.jk.service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.jk.bean.AreaBean;
import com.jk.bean.Order;
import com.jk.bean.TreeBean;
import com.jk.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private JedisPool jedisPool;

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
