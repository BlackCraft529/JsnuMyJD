package com.jsnu.jd.jsnujd.mapper;

import com.jsnu.jd.jsnujd.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.Map;

/**
 * @author 魏荣轩
 * @date 2020/8/7 22:55
 */
@Mapper
@Repository
public interface CategoryMapper {

    /**
     * 根据分类ID查询分类信息
     * @param cateId 分类ID
     * @return 分类信息
     */
    Category selectCategoryByCateId(String cateId);

    /**
     * 新增一个分类
     * @param category 分类实体
     * @return 成功条数
     */
    int addCategory(Category category);

    /**
     * 更新订单名称
     * @param data cateId：分类ID  name：分类名称
     * @return 成功条数
     */
    int updateCategoryName(Map<String,String> data);

    /**
     * 删除订单信息
     * @param cateId 订单ID
     */
    void deleteCategoryByCateId(String cateId);
}
