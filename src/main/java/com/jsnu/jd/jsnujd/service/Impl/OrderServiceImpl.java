package com.jsnu.jd.jsnujd.service.Impl;

import com.jsnu.jd.jsnujd.mapper.OrderMapper;
import com.jsnu.jd.jsnujd.pojo.Goods;
import com.jsnu.jd.jsnujd.service.OrderService;
import com.jsnu.jd.jsnujd.vo.Order;
import com.jsnu.jd.jsnujd.vo.OrderArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @author 魏荣轩
 * @date 2020/8/7 22:23
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;


    /**
     * 新增一个订单记录
     *
     * @param goodsList  商品列表
     * @param userId     用户ID
     * @param payment    付款
     * @param settlement 应付
     * @param createTime 创建时间
     * @param address    收货地址
     * @param status     状态
     * @return 新增条数
     * TODO:
     * 计算商品总价格?
     */
    @Override
    public int createNewOrder(String goodsList, String userId, double payment, double settlement, Date createTime,String address, int status) {
        com.jsnu.jd.jsnujd.pojo.Order order=new com.jsnu.jd.jsnujd.pojo.Order();
        String orderId= UUID.randomUUID().toString().replaceAll("-","");
        while(orderMapper.selectOrderByOrderId(orderId)!=null){
            orderId= UUID.randomUUID().toString().replaceAll("-","");
        }
        order.setId(orderId);
        order.setGoodsList(goodsList);
        order.setUserId(userId);
        order.setPayment(payment);
        order.setSettlement(settlement);
        order.setAddress(address);
        order.setStatus(status);
        order.setCreateTime(new Date());
        return orderMapper.createNewOrder(order);
    }

    /**
     * 根据订单号查询订单信息
     *
     * @param orderId 订单Id
     * @return 订单
     */
    @Override
    public Order selectOrderByOrderId(String orderId) {
        if(orderMapper.selectOrderByOrderId(orderId)!=null){
            return new Order(orderMapper.selectOrderByOrderId(orderId));
        }
        return null;
    }

    /**
     * 根据用户ID查询订单
     *
     * @param userId 用户ID
     * @return 订单列表
     */
    @Override
    public List<Order> selectOrderByUserId(String userId) {
        List<Order> voOrderList=new ArrayList<>();
        for(com.jsnu.jd.jsnujd.pojo.Order order:orderMapper.selectOrderByUserId(userId)){
            voOrderList.add(new Order(order));
        }
        return voOrderList;
    }

    /**
     * 根据用户ID和订单状态查询订单
     *
     * @param userId 用户ID
     * @param status 订单状态
     * @return 订单数据
     */
    @Override
    public List<Order> selectOrderByUserIdAndStatus(String userId, int status) {
        List<Order> voOrderList=new ArrayList<>();
        Map<String , String> data=new HashMap<>();
        data.put("userId",userId);
        data.put("status",status+"");
        for(com.jsnu.jd.jsnujd.pojo.Order order:orderMapper.selectOrderByUserIdAndStatus(data)){
            voOrderList.add(new Order(order));
        }
        return voOrderList;
    }

    /**
     * 更新订单状态
     *
     * @param orderId 订单ID
     * @param status  订单状态
     * @return 更新条数
     */
    @Override
    public int updateOrderStatus(String orderId, int status) {
        Map<String , String> data=new HashMap<>();
        data.put("orderId",orderId);
        data.put("status",status+"");
        return orderMapper.updateOrderStatus(data);
    }

    /**
     * 更新订单金额
     *
     * @param orderId 订单ID
     * @param payment 支付金额
     * @return 影响条数
     */
    @Override
    public int updatePaymentByOrderId(String orderId, double payment) {
        if(orderMapper.selectOrderByOrderId(orderId)==null){
            return 0;
        }
        com.jsnu.jd.jsnujd.pojo.Order order = orderMapper.selectOrderByOrderId(orderId);
        order.setPayment(payment);
        return orderMapper.updateOrderInfoByOrderPojo(order);
    }

    /**
     * 更新订单售价
     *
     * @param orderId    订单ID
     * @param settlement 售价
     * @return 影响条数
     */
    @Override
    public int updateSettlementByOrderId(String orderId, double settlement) {
        if(orderMapper.selectOrderByOrderId(orderId)==null){
            return 0;
        }
        com.jsnu.jd.jsnujd.pojo.Order order = orderMapper.selectOrderByOrderId(orderId);
        order.setSettlement(settlement);
        return orderMapper.updateOrderInfoByOrderPojo(order);
    }



    /**
     * 根据订单ID删除订单信息
     *
     * @param orderId 订单ID
     */
    @Override
    public void deleteOrderByOrderId(String orderId) {
        orderMapper.deleteOrderByOrderId(orderId);
    }

    /**
     * 根据用户ID查找最新的10条订单数据
     *
     * @param userId 用户ID
     * @return 订单数据
     */
    @Override
    public List<OrderArray> getNewestOrderListByUserId(String userId) {
        List<com.jsnu.jd.jsnujd.pojo.Order> pojoOrderList = orderMapper.getNewestOrderListByUserId(userId);
        List<OrderArray> newOrderList=new ArrayList<>();
        for(com.jsnu.jd.jsnujd.pojo.Order order:pojoOrderList){
            newOrderList.add(new OrderArray(new Order(order)));
        }
        return newOrderList;
    }

    /**
     * 根据vo类创建订单信息
     *
     * @param order 订单信息
     * @return 创建条数
     */
    @Override
    public int createNewOrder(Order order) {
        String goodsList="";
        for (Map.Entry<Goods,Integer> value : order.getGoodsList().entrySet()){
            goodsList+=value.getKey().getId()+"#"+value.getValue()+",";
        }
        //String goodsList, String userId, double payment, double settlement, Date createTime,String address,int status
        return createNewOrder(goodsList,
                order.getUser().getUuid()
                ,order.getPayment()
                ,order.getSettlement()
                ,order.getCreateTime()
                ,order.getAddress()
                ,order.getStatus());
    }
}
