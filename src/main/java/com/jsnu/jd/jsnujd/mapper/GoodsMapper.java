package com.jsnu.jd.jsnujd.mapper;

import com.jsnu.jd.jsnujd.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 魏荣轩
 * @date 2020/8/6 18:24
 */
@Mapper
@Repository
public interface GoodsMapper {

    /**
     * 新增一个商品
     * @param goods 商品
     * @return 新增条数
     */
    int addGoods(Goods goods);

    /**
     * 更新商品信息
     * @param goods 商品
     * @return 修改条数
     */
    int updateGoodsInfoByPojo(Goods goods);

    /**
     * 删除一个商品数据
     * @param goodsId 商品ID
     * @return 删除条数
     */
    int deleteGoodsByGoodsId(String goodsId);

    /**
     * 获取一个商品数据
     * @param goodsId 商品id
     * @return 商品信息
     */
    Goods selectGoodsByGoodsId(String goodsId);

    /**
     * 更新商品剩余数量
     * @param goodsId 商品ID
     * @param leftAmount 剩余数量
     * @return 修改条数
     */
    int updateGoodsLeftAmount(String goodsId,int leftAmount);
}
