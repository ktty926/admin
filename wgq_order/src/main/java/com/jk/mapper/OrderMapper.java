package com.jk.mapper;

import com.jk.bean.AreaBean;
import com.jk.bean.Order;
import com.jk.bean.TreeBean;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface OrderMapper {

    long findOrderByCount(HashMap<String, Object> hashMap);

    List<Order> findOrder(HashMap<String, Object> hashMap);

    @Select("SELECT id,name,url,pid from b_tree where pid = #{value}")
    List<TreeBean> getTree(String id);

    @Select("SELECT * FROM china where pid = #{value} and id != 0")
    List<AreaBean> findArea(Integer pid);

    Order findOrderByOrderNum(String num);
}
