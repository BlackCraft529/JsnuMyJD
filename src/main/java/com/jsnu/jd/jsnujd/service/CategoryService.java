package com.jsnu.jd.jsnujd.service;

import com.jsnu.jd.jsnujd.pojo.Category;

/**
 * @author 魏荣轩
 * @date 2020/8/7 23:01
 */
public interface CategoryService {
    /**
     * 根据分类ID查询分类信息
     * @param cateId 分类ID
     * @return 分类信息
     */
    Category selectCategoryByCateId(String cateId);

    /**
     * 新增一个分类信息
     * @param cateId 分类ID
     * @param name 分类名
     * @return 新增条数
     */
    int addCategory(String cateId,String name);

    /**
     * 更新分类名称
     * @param cateId 分类ID
     * @param name 分类名称
     * @return 修改条数
     */
    int updateCategoryName(String cateId,String name);

    /**
     * 删除订单信息
     * @param cateId 订单ID
     */
    void deleteCategoryByCateId(String cateId);
}
