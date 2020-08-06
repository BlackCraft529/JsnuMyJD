package com.jsnu.jd.jsnujd.service;

import com.jsnu.jd.jsnujd.pojo.Goods;

/**
 * @author 魏荣轩
 * @date 2020/8/6 18:42
 */
public interface GoodsService {
    /**
     * 新增一条商品数据
     * @param name 商品名称
     * @param desc 商品描述
     * @param price 价格
     * @param sellPrice 售价
     * @param cate 分类
     * @param leftAmount 剩余数量
     * @param image 图片链接
     * @return 新增条数
     */
    int addGoods(String name,String desc,double price,double sellPrice,String cate,int leftAmount,String image);

    /**
     * 通过商品ID获取商品数据
     * @param goodsId 商品ID
     * @return 商品实体类
     */
    Goods selectGoodsByGoodsId(String goodsId);

    /**
     * 根据商品ID删除商品数据
     * @param goodsId 商品ID
     * @return 删除条数
     */
    int deleteGoodsByGoodsId(String goodsId);

    /**
     * 更新商品名
     * @param goodsId 商品ID
     * @param goodsName 商品名
     * @return 修改条数
     */
    int updateGoodsName(String goodsId,String goodsName);

    /**
     * 更新商品描述
     * @param goodsId 商品ID
     * @param desc 描述
     * @return 修改条数
     */
    int updateGoodsDescription(String goodsId,String desc);

    /**
     * 更新商品价格
     * @param goodsId 商品ID
     * @param price 价格
     * @return 修改条数
     */
    int updateGoodsPrice(String goodsId,double price);

    /**
     * 更新商品售价
     * @param goodsId 商品ID
     * @param sellPrice 当前售价
     * @return 修改条数
     */
    int updateGoodsSellPrice(String goodsId,double sellPrice);

    /**
     * 修改商品分类
     * @param goodsId 商品ID
     * @param cateId 分类ID
     * @return 修改条数
     */
    int updateGoodsCate(String goodsId,String cateId);

    /**
     * 修改商品剩余数量
     * @param goodsId 商品ID
     * @param leftAmount 剩余数量
     * @return 修改条数
     * 注：数据库脏读幻读问题!
     */
    int updateGoodsLeftAmount(String goodsId,int leftAmount);

    /**
     * 更新商品图片信息
     * @param goodsId 商品ID
     * @param image 图片链接
     * @return 修改条数
     */
    int updateGoodsImage(String goodsId,String image);
}
