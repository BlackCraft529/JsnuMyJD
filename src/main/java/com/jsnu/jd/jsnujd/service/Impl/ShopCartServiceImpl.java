package com.jsnu.jd.jsnujd.service.Impl;

import com.jsnu.jd.jsnujd.mapper.GoodsMapper;
import com.jsnu.jd.jsnujd.mapper.ShopCartMapper;
import com.jsnu.jd.jsnujd.mapper.UserMapper;
import com.jsnu.jd.jsnujd.pojo.Goods;
import com.jsnu.jd.jsnujd.service.ShopCartService;
import com.jsnu.jd.jsnujd.vo.ShopCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 魏荣轩
 * @date 2020/8/9 11:12
 */
@Service("ShopCartService")
public class ShopCartServiceImpl implements ShopCartService {
    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 新增一个购物车信息
     *
     * @param ownerId   拥有者ID
     * @param goodsList 商品列表
     * @return 新增条数
     */
    @Override
    public int addShopCart(String ownerId, String goodsList) {
        String cartId = UUID.randomUUID().toString().replaceAll("-","");
        while (shopCartMapper.selectShopCartByCardId(cartId)!=null){
            cartId = UUID.randomUUID().toString().replaceAll("-","");
        }
        com.jsnu.jd.jsnujd.pojo.ShopCart shopCart = new com.jsnu.jd.jsnujd.pojo.ShopCart(cartId,ownerId,goodsList);
        return shopCartMapper.addShopCart(shopCart);
    }

    /**
     * 根据用户ID获取购物车信息
     *
     * @param userId 用户ID
     * @return 购物车vo类
     */
    @Override
    public ShopCart selectShopCartByUserId(String userId) {
        return new ShopCart(shopCartMapper.selectShopCartByUserId(userId));
    }

    /**
     * 根据购物车ID获取购物车信息
     *
     * @param cartId 购物车ID
     * @return 购物车vo类
     */
    @Override
    public ShopCart selectShopCartByCartId(String cartId) {
        return new ShopCart(shopCartMapper.selectShopCartByCardId(cartId));
    }

    /**
     * 根据购物车ID删除购物车信息
     *
     * @param cartId 购物车ID
     */
    @Override
    public void deleteShopCartByCartId(String cartId) {
        shopCartMapper.deleteShopCartByCartId(cartId);
    }

    /**
     * 根据购物车ID修改购物车信息
     *
     * @param cartId    购物车ID
     * @param goodsList 购物车信息
     * @return 修改条数
     */
    @Override
    public int updateShopCartInfoByCartId(String cartId, String goodsList) {
        if(shopCartMapper.selectShopCartByCardId(cartId)==null){
            return 0;
        }
        com.jsnu.jd.jsnujd.pojo.ShopCart shopCart=shopCartMapper.selectShopCartByCardId(cartId);
        shopCart.setGoodsList(goodsList);
        return shopCartMapper.updateShopCartGoodsListByPojo(shopCart);
    }

    /**
     * 根据用户ID修改购物车信息
     *
     * @param userId    用户ID
     * @param goodsList 商品信息
     * @return 修改条数
     */
    @Override
    public int updateShopCartInfoByUserId(String userId, String goodsList) {
        if(shopCartMapper.selectShopCartByUserId(userId)==null){
            return 0;
        }
        com.jsnu.jd.jsnujd.pojo.ShopCart shopCart=shopCartMapper.selectShopCartByUserId(userId);
        shopCart.setGoodsList(goodsList);
        return shopCartMapper.updateShopCartGoodsListByPojo(shopCart);
    }

    /**
     * 为用户购物车新增商品
     *
     * @param userId  用户ID
     * @param goodsId 商品ID
     * @param amount  数量
     * @return 修改条数
     */
    @Override
    public int addShopCartGoodsByUserId(String userId, String goodsId, int amount) {
        //新增购物车
        if(shopCartMapper.selectShopCartByUserId(userId)==null){
            String cartId = UUID.randomUUID().toString().replaceAll("-","");
            while (shopCartMapper.selectShopCartByCardId(cartId)!=null){
                cartId = UUID.randomUUID().toString().replaceAll("-","");
            }
            ShopCart shopCartNew=new ShopCart();
            shopCartNew.setId(cartId);
            Map<Goods,Integer> goodsList = new HashMap<>();
            goodsList.put(goodsMapper.selectGoodsByGoodsId(goodsId),amount);
            shopCartNew.setGoodsList(goodsList);
            shopCartNew.setOwner(userMapper.selectUserByUuid(userId));
            return shopCartMapper.addShopCart(new com.jsnu.jd.jsnujd.pojo.ShopCart(shopCartNew));
        }
        //增加购物车
        com.jsnu.jd.jsnujd.vo.ShopCart shopCart=new ShopCart(shopCartMapper.selectShopCartByUserId(userId));
        Map<Goods, Integer> newGoodsList = shopCart.getGoodsList();
        boolean isAdd=false;
        for(Map.Entry<Goods, Integer> entry : shopCart.getGoodsList().entrySet()){
            if(entry.getKey().getId().equalsIgnoreCase(goodsId)){
                newGoodsList.put(entry.getKey(),entry.getValue()+1);
                isAdd=true;
            }
        }
        if(!isAdd){
            newGoodsList.put(goodsMapper.selectGoodsByGoodsId(goodsId),1);
        }
        return shopCartMapper.updateShopCartGoodsListByPojo(new com.jsnu.jd.jsnujd.pojo.ShopCart(shopCart));
    }


}
