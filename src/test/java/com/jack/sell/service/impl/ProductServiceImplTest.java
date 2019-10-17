package com.jack.sell.service.impl;

import com.jack.sell.dataobject.ProductInfo;
import com.jack.sell.enums.ProductStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author jackjoily
 * @time ${Date}
 * @Description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl service;

    @Test
    public void findOne() {
        ProductInfo one = service.findOne("123456");
        assertEquals("123456", one.getProductId());
    }

    @Test
    public void findUpall() {
        List<ProductInfo> upall = service.findUpall();
        assertNotEquals(0, upall.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest=new PageRequest(0,2);
        Page<ProductInfo> all = service.findAll(pageRequest);
        System.out.println(all.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的虾");
        productInfo.setProductIcon("http://www.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);
        ProductInfo save = service.save(productInfo);
        assertNotEquals(null, save);
    }
    @Test
    public  void onSale(){
        service.onSale("123452");
    }
    @Test
    public  void offSale(){
        service.offSale("123452");
    }
}