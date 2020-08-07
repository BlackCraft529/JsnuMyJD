package com.jsnu.jd.jsnujd.mapper;

import com.jsnu.jd.jsnujd.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

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
     * @param data 商品数据，goodsId：id0001  leftAmount: 1
     * @return 修改条数
     */
    int updateGoodsLeftAmount(Map<String,String> data);

    /**
     * 获取所有商品信息
     * @return 商品信息列表
     */
    List<Goods> selectAllGoods();

    /**
     * 根据商品分类查找商品
     * @param cate 分类ID
     * @return 商品信息列表
     */
    List<Goods> selectGoodsByCate(String cate);

    /**
     * 根据商品名查找商品
     * @param nameExact 准确商品名
     * @return 商品
     * @deprecated
     */
    List<Goods> selectGoodsByNameExact(String nameExact);

    /**
     * 根据商品名模糊搜索
     * @param vagueName 商品名
     * @return 商品列表
     */
    List<Goods> selectGoodsByVagueName(String vagueName);

    /**
     * 查找最新商品
     * @param count 查找数量
     * @return 商品
     */
    List<Goods> findNewestGoods(int count);

    /**
     * 查找蓄热商品
     * @param count 数量
     * @return 商品列表
     */
    List<Goods> findHotGoods(int count);
}
