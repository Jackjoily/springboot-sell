package com.jack.sell.repository;

import com.jack.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@Repository
public interface ProductCategoryRepository  extends JpaRepository<ProductCategory,Integer> {
List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}
