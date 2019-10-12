package com.jack.sell.service.impl;

import com.jack.sell.dataobject.ProductInfo;
import com.jack.sell.enums.ProductStatusEnum;
import com.jack.sell.repository.ProductInfoRepository;
import com.jack.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpall() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    /**
     * @param pageable
     * @return Example springboot2 引入
     */
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        ProductInfo productInfo = new ProductInfo();
        Example<ProductInfo> example = Example.of(productInfo);
        return repository.findAll(example, pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }
}
