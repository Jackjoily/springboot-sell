package com.jack.sell.service;

import com.jack.sell.dataobject.ProductInfo;
import com.jack.sell.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
public interface ProductService {

    ProductInfo findOne(String prodcutId);
    /**
     * 查询所有在架的商品
     */
    List<ProductInfo> findUpall();
    Page<ProductInfo> findAll(Pageable pageable);
    ProductInfo save(ProductInfo productInfo);
    List<ProductInfo> findByCategotyType(Integer type


    );

    //加库存
    void increaseStock(List<CartDto> cartDtoList);

    void decreaseStock(List<CartDto> cartDtoList);

    //减库存
    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);

}
