package com.jack.sell.service;

import com.jack.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
