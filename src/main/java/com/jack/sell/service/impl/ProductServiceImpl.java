package com.jack.sell.service.impl;

import com.jack.sell.dataobject.ProductInfo;
import com.jack.sell.dto.CartDto;
import com.jack.sell.enums.ProductStatusEnum;
import com.jack.sell.enums.ResultEnum;
import com.jack.sell.exception.SellException;
import com.jack.sell.repository.ProductInfoRepository;
import com.jack.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = repository.findById(cartDto.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
            Integer result = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            ProductInfo productInfo = repository.findById(cartDto.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
            }
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }

    }

    //上架
    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).get();
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findById(productId).get();
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXISTS);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }
}
