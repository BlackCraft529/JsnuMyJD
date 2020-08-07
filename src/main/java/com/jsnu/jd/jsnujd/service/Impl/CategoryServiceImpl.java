package com.jsnu.jd.jsnujd.service.Impl;

import com.jsnu.jd.jsnujd.mapper.CategoryMapper;
import com.jsnu.jd.jsnujd.pojo.Category;
import com.jsnu.jd.jsnujd.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 魏荣轩
 * @date 2020/8/7 23:01
 */
@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据分类ID查询分类信息
     *
     * @param cateId 分类ID
     * @return 分类信息
     */
    @Override
    public Category selectCategoryByCateId(String cateId) {
        return categoryMapper.selectCategoryByCateId(cateId);
    }

    /**
     * 新增一个分类信息
     *
     * @param cateId 分类ID
     * @param name 分类名
     * @return 新增条数
     */
    @Override
    public int addCategory(String cateId,String name) {
        return categoryMapper.addCategory(new Category(cateId,name));
    }

    /**
     * 更新分类名称
     *
     * @param cateId 分类ID
     * @param name   分类名称
     * @return 修改条数
     */
    @Override
    public int updateCategoryName(String cateId, String name) {
        if(categoryMapper.selectCategoryByCateId(cateId)==null){
            return 0;
        }
        Map<String , String> data=new HashMap<>();
        data.put("cateId",cateId);
        data.put("name",name);
        return categoryMapper.updateCategoryName(data);
    }

    /**
     * 删除订单信息
     *
     * @param cateId 订单ID
     */
    @Override
    public void deleteCategoryByCateId(String cateId) {
        categoryMapper.deleteCategoryByCateId(cateId);
    }
}
