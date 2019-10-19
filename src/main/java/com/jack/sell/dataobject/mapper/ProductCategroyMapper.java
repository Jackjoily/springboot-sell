package com.jack.sell.dataobject.mapper;

import com.jack.sell.dataobject.ProductCategory;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;

import java.util.Map;

/**
 * @program: sell
 * @description:
 * @author: jackjoily
 * @create: 2019-10-18 13:33
 */
@Mapper
public interface ProductCategroyMapper {
    @Insert("insert into  product_category(category_name,category_type) values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

    @Insert("insert into  product_category(category_name,category_type) values(#{categoryName},#{categoryType})")
    int insertByMapWithObject(ProductCategory productCategory);

    @Select("select * from product_category where category_type=#{categoryType}")
    ProductCategory getproductCategory(@Param("categoryType") int categoryType);

    @Update("update product_category set category_name=#{categoryName}, category_type=#{categoryType} where category_id=#{categoryId} ")
    int updatetproductCategory(ProductCategory productCategory) ;

}