package com.jsnu.jd.jsnujd.mapper;

import com.jsnu.jd.jsnujd.pojo.ShopCart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 魏荣轩
 * @date 2020/8/9 10:52
 */
@Mapper
@Repository
public interface ShopCartMapper {

    /**
     * 新增一个购物车信息
     * @param shopCart 购物车
     * @return 新增条数
     */
    int addShopCart(ShopCart shopCart);

    /**
     * 根据购物车ID查询购物车信息
     * @param cartId 购物车ID
     * @return 购物车信息
     */
    ShopCart selectShopCartByCardId(String cartId);

    /**
     * 根据用户ID查询购物车
     * @param userId 用户ID
     * @return 购物车信息
     */
    ShopCart selectShopCartByUserId(String userId);

    /**
     * 根据购物车ID修改购物车包含商品信息
     * @param shopCart 购物车数据
     * @return 修改条数
     */
    int updateShopCartGoodsListByPojo(ShopCart shopCart);

    /**
     * 根据购物车ID删除购物车数据
     * @param cartId 购物车ID
     */
    void deleteShopCartByCartId(String cartId);
}
