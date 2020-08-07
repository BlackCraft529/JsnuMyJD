package com.jsnu.jd.jsnujd.mapper;

import com.jsnu.jd.jsnujd.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author 魏荣轩
 * @date 2020/8/7 21:48
 */
@Mapper
@Repository
public interface OrderMapper {

    /**
     * 根据订单ID查询订单
     * @param orderId 订单ID
     * @return 订单信息
     */
    Order selectOrderByOrderId(String orderId);

    /**
     * 根据用户ID查询订单
     * @param userId 用户ID
     * @return 订单列表
     */
    List<Order> selectOrderByUserId(String userId);

    /**
     * 根据用户ID和订单状态查询订单
     * @param data 数据  userId: xxxx status:1
     * @return 订单数据
     */
    List<Order> selectOrderByUserIdAndStatus(Map<String,String> data);

    /**
     * 更新订单状态
     * @param data 订单数据 orderId: xxxx status:1
     * @return 更新条数
     */
    int updateOrderStatus(Map<String,String> data);

    /**
     * 更新订单信息
     * @param order 订单pojo类
     *              不更新： 创建时间与状态
     * @return 更新条数
     */
    int updateOrderInfoByOrderPojo(Order order);

    /**
     * 创建一个新的订单
     * @param order 订单
     * @return 创建条数
     */
    int createNewOrder(Order order);

    /**
     * 根据订单ID删除订单信息
     * @param orderId 订单ID
     */
    void deleteOrderByOrderId(String orderId);
}
