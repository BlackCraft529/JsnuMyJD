package com.jsnu.jd.jsnujd.service.Impl;

import com.jsnu.jd.jsnujd.mapper.GoodsMapper;
import com.jsnu.jd.jsnujd.mapper.UserMapper;
import com.jsnu.jd.jsnujd.pojo.Goods;
import com.jsnu.jd.jsnujd.pojo.User;
import com.jsnu.jd.jsnujd.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.*;

/**
 * @author 魏荣轩
 * @date 2020/8/6 18:43
 */
@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserMapper userMapper;
    /**
     * 新增一条商品数据
     *
     * @param name       商品名称
     * @param desc       商品描述
     * @param price      价格
     * @param sellPrice  售价
     * @param cate       分类
     * @param leftAmount 剩余数量
     * @param image      图片链接
     * @param publisher  发布者
     * @return 新增条数
     */
    @Override
    public int addGoods(String name, String desc, double price, double sellPrice, String cate, int leftAmount, String image,String publisher) {
        Goods goods = new Goods();
        String goodsId= UUID.randomUUID().toString().replaceAll("-","");
        while(goodsMapper.selectGoodsByGoodsId(goodsId)!=null){
            goodsId= UUID.randomUUID().toString().replaceAll("-","");
        }
        goods.setPublisher(publisher);
        goods.setId(goodsId);
        goods.setName(name);
        goods.setDescription(desc);
        goods.setPrice(price);
        goods.setSellPrice(sellPrice);
        goods.setCate(cate);
        goods.setLeftAmount(leftAmount);
        goods.setImage(image);
        goods.setSellAmount(0);
        goods.setCreateTime(new Date());
        return goodsMapper.addGoods(goods);
    }

    /**
     * 通过商品ID获取商品数据
     *
     * @param goodsId 商品ID
     * @return 商品实体类
     */
    @Override
    public Goods selectGoodsByGoodsId(String goodsId) {
        return goodsMapper.selectGoodsByGoodsId(goodsId);
    }

    /**
     * 根据商品ID删除商品数据
     *
     * @param goodsId 商品ID
     * @return 删除条数
     */
    @Override
    public int deleteGoodsByGoodsId(String goodsId) {
        return goodsMapper.deleteGoodsByGoodsId(goodsId);
    }

    /**
     * 更新商品名
     *
     * @param goodsId   商品ID
     * @param goodsName 商品名
     * @return 修改条数
     */
    @Override
    public int updateGoodsName(String goodsId, String goodsName) {
        if(goodsMapper.selectGoodsByGoodsId(goodsId)==null){
            return 0;
        }
        Goods goods = goodsMapper.selectGoodsByGoodsId(goodsId);
        goods.setName(goodsName);
        return goodsMapper.updateGoodsInfoByPojo(goods);
    }

    /**
     * 更新商品描述
     *
     * @param goodsId 商品ID
     * @param desc    描述
     * @return 修改条数
     */
    @Override
    public int updateGoodsDescription(String goodsId, String desc) {
        if(goodsMapper.selectGoodsByGoodsId(goodsId)==null){
            return 0;
        }
        Goods goods = goodsMapper.selectGoodsByGoodsId(goodsId);
        goods.setDescription(desc);
        return goodsMapper.updateGoodsInfoByPojo(goods);
    }

    /**
     * 更新商品价格
     *
     * @param goodsId 商品ID
     * @param price   价格
     * @return 修改条数
     */
    @Override
    public int updateGoodsPrice(String goodsId, double price) {
        if(goodsMapper.selectGoodsByGoodsId(goodsId)==null){
            return 0;
        }
        Goods goods = goodsMapper.selectGoodsByGoodsId(goodsId);
        goods.setPrice(price);
        return goodsMapper.updateGoodsInfoByPojo(goods);
    }

    /**
     * 更新商品售价
     *
     * @param goodsId   商品ID
     * @param sellPrice 当前售价
     * @return 修改条数
     */
    @Override
    public int updateGoodsSellPrice(String goodsId, double sellPrice) {
        if(goodsMapper.selectGoodsByGoodsId(goodsId)==null){
            return 0;
        }
        Goods goods = goodsMapper.selectGoodsByGoodsId(goodsId);
        goods.setSellPrice(sellPrice);
        return goodsMapper.updateGoodsInfoByPojo(goods);
    }

    /**
     * 修改商品分类
     *
     * @param goodsId 商品ID
     * @param cateId  分类ID
     * @return 修改条数
     */
    @Override
    public int updateGoodsCate(String goodsId, String cateId) {
        if(goodsMapper.selectGoodsByGoodsId(goodsId)==null){
            return 0;
        }
        Goods goods = goodsMapper.selectGoodsByGoodsId(goodsId);
        goods.setCate(cateId);
        return goodsMapper.updateGoodsInfoByPojo(goods);
    }

    /**
     * 修改商品剩余数量
     *
     * @param goodsId    商品ID
     * @param leftAmount 剩余数量
     * @return 修改条数
     * 注：数据库脏读幻读问题!
     */
    @Override
    public synchronized int updateGoodsLeftAmount(String goodsId, int leftAmount) {
        if(goodsMapper.selectGoodsByGoodsId(goodsId)==null){
            return 0;
        }
        Map<String,String> data=new HashMap<>();
        data.put("goodsId",goodsId);
        data.put("leftAmount",leftAmount+"");
        return goodsMapper.updateGoodsLeftAmount(data);
    }

    /**
     * 更新商品图片信息
     *
     * @param goodsId 商品ID
     * @param image   图片链接
     * @return 修改条数
     */
    @Override
    public int updateGoodsImage(String goodsId, String image) {
        if(goodsMapper.selectGoodsByGoodsId(goodsId)==null){
            return 0;
        }
        Goods goods = goodsMapper.selectGoodsByGoodsId(goodsId);
        goods.setImage(image);
        return goodsMapper.updateGoodsInfoByPojo(goods);
    }

    /**
     * 获取所有商品信息
     *
     * @return 商品信息列表
     */
    @Override
    public List<Goods> selectAllGoods() {
        return goodsMapper.selectAllGoods();
    }

    /**
     * 根据商品分类查找商品
     *
     * @param cate 分类ID
     * @return 商品信息列表
     */
    @Override
    public List<Goods> selectGoodsByCate(String cate) {
        return goodsMapper.selectGoodsByCate(cate);
    }

    /**
     * 根据商品名查找商品
     *
     * @param nameExact 准确商品名
     * @return 商品
     * @deprecated
     */
    @Override
    public List<Goods> selectGoodsByNameExact(String nameExact) {
        return selectGoodsByNameExact(nameExact);
    }

    /**
     * 根据商品名模糊搜索
     *
     * @param vagueName 商品名
     * @return 商品列表
     */
    @Override
    public List<Goods> selectGoodsByVagueName(String vagueName) {
        return goodsMapper.selectGoodsByVagueName("%"+vagueName+"%");
    }

    /**
     * 查找最新商品
     *
     * @param count 查找数量
     * @return 商品
     */
    @Override
    public List<Goods> findNewestGoods(int count) {
        return goodsMapper.findNewestGoods(count);
    }

    /**
     * 查找最热商品
     *
     * @param count 数量
     * @return 商品列表
     */
    @Override
    public List<Goods> findHotGoods(int count) {
        return goodsMapper.findHotGoods(count);
    }
}
