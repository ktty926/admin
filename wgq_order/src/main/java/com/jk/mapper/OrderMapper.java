package com.jk.mapper;

import com.jk.bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    @Select("SELECT * FROM demand_sensitive_word")
    List<SensitiveWords> findSensitiveWords();

    @Insert("insert into t_company_comment (companyCommentId,companyComment) values (#{companyCommentId},#{companyComment})")
    void addComment(CompanyComment companyComment);

    @Update("update accept_order set order_status = '2' where order_number = #{value}")
    void updateOrderStatus(String orderNum);

    @Update("update accept_order set order_status = '3' where order_number = #{value}")
    void updateOrder(String orderNum);

    void addOrder(Order order);
}
