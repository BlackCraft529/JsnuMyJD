package com.jsnu.jd.jsnujd.service;


import com.jsnu.jd.jsnujd.vo.Order;
import java.util.Date;
import java.util.List;
/**
 * @author 魏荣轩
 * @date 2020/8/7 22:22
 */

public interface OrderService {

    /**
     * 新增一个订单记录
     *
     * @param goodsList 商品列表
     * @param userId 用户ID
     * @param payment 付款
     * @param settlement 应付
     * @param address 收货地址
     * @param createTime 创建时间
     * @param status 状态
     * @return 新增条数
     */
    int createNewOrder(String goodsList, String userId, double payment, double settlement, Date createTime,String address,int status);

    /**
     * 根据订单号查询订单信息
     * @param orderId 订单Id
     * @return 订单
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
     * @param userId 用户ID
     * @param status 订单状态
     * @return 订单数据
     */
    List<Order> selectOrderByUserIdAndStatus(String userId, int status);

    /**
     * 更新订单状态
     * @param orderId 订单ID
     * @param status 订单状态
     * @return 更新条数
     */
    int updateOrderStatus(String orderId, int status);

    /**
     * 更新订单金额
     * @param orderId 订单ID
     * @param payment 支付金额
     * @return 影响条数
     */
    int updatePaymentByOrderId(String orderId,double payment);

    /**
     * 更新订单售价
     * @param orderId 订单ID
     * @param settlement 售价
     * @return 影响条数
     */
    int updateSettlementByOrderId(String orderId,double settlement);

    /**
     * 根据订单ID删除订单信息
     * @param orderId 订单ID
     */
    void deleteOrderByOrderId(String orderId);
}
