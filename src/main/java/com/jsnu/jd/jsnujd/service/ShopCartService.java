package com.jsnu.jd.jsnujd.service;

import com.jsnu.jd.jsnujd.vo.ShopCart;

/**
 * @author 魏荣轩
 * @date 2020/8/9 11:07
 */
public interface ShopCartService {

    /**
     * 新增一个购物车信息
     * @param ownerId 拥有者ID
     * @param goodsList 商品列表
     * @return 新增条数
     */
    int addShopCart(String ownerId,String goodsList);

    /**
     * 根据用户ID获取购物车信息
     * @param userId 用户ID
     * @return 购物车vo类
     */
    ShopCart selectShopCartByUserId(String userId);

    /**
     * 根据购物车ID获取购物车信息
     * @param cartId 购物车ID
     * @return 购物车vo类
     */
    ShopCart selectShopCartByCartId(String cartId);

    /**
     * 根据购物车ID删除购物车信息
     * @param cartId 购物车ID
     */
    void deleteShopCartByCartId(String cartId);

    /**
     * 根据购物车ID修改购物车信息
     * @param cartId 购物车ID
     * @param goodsList 购物车信息
     * @return 修改条数
     */
    int updateShopCartInfoByCartId(String cartId,String goodsList);

    /**
     * 根据用户ID修改购物车信息
     * @param userId 用户ID
     * @param goodsList 商品信息
     * @return 修改条数
     */
    int updateShopCartInfoByUserId(String userId,String goodsList);

    /**
     * 为用户购物车新增商品
     * @param userId 用户ID
     * @param goodsId 商品ID
     * @param amount 数量
     * @return 修改条数
     */
    int addShopCartGoodsByUserId(String userId,String goodsId,int amount);
}
